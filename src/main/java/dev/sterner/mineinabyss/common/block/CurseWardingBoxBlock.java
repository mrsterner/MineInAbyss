package dev.sterner.mineinabyss.common.block;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.block.LodestoneEntityBlock;

public class CurseWardingBoxBlock<T extends CurseWardingBoxBlockEntity> extends LodestoneEntityBlock<T> {
    public CurseWardingBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
