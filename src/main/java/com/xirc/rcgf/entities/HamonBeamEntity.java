package com.xirc.rcgf.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HamonBeamEntity extends PersistentProjectileEntity {

    private static final int MAX_LIFE_TICKS = 10000;
    private static final float DAMAGE_AMOUNT = 8.0F;
    private static final int FIRE_DURATION = 4;

    public HamonBeamEntity(EntityType<? extends HamonBeamEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true); // Remove gravity effect
    }

    public HamonBeamEntity(World world, LivingEntity owner) {
        super(ModEntities.HAMON_BEAM, owner, world);
        this.setNoGravity(true);
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d velocity = this.getVelocity();
        Vec3d pos = this.getPos();
        Vec3d nextPos = pos.add(velocity);
        this.setPosition(nextPos);

        if (this.getWorld().isClient) {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();

            // Particle trail effect
            double speedFactor = 0.1;
            for (int i = 0; i < 10; i++) {
                double offsetX = velocity.x * i * speedFactor;
                double offsetY = velocity.y * i * speedFactor;
                double offsetZ = velocity.z * i * speedFactor;

                this.getWorld().addParticle(
                        ParticleTypes.END_ROD,
                        x - offsetX,
                        y - offsetY,
                        z - offsetZ,
                        0, 0, 0
                );
            }
        }

        this.checkBlockCollision();

        if (this.age > MAX_LIFE_TICKS) {
            if (!this.getWorld().isClient) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.NEUTRAL,
                        0.7F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
            }
            this.discard();
        }

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient && entityHitResult.getEntity() instanceof LivingEntity target) {
            target.damage(this.getDamageSources().magic(), DAMAGE_AMOUNT);
            target.setOnFireFor(FIRE_DURATION);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS,
                    1.0F, 1.0F);

            for (int i = 0; i < 8; i++) {
                ((net.minecraft.server.world.ServerWorld)this.getWorld()).spawnParticles(
                        ParticleTypes.FLAME,
                        this.getX(), this.getY(), this.getZ(),
                        10, 0.2D, 0.2D, 0.2D, 0.05D
                );
            }

            this.discard();
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    public EntitySpawnS2CPacket createSpawnPacket() {
        // Fabric-specific spawn packet
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public net.minecraft.entity.EntityDimensions getDimensions(EntityPose pose) {
        // Double the hitbox size
        return super.getDimensions(pose).scaled(2.0F);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected float getDragInWater() {
        return 1.0F;
    }
}
