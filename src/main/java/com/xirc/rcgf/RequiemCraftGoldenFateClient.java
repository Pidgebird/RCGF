package com.xirc.rcgf;

import com.xirc.rcgf.client.renderer.HamonBeamRenderer;
import com.xirc.rcgf.entities.HamonBeamEntity;
import com.xirc.rcgf.entities.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class RequiemCraftGoldenFateClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.HAMON_BEAM, HamonBeamRenderer::new);
    }
}
