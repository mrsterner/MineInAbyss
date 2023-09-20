package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.item.ItemWithParticle;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface MIAItems {
    DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineInAbyss.MODID);


    RegistryObject<Item> STAR_COMPASS = ITEMS.register("star_compass", () -> new ItemWithParticle(new Item.Properties()));
    RegistryObject<Item> CRADLE_OF_DESIRE = ITEMS.register("cradle_of_desire", () -> new Item(new Item.Properties()));
    RegistryObject<Item> THOUSAND_MEN_PINS = ITEMS.register("thousand_men_pins", () -> new Item(new Item.Properties()));
}
