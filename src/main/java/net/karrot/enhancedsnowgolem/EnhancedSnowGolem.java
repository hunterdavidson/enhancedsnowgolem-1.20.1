package net.karrot.enhancedsnowgolem;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.karrot.enhancedsnowgolem.entity.ModEntities;
import net.karrot.enhancedsnowgolem.entity.client.ModModelLayers;
import net.karrot.enhancedsnowgolem.entity.client.PackedSnowGolemModel;
import net.karrot.enhancedsnowgolem.entity.client.PackedSnowGolemRenderer;
import net.karrot.enhancedsnowgolem.entity.custom.PackedSnowGolemEntity;
import net.karrot.enhancedsnowgolem.item.ModItems;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedSnowGolem implements ModInitializer {
	public static final String MOD_ID = "enhancedsnowgolem";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		EntityRendererRegistry.register(ModEntities.PACKED_SNOWBALL_ENTITY, FlyingItemEntityRenderer::new);

		FabricDefaultAttributeRegistry.register(ModEntities.PACKED_SNOW_GOLEM, PackedSnowGolemEntity.createSnowGolemAttributes());
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PACKED_SNOW_GOLEM, PackedSnowGolemModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.PACKED_SNOW_GOLEM, PackedSnowGolemRenderer::new);
	}


}