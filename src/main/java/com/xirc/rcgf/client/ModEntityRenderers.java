package com.xirc.rcgf.client;

import com.xirc.rcgf.client.renderer.HamonBeamRenderer;
import com.xirc.rcgf.entities.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.HAMON_BEAM, HamonBeamRenderer::new);
    }
}