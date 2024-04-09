package com.bconlon.vanillaequals.data;

import com.bconlon.vanillaequals.data.generators.EqualsItemModelData;
import com.bconlon.vanillaequals.data.generators.EqualsLanguageData;
import com.bconlon.vanillaequals.data.generators.tags.EqualsBiomeTagData;
import com.bconlon.vanillaequals.data.generators.tags.EqualsPaintingVariantTagData;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class EqualsData {
    public static void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        // Client Data
        generator.addProvider(event.includeClient(), new EqualsItemModelData(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new EqualsLanguageData(packOutput));

        // Server Data
        // Tags
        generator.addProvider(event.includeServer(), new EqualsBiomeTagData(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new EqualsPaintingVariantTagData(packOutput, lookupProvider, fileHelper));

        // pack.mcmeta
        generator.addProvider(true, new PackMetadataGenerator(packOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.translatable("pack.vanilla_equals.mod.description"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
    }
}
