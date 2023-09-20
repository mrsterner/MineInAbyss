package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.FleshBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface MIABlocks {
    DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineInAbyss.MODID);

    RegistryObject<Block> MARBLE = BLOCKS.register("marble", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    RegistryObject<Block> FLESH = BLOCKS.register("flesh", () -> new FleshBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)));
}
