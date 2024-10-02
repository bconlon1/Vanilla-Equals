package com.bconlon.vanillaequals.data.generators;

import com.bconlon.vanillaequals.VanillaEquals;
import com.bconlon.vanillaequals.blocks.EqualsBlocks;
import com.bconlon.vanillaequals.data.providers.EqualsBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EqualsBlockStateData extends EqualsBlockStateProvider {
    public EqualsBlockStateData(PackOutput output, ExistingFileHelper helper) {
        super(output, VanillaEquals.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.crossBlock(EqualsBlocks.BUTTERCUP.get());
    }
}
