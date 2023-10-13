package dev.sterner.mineinabyss.data;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MIABiomeProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, MIAConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, MIAPlacedFeatures::bootstrap)
            .add(Registries.BIOME, MIABiomes::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, MIABiomeModifiers::bootstrap)
            .add(Registries.CONFIGURED_CARVER, MIACarvers::bootstrap);

    public MIABiomeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MineInAbyss.MODID));
    }
}
