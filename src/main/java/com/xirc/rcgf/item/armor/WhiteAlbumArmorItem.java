package com.xirc.rcgf.item.armor;

import com.xirc.rcgf.item.util.WhiteAlbumArmorMaterials;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WhiteAlbumArmorItem extends ArmorItem {
    public WhiteAlbumArmorItem(Type type) {
        super(WhiteAlbumArmorMaterials.WHITE_ALBUM, type,
                new Item.Settings().maxCount(1).fireproof());
    }

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.world.World world,
                              net.minecraft.entity.Entity entity, int slot, boolean selected) {
        // Add enchantments if not already present
        if (stack.getEnchantments().isEmpty()) {
            // Curse of Binding
            stack.addEnchantment(Enchantments.BINDING_CURSE, 1);

            // Curse of Vanishing
            stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);

            // Protection 2 for helmet, chestplate, and leggings
            if (this.getType() != Type.BOOTS) {
                stack.addEnchantment(Enchantments.PROTECTION, 2);
            }

            // Frost Walker 1 for boots
            if (this.getType() == Type.BOOTS) {
                stack.addEnchantment(Enchantments.FROST_WALKER, 1);
            }
        }
    }
}