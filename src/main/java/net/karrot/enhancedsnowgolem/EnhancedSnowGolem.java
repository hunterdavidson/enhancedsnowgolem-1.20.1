package net.karrot.enhancedsnowgolem;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.karrot.enhancedsnowgolem.entity.ModEntities;
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
	}


}