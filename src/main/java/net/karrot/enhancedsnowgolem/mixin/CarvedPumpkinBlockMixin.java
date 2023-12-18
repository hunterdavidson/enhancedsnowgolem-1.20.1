package net.karrot.enhancedsnowgolem.mixin;

import net.karrot.enhancedsnowgolem.entity.ModEntities;
import net.karrot.enhancedsnowgolem.entity.custom.PackedSnowGolemEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.include.com.google.common.base.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {
    @Unique
    private static final Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE = state -> state != null && (state.isOf(Blocks.CARVED_PUMPKIN) || state.isOf(Blocks.JACK_O_LANTERN));
    @Unique
    @Nullable
    private BlockPattern packedSnowGolemPattern = null;

    @Inject(method = "onBlockAdded", at = @At("HEAD"))
    private void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (!oldState.isOf(state.getBlock())) {
            trySpawnPackedSnowGolem(world, pos);
        }
    }

    @Unique
    private void trySpawnPackedSnowGolem(World world, BlockPos pos) {
        if (!world.isClient) {
            BlockPattern.Result result = this.getPackedSnowGolemPattern().searchAround(world, pos);
            if (result != null) {
                PackedSnowGolemEntity packedSnowGolemEntity = ModEntities.PACKED_SNOW_GOLEM.create(world);
                if (packedSnowGolemEntity != null) {
                    spawnEntity(world, result, packedSnowGolemEntity, result.translate(0, 2, 0).getBlockPos());
                }
            }
        }
    }
    @Unique
    private BlockPattern getPackedSnowGolemPattern() {
        if (this.packedSnowGolemPattern == null) {
            this.packedSnowGolemPattern = BlockPatternBuilder.start().aisle("^", "#", "#").where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.PACKED_ICE))).build();
        }
        return this.packedSnowGolemPattern;
    }

    @Unique
    private static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        CarvedPumpkinBlock.breakPatternBlocks(world, patternResult);
        entity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0f, 0.0f);
        world.spawnEntity(entity);
        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }
        CarvedPumpkinBlock.updatePatternBlocks(world, patternResult);
    }
}