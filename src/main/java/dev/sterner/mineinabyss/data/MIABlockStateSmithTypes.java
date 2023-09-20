package dev.sterner.mineinabyss.data;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.FleshBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import team.lodestar.lodestone.systems.datagen.statesmith.BlockStateSmith;

public class MIABlockStateSmithTypes {
    public static BlockStateSmith<Block> VARIABLE_BLOCK = new BlockStateSmith<>(Block.class, (block, provider) -> {
        String name = provider.getBlockName(block);

        ModelFile var0 = provider.models().cubeAll(name, MineInAbyss.id("block/" + name ));
        ModelFile var1 = provider.models().cubeAll(name + "_1", MineInAbyss.id("block/" + name + "_1"));
        ModelFile var2 = provider.models().cubeAll(name + "_2", MineInAbyss.id("block/" + name + "_2"));

        provider.getVariantBuilder(block)
                .partialState().with(FleshBlock.VARIANTS, 1).modelForState().modelFile(var0).addModel()
                .partialState().with(FleshBlock.VARIANTS, 2).modelForState().modelFile(var1).addModel()
                .partialState().with(FleshBlock.VARIANTS, 3).modelForState().modelFile(var2).addModel();

    });
}
