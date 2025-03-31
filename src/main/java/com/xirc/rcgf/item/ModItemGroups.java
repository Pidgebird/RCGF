package com.xirc.rcgf.item;

import com.xirc.rcgf.RequiemCraftGoldenFate;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup REQUIEMCRAFT_ITEMS = Registry.register(Registries.ITEM_GROUP,
             new Identifier(RequiemCraftGoldenFate.MOD_ID, "hamon_ingot"),
             FabricItemGroup.builder().displayName(Text.translatable("itemgroup.requiemcraft_items"))
                     .icon(() -> new ItemStack(ModItems.HAMON_INGOT)).entries((displayContext, entries) -> {
                         entries.add(ModItems.HAMON_INGOT);
                         entries.add(ModItems.PILLARITE_INGOT);
                         entries.add(ModItems.PHANTOM_INGOT);
                         entries.add(ModItems.HAMON_INFUSED_BLADE);
                         entries.add(ModItems.WHITE_ALBUM_HELMET);
                         entries.add(ModItems.WHITE_ALBUM_CHESTPLATE);
                         entries.add(ModItems.WHITE_ALBUM_LEGGINGS);
                         entries.add(ModItems.WHITE_ALBUM_BOOTS);
                         entries.add(ModItems.LUCK_AND_PLUCK);
                         entries.add(ModItems.LUCK);

                     }).build());

    public static void registerItemGroups() {
        RequiemCraftGoldenFate.LOGGER.info("Registering Item Groups for " +RequiemCraftGoldenFate.MOD_ID);

    }
}
