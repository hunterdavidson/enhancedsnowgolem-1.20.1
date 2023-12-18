package net.karrot.enhancedsnowgolem.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.karrot.enhancedsnowgolem.EnhancedSnowGolem;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SnowmanPumpkinFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.Identifier;
@Environment(value=EnvType.CLIENT)
public class PackedSnowGolemRenderer
        extends MobEntityRenderer<SnowGolemEntity, SnowGolemEntityModel<SnowGolemEntity>> {
    private static final Identifier TEXTURE = new Identifier(EnhancedSnowGolem.MOD_ID, "textures/entity/packed_snow_golem.png");

    public PackedSnowGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new SnowGolemEntityModel<>(context.getPart(EntityModelLayers.SNOW_GOLEM)), 0.5f);
        this.addFeature(new SnowmanPumpkinFeatureRenderer(this, context.getBlockRenderManager(), context.getItemRenderer()));
    }

    @Override
    public Identifier getTexture(SnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}

