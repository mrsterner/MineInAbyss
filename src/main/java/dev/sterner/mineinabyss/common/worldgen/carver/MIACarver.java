package dev.sterner.mineinabyss.common.worldgen.carver;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

public class MIACarver<C extends CarverConfiguration> {



    public static final WorldCarver<AbyssCarverConfiguration> ABYSS = register("abyss", new AbyssCarver(AbyssCarverConfiguration.CODEC));

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> F register(String pKey, F pCarver) {
        return Registry.register(BuiltInRegistries.CARVER, pKey, pCarver);
    }
}
