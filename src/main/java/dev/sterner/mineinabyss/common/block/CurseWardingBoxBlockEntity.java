package dev.sterner.mineinabyss.common.block;

import dev.sterner.mineinabyss.common.util.Constants;
import dev.sterner.mineinabyss.core.listener.MeatToEntityDataReloadListener;
import dev.sterner.mineinabyss.registry.MIABlockEntityTypes;
import dev.sterner.mineinabyss.registry.MIABlocks;
import mod.azure.azurelib.animatable.GeoBlockEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import team.lodestar.lodestone.systems.multiblock.MultiBlockCoreEntity;
import team.lodestar.lodestone.systems.multiblock.MultiBlockStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CurseWardingBoxBlockEntity extends MultiBlockCoreEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    private ItemStack itemStack = ItemStack.EMPTY;
    @Deprecated
    private EntityType<?> entityType;
    private boolean isOpen = false;
    private int progress = 0;

    public static final Supplier<MultiBlockStructure> STRUCTURE = () ->
            MultiBlockStructure.of(genStruct(MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState(), new ArrayList<>())
                    .toArray(MultiBlockStructure.StructurePiece[]::new));

    public static List<MultiBlockStructure.StructurePiece> genStruct(BlockState state, List<MultiBlockStructure.StructurePiece> list) {
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                for (int z = 0; z <= 1; z++) {
                    list.add(new MultiBlockStructure.StructurePiece(x, y, z, state));
                }
            }
        }

        return list;
    }

    @Override
    public void tick() {
        super.tick();
        if (!itemStack.isEmpty()) {
            progress++;
            if (progress > 20 * 10) {//TODO not hardcode this

                EntityType<?> entityType = MeatToEntityDataReloadListener.getEntity(itemStack.getItem());
                if (entityType != null && getLevel() != null) {
                    Entity entity = entityType.create(getLevel());
                    if (entity != null) {
                        entity.setPos(getBlockPos().getCenter().add(0,2,0));
                        getLevel().addFreshEntity(entity);
                    }
                }

                itemStack = ItemStack.EMPTY;
                progress = 0;
            }
        }

    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        for (Map.Entry<ResourceLocation, MeatToEntityDataReloadListener.MeatData> i : MeatToEntityDataReloadListener.MEAT_DATA.entrySet()) {
            System.out.println(i.getKey());
            System.out.println(i.getValue().items.stream().toList());
        }

        return super.onUse(player, hand);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        pTag.putBoolean(Constants.Nbt.OPEN, this.isOpen);

        if (!this.itemStack.isEmpty()) {
            pTag.put(Constants.Nbt.ITEM, this.itemStack.save(new CompoundTag()));
        }

        if (this.entityType != null) {
            pTag.putString(Constants.Nbt.ENTITY, ForgeRegistries.ENTITY_TYPES.getKey(this.entityType).toString());
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        this.isOpen = pTag.getBoolean(Constants.Nbt.OPEN);

        CompoundTag itemTag = pTag.getCompound(Constants.Nbt.ITEM);
        if (!itemTag.isEmpty()) {
            this.itemStack = ItemStack.of(itemTag);
        }

        if (pTag.contains(Constants.Nbt.ENTITY)) {
            this.entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(pTag.getString(Constants.Nbt.ENTITY)));
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(2);
    }

    public CurseWardingBoxBlockEntity(BlockEntityType<?> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
    }

    public CurseWardingBoxBlockEntity(BlockPos pos, BlockState state) {
        this(MIABlockEntityTypes.CURSE_WARDING_BOX.get(), STRUCTURE.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (getLevel().getDayTime() > 23000 || getLevel().getDayTime() < 13000) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("opening"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenPlay("closed"));
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}