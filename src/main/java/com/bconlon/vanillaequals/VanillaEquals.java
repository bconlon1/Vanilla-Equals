package com.bconlon.vanillaequals;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.blocks.EqualsBlocks;
import com.bconlon.vanillaequals.blocks.EqualsPaintingVariants;
import com.bconlon.vanillaequals.data.EqualsData;
import com.bconlon.vanillaequals.effect.EqualsEffects;
import com.bconlon.vanillaequals.item.EqualsItems;
import com.bconlon.vanillaequals.network.EqualsPackets;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(VanillaEquals.MODID)
public class VanillaEquals {
    public static final String MODID = "vanilla_equals";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VanillaEquals(IEventBus bus, Dist dist) {
        bus.addListener(EqualsData::dataSetup);
        bus.addListener(this::commonSetup);
        bus.addListener(EqualsPackets::packetSetup);

        DeferredRegister<?>[] registers = {
                EqualsBlocks.BLOCKS,
                EqualsItems.ITEMS,
                EqualsEffects.EFFECTS,
                EqualsPaintingVariants.PAINTINGS,
                EqualsAttachments.ATTACHMENTS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EqualsBlocks.registerPots();
            EqualsBlocks.registerFlammability();
        });
    }
}
