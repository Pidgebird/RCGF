package com.xirc.rcgf.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModEntities {
    // Register the Hamon Beam Entity Type
    public static final EntityType<HamonBeamEntity> HAMON_BEAM = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("rcgf", "hamon_beam"),
            FabricEntityTypeBuilder.<HamonBeamEntity>create(SpawnGroup.MISC, (type, world) -> new HamonBeamEntity(type, world))
                    .dimensions(EntityDimensions.fixed(0.8f, 0.5f)) // Adjust size as needed
                    .trackRangeBlocks(30)
                    .trackedUpdateRate(20)
                    .build()
    );

    public static void registerEntities() {
        // This method can be called in your mod's main initialization
    }
}