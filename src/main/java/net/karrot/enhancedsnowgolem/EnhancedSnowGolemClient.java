package net.karrot.enhancedsnowgolem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.karrot.enhancedsnowgolem.entity.ModEntities;
import net.karrot.enhancedsnowgolem.entity.client.ModModelLayers;
import net.karrot.enhancedsnowgolem.entity.client.PackedSnowGolemModel;
import net.karrot.enhancedsnowgolem.entity.client.PackedSnowGolemRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class EnhancedSnowGolemClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){

        EntityRendererRegistry.register(ModEntities.PACKED_SNOWBALL_ENTITY, FlyingItemEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PACKED_SNOW_GOLEM, PackedSnowGolemModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PACKED_SNOW_GOLEM, PackedSnowGolemRenderer::new);
    }
}
