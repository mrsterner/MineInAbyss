package dev.sterner.mineinabyss.common.block;

import dev.sterner.mineinabyss.registry.MIABlockEntityTypes;
import dev.sterner.mineinabyss.registry.MIABlocks;
import mod.azure.azurelib.animatable.GeoBlockEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import team.lodestar.lodestone.systems.multiblock.MultiBlockCoreEntity;
import team.lodestar.lodestone.systems.multiblock.MultiBlockStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CurseWardingBoxBlockEntity extends MultiBlockCoreEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public static final Supplier<MultiBlockStructure> STRUCTURE = () ->
            MultiBlockStructure.of(genStruct(MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState())
                    .toArray(MultiBlockStructure.StructurePiece[]::new));

    public static List<MultiBlockStructure.StructurePiece> genStruct(BlockState state){
        List<MultiBlockStructure.StructurePiece> list = new ArrayList<>();

        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                for (int z = 0; z <= 1; z++) {
                    list.add(new MultiBlockStructure.StructurePiece(x, y ,z, state));
                }
            }
        }

        return list;
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

    //TODO add itemEntity and mobEntity storage

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (getLevel().getDayTime() > 23000 || getLevel().getDayTime() < 13000) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("opening"));
            }
            else {
                return state.setAndContinue(RawAnimation.begin().thenPlay("closed"));
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
