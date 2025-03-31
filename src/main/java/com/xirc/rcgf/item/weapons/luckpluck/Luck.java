package com.xirc.rcgf.item.weapons.luckpluck;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class Luck extends SwordItem {

    public Luck(Settings settings) {
        super(ToolMaterials.DIAMOND, 4, -2.8f, settings); // Base damage and attack speed
    }

    @Override
    public boolean isDamageable() {
        return false;  // Makes the item unbreakable
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return false;  // No durability bar
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Apply slowness effect on hit
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1)); // 1 second slowness
        return super.postHit(stack, target, attacker);  // Call the parent class's postHit method
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("A legendary blade once wielded by Bruford. ").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("Serves as a precursor to the enhanced Luck & Pluck. ").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
    }
}
