package net.karrot.enhancedsnowgolem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.karrot.enhancedsnowgolem.entity.ModEntities;
import net.karrot.enhancedsnowgolem.entity.custom.PackedSnowGolemEntity;
import net.karrot.enhancedsnowgolem.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedSnowGolem implements ModInitializer {
	public static final String MOD_ID = "enhancedsnowgolem";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();

		FabricDefaultAttributeRegistry.register(ModEntities.PACKED_SNOW_GOLEM, PackedSnowGolemEntity.createSnowGolemAttributes());
	}


}