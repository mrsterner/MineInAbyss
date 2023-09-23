package dev.sterner.mineinabyss.common.block;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.systems.multiblock.MultiblockComponentBlock;

public class CurseWardingBoxComponent extends MultiblockComponentBlock {
    public CurseWardingBoxComponent(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }
}
