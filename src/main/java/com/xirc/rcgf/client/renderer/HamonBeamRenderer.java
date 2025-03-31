package com.xirc.rcgf.client.renderer;

import com.xirc.rcgf.entities.HamonBeamEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class HamonBeamRenderer extends ProjectileEntityRenderer<HamonBeamEntity> {
    private static final Identifier TEXTURE = new Identifier("rcgf", "textures/entity/hamon_beam.png");

    public HamonBeamRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(HamonBeamEntity entity) {
        return TEXTURE;
    }
}