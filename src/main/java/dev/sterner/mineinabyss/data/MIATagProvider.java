package dev.sterner.mineinabyss.data;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.registry.MIATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MIATagProvider {
    public static class MIAEntityTypes extends EntityTypeTagsProvider {

        public MIAEntityTypes(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pOutput, pProvider, MineInAbyss.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            super.addTags(pProvider);
            this.tag(MIATags.CURSE_SUSCEPTIBLE)
                    .add(
                            EntityType.VILLAGER,
                            EntityType.ZOMBIE_VILLAGER,
                            EntityType.PLAYER,
                            EntityType.PIGLIN,
                            EntityType.PIGLIN_BRUTE,
                            EntityType.ZOMBIFIED_PIGLIN,
                            EntityType.WANDERING_TRADER
                    );
        }
    }

    public static class MIABlocks extends BlockTagsProvider {
        public MIABlocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, MineInAbyss.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

        }
    }

    public static class MIAItems extends ItemTagsProvider {
        public MIAItems(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
            super(pOutput, pLookupProvider, pBlockTags, MineInAbyss.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

        }
    }
}
