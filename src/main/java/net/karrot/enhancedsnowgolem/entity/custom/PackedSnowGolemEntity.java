    package net.karrot.enhancedsnowgolem.entity.custom;

    import net.karrot.enhancedsnowgolem.item.ModItems;
    import net.minecraft.block.BlockState;
    import net.minecraft.block.Blocks;
    import net.minecraft.entity.*;
    import net.minecraft.entity.ai.goal.*;
    import net.minecraft.entity.attribute.DefaultAttributeContainer;
    import net.minecraft.entity.attribute.EntityAttributes;
    import net.minecraft.entity.damage.DamageSource;
    import net.minecraft.entity.mob.MobEntity;
    import net.minecraft.entity.mob.Monster;
    import net.minecraft.entity.passive.SnowGolemEntity;
    import net.minecraft.entity.player.PlayerEntity;
    import net.minecraft.registry.tag.BiomeTags;
    import net.minecraft.sound.SoundEvents;
    import net.minecraft.util.math.BlockPos;
    import net.minecraft.util.math.MathHelper;
    import net.minecraft.world.GameRules;
    import net.minecraft.world.World;
    import net.minecraft.world.event.GameEvent;

    public class PackedSnowGolemEntity extends SnowGolemEntity{

        public PackedSnowGolemEntity(EntityType<? extends SnowGolemEntity> entityType, World world) {
            super(entityType, world);
        }

        @Override
        protected void initGoals() {
            this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25, 20, 10.0f));
            this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0, 1.0000001E-5f));
            this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
            this.goalSelector.add(4, new LookAroundGoal(this));
            this.targetSelector.add(1, new ActiveTargetGoal<>(this, MobEntity.class, 10, true, false, entity -> entity instanceof Monster));
        }

        public static DefaultAttributeContainer.Builder createSnowGolemAttributes() {
            return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
        }

        @Override
        public boolean hurtByWater() {
            return false;
        }

        @Override
        public void tickMovement() {
            super.tickMovement();
            if (!this.getWorld().isClient) {
                if (this.getWorld().getBiome(this.getBlockPos()).isIn(BiomeTags.SNOW_GOLEM_MELTS)) {
                    this.damage(this.getDamageSources().onFire(), 1.0f);
                }
                if (!this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    return;
                }
                BlockState blockState = Blocks.SNOW.getDefaultState();
                for (int i = 0; i < 4; ++i) {
                    int j = MathHelper.floor(this.getX() + (double)((float)(i % 2 * 2 - 1) * 0.25f));
                    int k = MathHelper.floor(this.getY());
                    int l = MathHelper.floor(this.getZ() + (double)((float)(i / 2 % 2 * 2 - 1) * 0.25f));
                    BlockPos blockPos = new BlockPos(j, k, l);
                    if (!this.getWorld().getBlockState(blockPos).isAir() || !blockState.canPlaceAt(this.getWorld(), blockPos)) continue;
                    this.getWorld().setBlockState(blockPos, blockState);
                    this.getWorld().emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(this, blockState));
                }
            }
        }

        @Override
        public void attack(LivingEntity target, float pullProgress) {
            PackedSnowballEntity packedSnowballEntity = new PackedSnowballEntity(this, this.getWorld());
            double d = target.getEyeY() - (double) 1.1f;
            double e = target.getX() - this.getX();
            double f = d - packedSnowballEntity.getY();
            double g = target.getZ() - this.getZ();
            double h = Math.sqrt(e * e + g * g) * (double) 0.2f;
            packedSnowballEntity.setVelocity(e, f + h, g, 1.6f, 12.0f);
            this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 0.4f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.getWorld().spawnEntity(packedSnowballEntity);
        }

        @Override
        protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
            super.dropEquipment(source, lootingMultiplier, allowDrops);
            int droppedSnowballs = this.random.nextInt(1);
            for (int i = 0; i < droppedSnowballs + 1; i++) {
                this.dropItem(ModItems.PACKED_SNOWBALL);
            }
        }
    }


