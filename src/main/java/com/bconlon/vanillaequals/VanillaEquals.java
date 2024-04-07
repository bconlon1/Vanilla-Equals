package com.bconlon.vanillaequals;

import com.bconlon.vanillaequals.blocks.EqualsPaintingVariants;
import com.bconlon.vanillaequals.data.EqualsData;
import com.mojang.logging.LogUtils;
import net.minecraft.DetectedVersion;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.Optional;

@Mod(VanillaEquals.MODID)
public class VanillaEquals {
    public static final String MODID = "vanilla_equals";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VanillaEquals(IEventBus bus, Dist dist) {
        bus.addListener(EqualsData::dataSetup);

        DeferredRegister<?>[] registers = {
                EqualsPaintingVariants.PAINTINGS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }
}
