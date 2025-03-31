package com.xirc.rcgf.item.weapons.luckpluck;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.client.item.TooltipContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Luck_and_Pluck extends SwordItem {
    private static final String BUFF_KEY = "luck_and_pluck_buff";

    public Luck_and_Pluck(ToolMaterial material, Settings settings) {
        super(material, 7, -3.2f, settings);  // Base damage and attack speed
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("The Luck & Pluck sword channels hamon through the blade, ").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("empowering the user to deliver a devastating strike ").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("that deals ").formatted(Formatting.BLUE)
                .append(Text.literal("Double Damage").formatted(Formatting.DARK_BLUE))
                .append(Text.literal(" but harms the user").formatted(Formatting.RED)));
        tooltip.add(Text.literal("").formatted(Formatting.GOLD));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        // Apply slowness effect on hit
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 0));

        // Check if the sword has the buff
        NbtCompound nbt = stack.getOrCreateNbt();
        if (nbt.getBoolean(BUFF_KEY)) {
            float baseDamage = 7;  // Base sword damage

            if (attacker instanceof PlayerEntity player) {
                // Apply double damage to the target
                target.damage(player.getDamageSources().playerAttack(player), baseDamage);

                // Apply self-damage (2 hearts)
                player.damage(player.getDamageSources().magic(), 4.0F);

                // Visual effects for double damage
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            ParticleTypes.CRIT,
                            target.getX(),
                            target.getY() + target.getHeight() / 2,
                            target.getZ(),
                            30, 0.5, 0.5, 0.5, 0.1
                    );
                }

                // Play critical hit sound
                world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0f, 0.8f);
            }

            // Reset the buff after use
            nbt.putBoolean(BUFF_KEY, false);

            // Deactivation particles
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.SMOKE,
                        attacker.getX(),
                        attacker.getY() + 1.0,
                        attacker.getZ(),
                        10, 0.3, 0.3, 0.3, 0.05
                );
            }
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Get the NBT data
        NbtCompound nbt = stack.getOrCreateNbt();

        // Check if not on cooldown
        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            if (!nbt.getBoolean(BUFF_KEY)) {
                // Activate buff
                nbt.putBoolean(BUFF_KEY, true);

                // Apply glowing effect to the user
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0)); // 5 seconds

                // Play activation sound
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.PLAYERS,
                        0.7f, 1.2f);

                // Spawn particles for buff activation
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            ParticleTypes.END_ROD,
                            user.getX(),
                            user.getY() + 1.0,
                            user.getZ(),
                            40, 0.5, 1.0, 0.5, 0.1
                    );
                }

                // Show activation message
                if (!world.isClient) {
                    user.sendMessage(Text.literal("§6§lLuck and Pluck activated! Next hit deals double damage!").formatted(Formatting.GOLD), true);
                }

                // Set cooldown (12 seconds)
                user.getItemCooldownManager().set(this, 240);

                return TypedActionResult.success(stack);
            }
        } else {
            if (!world.isClient) {
                user.sendMessage(Text.literal("§7Luck and Pluck is recharging...").formatted(Formatting.GRAY), true);
            }
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean isDamageable() {
        return false;  // Unbreakable
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return false;  // No durability bar
    }
}
