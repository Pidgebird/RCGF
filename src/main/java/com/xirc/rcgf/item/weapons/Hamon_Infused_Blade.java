package com.xirc.rcgf.item.weapons;

import com.xirc.rcgf.entities.HamonBeamEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Hamon_Infused_Blade extends SwordItem {

    private static final int BEAM_COOLDOWN_TICKS = 100;  // 5 seconds cooldown
    private static final Logger log = LoggerFactory.getLogger(Hamon_Infused_Blade.class);

    public Hamon_Infused_Blade() {
        super(ToolMaterials.DIAMOND, 6, -2.4F, new Settings().maxCount(1).fireproof());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (player == null || world == null) {
            log.warn("Player or world is null in Hamon Infused Blade use method");
            return new TypedActionResult<>(ActionResult.FAIL, null);
        }
        ItemStack stack = player.getStackInHand(hand);


        if (player.getItemCooldownManager().isCoolingDown(this)) {
            return new TypedActionResult<>(ActionResult.FAIL, stack);
        }

        if (!world.isClient) {
            try {
                HamonBeamEntity beam = new HamonBeamEntity(world, player);

                // More precise positioning
                beam.setPosition(
                        player.getX() + player.getRotationVector().x,
                        player.getEyeY() - 0.2,
                        player.getZ() + player.getRotationVector().z
                );

                // Validate velocity
                beam.setVelocity(player.getRotationVector().multiply(5.0));

                world.spawnEntity(beam);

                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                player.getItemCooldownManager().set(this, BEAM_COOLDOWN_TICKS);
            } catch (Exception e) {
                log.error("Error spawning Hamon Beam Entity", e);
                return new TypedActionResult<>(ActionResult.FAIL, stack);
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("The Hamon Infused Blade is a legendary weapon forged").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("with Ripple energy, capable of piercing through foes").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("and unleashing a searing Hamon Beam of light that").formatted(Formatting.YELLOW));
        tooltip.add(Text.literal("sets enemies on ").formatted(Formatting.YELLOW).append(Text.literal("fire.").formatted(Formatting.GOLD)));
        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("Right-click: ").formatted(Formatting.AQUA).append(Text.literal("Release Hamon Beam (8s cooldown)").formatted(Formatting.WHITE)));
        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
    }


    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

}