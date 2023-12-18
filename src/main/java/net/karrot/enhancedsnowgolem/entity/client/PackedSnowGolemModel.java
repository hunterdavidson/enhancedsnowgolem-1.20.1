package net.karrot.enhancedsnowgolem.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.karrot.enhancedsnowgolem.entity.custom.PackedSnowGolemEntity;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.model.ModelPart;

@Environment(value= EnvType.CLIENT)
public class PackedSnowGolemModel<T extends PackedSnowGolemEntity>
        extends SnowGolemEntityModel<T> {

    public PackedSnowGolemModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        // Add any additional angle setting here
    }

    @Override
    public void animateModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.animateModel(entity, limbSwing, limbSwingAmount, partialTick);
        // Add any additional animations here
    }
}