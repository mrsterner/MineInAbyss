package dev.sterner.mineinabyss.client.model;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlockEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;

public class CurseWardingBoxBlockEntityModel extends GeoModel<CurseWardingBoxBlockEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation(MineInAbyss.MODID, "geo/curse_warding_box.geo.json");
    private static final ResourceLocation TEXTURE = new ResourceLocation(MineInAbyss.MODID, "textures/block/curse_warding_box.png");
    private static final ResourceLocation ANIMATION = new ResourceLocation(MineInAbyss.MODID, "animations/curse_warding_box.animation.json");

    @Override
    public ResourceLocation getModelResource(CurseWardingBoxBlockEntity curseWardingBoxBlockEntity) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(CurseWardingBoxBlockEntity curseWardingBoxBlockEntity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(CurseWardingBoxBlockEntity curseWardingBoxBlockEntity) {
        return ANIMATION;
    }
}
