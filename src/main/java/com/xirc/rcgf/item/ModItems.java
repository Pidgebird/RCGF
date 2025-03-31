package com.xirc.rcgf.item;

import com.xirc.rcgf.RequiemCraftGoldenFate;
import com.xirc.rcgf.item.armor.WhiteAlbumArmorItem;
import com.xirc.rcgf.item.weapons.Hamon_Infused_Blade;
import com.xirc.rcgf.item.weapons.luckpluck.Luck;
import com.xirc.rcgf.item.weapons.luckpluck.Luck_and_Pluck;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item HAMON_INGOT = registerItem("hamon_ingot", new Item(new FabricItemSettings()));
    public static final Item PILLARITE_INGOT = registerItem("pillarite_ingot", new Item(new FabricItemSettings()));
    public static final Item PHANTOM_INGOT = registerItem("phantom_ingot", new Item(new FabricItemSettings()));

    public static final Item HAMON_INFUSED_BLADE = registerItem("hamon_infused_blade", new Hamon_Infused_Blade());
    public static final Item LUCK_AND_PLUCK = registerItem("luck_and_pluck",
            new Luck_and_Pluck(net.minecraft.item.ToolMaterials.NETHERITE, new FabricItemSettings()));
    public static final Item LUCK = registerItem("luck", new Luck(new FabricItemSettings()));



    public static final Item WHITE_ALBUM_HELMET = registerItem("white_album_helmet",
            new WhiteAlbumArmorItem(ArmorItem.Type.HELMET));
    public static final Item WHITE_ALBUM_CHESTPLATE = registerItem("white_album_chestplate",
            new WhiteAlbumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Item WHITE_ALBUM_LEGGINGS = registerItem("white_album_leggings",
            new WhiteAlbumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Item WHITE_ALBUM_BOOTS = registerItem("white_album_boots",
            new WhiteAlbumArmorItem(ArmorItem.Type.BOOTS));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RequiemCraftGoldenFate.MOD_ID, name), item);
    }

    public static void registerModItems() {
        RequiemCraftGoldenFate.LOGGER.info("Registering Mod Items for " + RequiemCraftGoldenFate.MOD_ID);
    }
}