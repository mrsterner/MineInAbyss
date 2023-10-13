package dev.sterner.mineinabyss.compat.terrablender;

import com.mojang.datafixers.util.Pair;
import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.registry.MIABiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.function.Consumer;

public class MIARegion extends Region {
    public MIARegion() {
        super(MineInAbyss.id("biome_provider"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        mapper.accept(Pair.of(BiomeClimateHandler.LAYER_1, MIABiomes.LAYER_1));
    }

    public void init(ParallelDispatchEvent event) {
        event.enqueueWork(() -> Regions.register(new MIARegion()));
    }
}
