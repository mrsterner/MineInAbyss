package dev.sterner.mineinabyss.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public interface MIACarvers {

    ResourceKey<ConfiguredWorldCarver<?>> ABYSS = createKey("abyss");

    static ResourceKey<ConfiguredWorldCarver<?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(pName));
    }

    static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> pContext) {
        HolderGetter<Block> holdergetter = pContext.lookup(Registries.BLOCK);
        pContext.register(ABYSS, WorldCarver.CANYON.configured(
                new CanyonCarverConfiguration(
                        0.01F,
                        UniformHeight.of(VerticalAnchor.absolute(10),
                                VerticalAnchor.absolute(67)),
                        ConstantFloat.of(3.0F),
                        VerticalAnchor.aboveBottom(8),
                        CarverDebugSettings.of(
                                false,
                                Blocks.WARPED_BUTTON.defaultBlockState()),
                        holdergetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                        UniformFloat.of(-0.125F, 0.125F),
                        new CanyonCarverConfiguration.CanyonShapeConfiguration(
                                UniformFloat.of(
                                        0.75F,
                                        1.0F),
                                TrapezoidFloat.of(0.0F, 6.0F, 2.0F),
                                3,
                                UniformFloat.of(
                                        0.75F,
                                        1.0F),
                                1.0F,
                                0.0F)
                )
        ));
     }
}
