package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.curse.Curse;
import dev.sterner.mineinabyss.common.curse.CurseIntensity;
import dev.sterner.mineinabyss.common.curse.curses.MediumCurse;
import dev.sterner.mineinabyss.common.curse.curses.SevereCurse;
import dev.sterner.mineinabyss.common.curse.curses.StrongCurse;
import dev.sterner.mineinabyss.common.curse.curses.WeakCurse;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MIARegistries {

    //Curse key
    private static final ResourceKey<Registry<Curse>> CURSE = ResourceKey.createRegistryKey(MineInAbyss.id("curse"));

    //Curse Registry
    public static final DeferredRegister<Curse> CURSE_DEFERRED_REGISTER = DeferredRegister.create(MIARegistries.CURSE, MineInAbyss.MODID);

    //
    public static final Supplier<IForgeRegistry<Curse>> CURSE_REGISTRY = MIARegistries.makeSyncedRegistry(CURSE_DEFERRED_REGISTER);

    //Curses
    //TODO change new Curse(...) to implementation
    public static final RegistryObject<Curse> NONE = MIARegistries.CURSE_DEFERRED_REGISTER.register("none", () -> new Curse(CurseIntensity.NONE));
    public static final RegistryObject<Curse> WEAK = MIARegistries.CURSE_DEFERRED_REGISTER.register("weak", () -> new WeakCurse(CurseIntensity.WEAK));
    public static final RegistryObject<Curse> MEDIUM = MIARegistries.CURSE_DEFERRED_REGISTER.register("medium", () -> new MediumCurse(CurseIntensity.MEDIUM));
    public static final RegistryObject<Curse> STRONG = MIARegistries.CURSE_DEFERRED_REGISTER.register("strong", () -> new StrongCurse(CurseIntensity.STRONG));
    public static final RegistryObject<Curse> SEVERE = MIARegistries.CURSE_DEFERRED_REGISTER.register("severe", () -> new SevereCurse(CurseIntensity.SEVERE));

    private static <T> Supplier<IForgeRegistry<T>> makeSyncedRegistry(DeferredRegister<T> deferredRegister) {
        return deferredRegister.makeRegistry(() -> new RegistryBuilder<T>().disableSaving());
    }
}
