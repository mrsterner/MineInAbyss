package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public interface MIABiomes {

    ResourceKey<Biome> LAYER_1 = register("layer_1");

    static void bootstrap(BootstapContext<Biome> bootstapContext) {
        HolderGetter<PlacedFeature> holdergetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        bootstapContext.register(LAYER_1, MIABiomes.layer1(holdergetter, holdergetter1));
    }

    static Biome layer1(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter1) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter1);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);


        Music music = Musics.createGameMusic(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP);
        return biome(0.5F, 0.5F, mobBuilder, biomeBuilder, music, 4159204);
    }

    static Biome biome(float temperature, float downFall, MobSpawnSettings.Builder mobBuilder, BiomeGenerationSettings.Builder generationBuilder, @Nullable Music music, int waterColor) {
        return biome(temperature, downFall, waterColor, 329011, mobBuilder, generationBuilder, music);
    }

    static Biome biome(float temperature, float downFall, int waterColor, int waterFogColor, MobSpawnSettings.Builder mobBuilder, BiomeGenerationSettings.Builder generationBuilder, @Nullable Music music) {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(temperature)
                .downfall(downFall)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(waterColor)
                                .waterFogColor(waterFogColor)
                                .fogColor(12638463)
                                .skyColor(calculateSkyColor(temperature))
                                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                                .backgroundMusic(music).build())
                .mobSpawnSettings(mobBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    static int calculateSkyColor(float temperature) {
        float value = temperature / 3.0F;
        value = Mth.clamp(value, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - value * 0.05F, 0.5F + value * 0.1F, 1.0F);
    }

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, MineInAbyss.id(name));
    }
}
