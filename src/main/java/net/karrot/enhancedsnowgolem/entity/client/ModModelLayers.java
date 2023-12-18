package net.karrot.enhancedsnowgolem.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.karrot.enhancedsnowgolem.EnhancedSnowGolem;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
@Environment(EnvType.CLIENT)
public class ModModelLayers {
    public static final EntityModelLayer PACKED_SNOW_GOLEM =
            new EntityModelLayer(new Identifier(EnhancedSnowGolem.MOD_ID, "packed_snow_golem"), "main");
}
