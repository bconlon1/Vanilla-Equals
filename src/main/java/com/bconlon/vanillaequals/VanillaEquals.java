package com.bconlon.vanillaequals;

import com.bconlon.vanillaequals.attachment.EqualsAttachments;
import com.bconlon.vanillaequals.blocks.EqualsPaintingVariants;
import com.bconlon.vanillaequals.data.EqualsData;
import com.bconlon.vanillaequals.item.EqualsItems;
import com.bconlon.vanillaequals.network.EqualsPackets;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(VanillaEquals.MODID)
public class VanillaEquals {
    public static final String MODID = "vanilla_equals";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VanillaEquals(IEventBus bus, Dist dist) {
        bus.addListener(EqualsData::dataSetup);
        bus.addListener(EqualsPackets::packetSetup);

        DeferredRegister<?>[] registers = {
                EqualsItems.ITEMS,
                EqualsPaintingVariants.PAINTINGS,
                EqualsAttachments.ATTACHMENTS
        };

        for (DeferredRegister<?> register : registers) {
            register.register(bus);
        }
    }
}
