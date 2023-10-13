package dev.sterner.mineinabyss.data;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.registry.MIABlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.datagen.BlockStateSmithTypes;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneBlockStateProvider;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneItemModelProvider;
import team.lodestar.lodestone.systems.datagen.statesmith.AbstractBlockStateSmith;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static dev.sterner.mineinabyss.registry.MIABlocks.*;

public class MIABlockStateProvider extends LodestoneBlockStateProvider {
    public MIABlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper, LodestoneItemModelProvider itemModelProvider) {
        super(output, MineInAbyss.MODID, exFileHelper, itemModelProvider);
    }

    @Override
    public @NotNull String getName() {
        return "Mine in Abyss BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Supplier<Block>> blocks = new HashSet<>(MIABlocks.BLOCKS.getEntries());

        AbstractBlockStateSmith.StateSmithData data = new AbstractBlockStateSmith.StateSmithData(this, blocks::remove);

        BlockStateSmithTypes.FULL_BLOCK.act(data,
                MARBLE
        );

        MIABlockStateSmithTypes.VARIABLE_BLOCK.act(data, FLESH);
    }


}
