package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public interface MIATags {
    TagKey<EntityType<?>> CURSE_SUSCEPTIBLE = TagKey.create(Registries.ENTITY_TYPE,
            MineInAbyss.id("curse_susceptible"));
}
