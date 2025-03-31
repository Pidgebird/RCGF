package com.xirc.rcgf.datagen;

import com.xirc.rcgf.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HAMON_INFUSED_BLADE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LUCK_AND_PLUCK, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LUCK, Models.HANDHELD);
    }

    // Add this method to satisfy the interface requirement
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Leave it empty if you don't have block models to generate
    }
}
