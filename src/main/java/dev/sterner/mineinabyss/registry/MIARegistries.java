package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.curse.Curse;
import dev.sterner.mineinabyss.common.curse.CurseIntensity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public interface MIARegistries {

    ResourceKey<Registry<Curse>> CURSE = ResourceKey.createRegistryKey(MineInAbyss.id("curse"));

    DeferredRegister<Curse> CURSE_DEFERRED_REGISTER = DeferredRegister.create(MIARegistries.CURSE, MineInAbyss.MODID);

    Supplier<IForgeRegistry<Curse>> CURSE_REGISTRY = MIARegistries.makeSyncedRegistry(CURSE_DEFERRED_REGISTER);

    RegistryObject<Curse> NONE = MIARegistries.CURSE_DEFERRED_REGISTER.register("none", () -> new Curse(CurseIntensity.NONE));

    private static <T> Supplier<IForgeRegistry<T>> makeSyncedRegistry(DeferredRegister<T> deferredRegister) {
        return deferredRegister.makeRegistry(() -> new RegistryBuilder<T>().disableSaving());
    }
}
