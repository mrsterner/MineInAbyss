package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface MIABlockEntityTypes {
    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MineInAbyss.MODID);

    RegistryObject<BlockEntityType<CurseWardingBoxBlockEntity>> CURSE_WARDING_BOX =
            BLOCK_ENTITY_TYPES.register("curse_warding_box",
                    () -> BlockEntityType.Builder.of(
                            CurseWardingBoxBlockEntity::new,
                            MIABlocks.CURSE_WARDING_BOX.get()
                    ).build(null)
            );

}
