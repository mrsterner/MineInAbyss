package dev.sterner.mineinabyss.common.block;

import dev.sterner.mineinabyss.registry.MIABlockEntityTypes;
import dev.sterner.mineinabyss.registry.MIABlocks;
import mod.azure.azurelib.animatable.GeoBlockEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.multiblock.MultiBlockCoreEntity;
import team.lodestar.lodestone.systems.multiblock.MultiBlockStructure;

import java.util.function.Supplier;

public class CurseWardingBoxBlockEntity extends MultiBlockCoreEntity implements GeoBlockEntity {

    public static final Supplier<MultiBlockStructure> STRUCTURE = () ->
            (MultiBlockStructure.of(
                    new MultiBlockStructure.StructurePiece(1, 0, 0, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(1, 0, 1, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(0, 0, 1, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(1, 1, 0, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(1, 1, 1, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(0, 1, 1, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState()),
                    new MultiBlockStructure.StructurePiece(0, 1, 0, MIABlocks.CURSE_WARDING_BOX_COMPONENT.get().defaultBlockState())
                    ));


    public CurseWardingBoxBlockEntity(BlockEntityType<?> type, MultiBlockStructure structure, BlockPos pos, BlockState state) {
        super(type, structure, pos, state);
    }

    public CurseWardingBoxBlockEntity(BlockPos pos, BlockState state) {
        this(MIABlockEntityTypes.CURSE_WARDING_BOX.get(), STRUCTURE.get(), pos, state);
    }

    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
