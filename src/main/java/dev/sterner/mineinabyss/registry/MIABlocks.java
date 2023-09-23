package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlock;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxComponent;
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

    RegistryObject<Block> CURSE_WARDING_BOX =
            BLOCKS.register("curse_warding_box",
                    () -> new CurseWardingBoxBlock<>(
                            BlockBehaviour.Properties.of()
                                    .noOcclusion()
                    ).setBlockEntity(MIABlockEntityTypes.CURSE_WARDING_BOX)
            );
    RegistryObject<Block> CURSE_WARDING_BOX_COMPONENT =
            BLOCKS.register("curse_warding_box_component",
                    () -> new CurseWardingBoxComponent(
                            BlockBehaviour.Properties.copy(CURSE_WARDING_BOX.get())
                                    .lootFrom(CURSE_WARDING_BOX)
                                    .noOcclusion()
                    )
            );
}
