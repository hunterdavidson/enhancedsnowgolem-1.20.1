package net.karrot.enhancedsnowgolem.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.karrot.enhancedsnowgolem.EnhancedSnowGolem;
import net.karrot.enhancedsnowgolem.entity.custom.PackedSnowballEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<PackedSnowballEntity> PACKED_SNOWBALL_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(EnhancedSnowGolem.MOD_ID, "packed_snowball_entity"), FabricEntityTypeBuilder.<PackedSnowballEntity>create(SpawnGroup.MISC, PackedSnowballEntity::new)
            .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
}
