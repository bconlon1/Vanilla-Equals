package com.bconlon.vanillaequals.item.miscellaneous;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.entity.passive.Variant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class VariantSpawnEggItem extends SpawnEggItem {
    private static final DispenseItemBehavior DEFAULT_DISPENSE_BEHAVIOR = (source, stack) -> {
        Direction face = source.state().getValue(DispenserBlock.FACING);
        EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
        try {
            type.spawn(source.level(), stack, null, source.pos().relative(face), MobSpawnType.DISPENSER, face != Direction.UP, false);
        } catch (Exception exception) {
            DispenseItemBehavior.LOGGER.error("Error while dispensing spawn egg from dispenser at {}", source.pos(), exception);
            return ItemStack.EMPTY;
        }
        stack.shrink(1);
        source.level().gameEvent(GameEvent.ENTITY_PLACE, source.pos(), GameEvent.Context.of(source.state()));
        return stack;
    };

    private static final List<VariantSpawnEggItem> VARIANT_EGGS = new ArrayList<>();
    private static final Map<Variant, VariantSpawnEggItem> VARIANT_MAP = new IdentityHashMap<>();
    private final Supplier<? extends EntityType<? extends Mob>> typeSupplier;
    private final Variant variant;
    private final Function<String, Variant> getter;

    public VariantSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, Variant variant, Function<String, Variant> getter, int backgroundColor, int highlightColor, Properties properties) {
        super(null, backgroundColor, highlightColor, properties);
        this.typeSupplier = type;
        this.variant = variant;
        this.getter = getter;
        VARIANT_EGGS.add(this);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemStack = context.getItemInHand();
            BlockPos blockPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockState = level.getBlockState(blockPos);
            if (level.getBlockEntity(blockPos) instanceof Spawner spawner) {
                EntityType<?> entityType = this.getType(itemStack.getTag());
                spawner.setEntityId(entityType, level.getRandom());
                level.sendBlockUpdated(blockPos, blockState, blockState, 3);
                level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
                itemStack.shrink(1);
            } else {
                BlockPos relativePos;
                if (blockState.getCollisionShape(level, blockPos).isEmpty()) {
                    relativePos = blockPos;
                } else {
                    relativePos = blockPos.relative(direction);
                }
                EntityType<?> entityType = this.getType(itemStack.getTag());
                itemStack.addTagElement("NaturalVariant", StringTag.valueOf(this.getDefaultVariant().getSerializedName()));
                if (entityType.spawn((ServerLevel) level, itemStack, context.getPlayer(), relativePos, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockPos, relativePos) && direction == Direction.UP) != null) {
                    itemStack.shrink(1);
                    level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
                }
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemStack);
        } else if (!(level instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemStack);
        } else {
            BlockPos blockPos = hitResult.getBlockPos();
            if (!(level.getBlockState(blockPos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemStack);
            } else if (level.mayInteract(player, blockPos) && player.mayUseItemAt(blockPos, hitResult.getDirection(), itemStack)) {
                EntityType<?> entityType = this.getType(itemStack.getTag());
                itemStack.addTagElement("NaturalVariant", StringTag.valueOf(this.getDefaultVariant().getSerializedName()));
                Entity entity = entityType.spawn((ServerLevel) level, itemStack, player, blockPos, MobSpawnType.SPAWN_EGG, false, false);
                if (entity == null) {
                    return InteractionResultHolder.pass(itemStack);
                } else {
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
                    return InteractionResultHolder.consume(itemStack);
                }
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }
    }

    @Override
    public Optional<Mob> spawnOffspringFromSpawnEgg(Player player, Mob mob, EntityType<? extends Mob> entityType, ServerLevel serverLevel, Vec3 pPos, ItemStack stack) {
        Variant variant = null;
        if (mob.hasData(EqualsAttachments.MOB_VARIANT)) {
            variant = mob.getData(EqualsAttachments.MOB_VARIANT).getVariant(this.getter);
        }
        if (!this.spawnsEntity(stack.getTag(), entityType, variant)) {
            return Optional.empty();
        } else {
            Mob mob1;
            if (mob instanceof AgeableMob ageableMob) {
                mob1 = ageableMob.getBreedOffspring(serverLevel, ageableMob);
            } else {
                mob1 = entityType.create(serverLevel);
            }
            if (mob1 == null) {
                return Optional.empty();
            } else { //todo
                mob1.setBaby(true);
                if (!mob1.isBaby()) {
                    return Optional.empty();
                } else {
                    mob1.moveTo(pPos.x(), pPos.y(), pPos.z(), 0.0F, 0.0F);
                    serverLevel.addFreshEntityWithPassengers(mob1);
                    if (stack.hasCustomHoverName()) {
                        mob1.setCustomName(stack.getHoverName());
                    }
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    return Optional.of(mob1);
                }
            }
        }
    }

    public boolean spawnsEntity(@Nullable CompoundTag nbt, EntityType<?> type, Variant variant) {
        return this.spawnsEntity(nbt, type) && Objects.equals(this.getDefaultVariant(), variant);
    }

    @Override
    protected EntityType<?> getDefaultType() {
        return this.typeSupplier.get();
    }

    public Variant getDefaultVariant() {
        return this.variant;
    }

    @Mod.EventBusSubscriber(modid = VanillaEquals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class CommonHandler {
        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> VARIANT_EGGS.forEach(egg -> {
                DispenserBlock.registerBehavior(egg, DEFAULT_DISPENSE_BEHAVIOR);
                VARIANT_MAP.put(egg.variant, egg);
            }));
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = VanillaEquals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class ColorRegisterHandler {
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void registerSpawnEggColors(RegisterColorHandlersEvent.Item event) {
            VARIANT_EGGS.forEach(egg -> event.register((stack, layer) -> egg.getColor(layer), egg));
        }
    }
}
