package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface MIAItems {
    DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineInAbyss.MODID);

    RegistryObject<Item> STAR_COMPASS = ITEMS.register("star_compass", () -> new Item(settings()));
    RegistryObject<Item> CRADLE_OF_DESIRE = ITEMS.register("cradle_of_desire", () -> new Item(settings()));
    RegistryObject<Item> THOUSAND_MEN_PINS = ITEMS.register("thousand_men_pins", () -> new Item(settings()));


    RegistryObject<Item> FLESH = ITEMS.register("flesh", () -> new BlockItem(MIABlocks.FLESH.get(), settings()));
    RegistryObject<Item> MARBLE = ITEMS.register("marble", () -> new BlockItem(MIABlocks.MARBLE.get(), settings()));

    private static Item.Properties settings(){
        return new Item.Properties();
    }
}
