package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlockEntity;
import dev.sterner.mineinabyss.common.item.StarCompassItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.lodestar.lodestone.systems.multiblock.MultiBlockItem;

public interface MIAItems {
    DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineInAbyss.MODID);

    RegistryObject<StarCompassItem> STAR_COMPASS = ITEMS.register("star_compass", () -> new StarCompassItem(settings()));
    RegistryObject<Item> CRADLE_OF_DESIRE = ITEMS.register("cradle_of_desire", () -> new Item(settings()));
    RegistryObject<Item> THOUSAND_MEN_PINS = ITEMS.register("thousand_men_pins", () -> new Item(settings()));
    RegistryObject<Item> LIFE_REVERBERATING_STONE = ITEMS.register("life_reverberating_stone", () -> new Item(settings()));


    RegistryObject<Item> FLESH = ITEMS.register("flesh", () -> new BlockItem(MIABlocks.FLESH.get(), settings()));
    RegistryObject<Item> MARBLE = ITEMS.register("marble", () -> new BlockItem(MIABlocks.MARBLE.get(), settings()));

    RegistryObject<Item> CURSE_WARDING_BOX =
            ITEMS.register("curse_warding_box",
                    () -> new MultiBlockItem(
                            MIABlocks.CURSE_WARDING_BOX.get(),
                            new Item.Properties(),
                            CurseWardingBoxBlockEntity.STRUCTURE)
            );


    private static Item.Properties settings() {
        return new Item.Properties();
    }
}
