package dev.sterner.mineinabyss.data;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.registry.MIAItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import team.lodestar.lodestone.systems.datagen.ItemModelSmithTypes;
import team.lodestar.lodestone.systems.datagen.itemsmith.AbstractItemModelSmith;
import team.lodestar.lodestone.systems.datagen.providers.LodestoneItemModelProvider;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class MIAItemModelProvider extends LodestoneItemModelProvider {
    public MIAItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MineInAbyss.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Mine in Abyss Item Models";
    }

    @Override
    protected void registerModels() {
        Set<Supplier<Item>> items = new HashSet<>(MIAItems.ITEMS.getEntries());

        items.removeIf(i -> i.get() instanceof BlockItem);

        AbstractItemModelSmith.ItemModelSmithData data = new AbstractItemModelSmith.ItemModelSmithData(this, items::remove);


        //Everything which is left in "items" will get generated
        ItemModelSmithTypes.GENERATED_ITEM.act(data, items);
    }
}
