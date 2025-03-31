package com.xirc.rcgf.item.util;

import com.xirc.rcgf.RequiemCraftGoldenFate;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum WhiteAlbumArmorMaterials implements ArmorMaterial {
    WHITE_ALBUM("white_album", 0, new int[] { 5, 8, 6, 3 }, 0,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 1f, () -> Ingredient.EMPTY);

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;
    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    WhiteAlbumArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                             SoundEvent equipSound, float toughness, float knockbackResistance,
                             Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return RequiemCraftGoldenFate.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    // Custom armor item class to handle specific enchantments and properties
    public static class WhiteAlbumArmorItem extends ArmorItem {
        public WhiteAlbumArmorItem(Type type) {
            super(WhiteAlbumArmorMaterials.WHITE_ALBUM, type,
                    new Item.Settings().maxCount(1).fireproof());
        }

        @Override
        public void inventoryTick(ItemStack stack, net.minecraft.world.World world,
                                  net.minecraft.entity.Entity entity, int slot, boolean selected) {

                stack.addEnchantment(Enchantments.BINDING_CURSE, 1);
                stack.addEnchantment(Enchantments.VANISHING_CURSE, 1);

                // Protection enchantment for helmet, chestplate, and leggings
                if (this.getType() != Type.BOOTS) {
                    stack.addEnchantment(Enchantments.PROTECTION, 2);
                }

                // Frost Walker for boots
                if (this.getType() == Type.BOOTS) {
                    stack.addEnchantment(Enchantments.FROST_WALKER, 1);
                }
            }
        }
    }