package dev.sterner.mineinabyss.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class CurseWardingBoxBlock<T extends CurseWardingBoxBlockEntity> extends LodestoneEntityBlock<T> {
    public CurseWardingBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }


}
