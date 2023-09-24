package dev.sterner.mineinabyss.client.renderer.block;

import dev.sterner.mineinabyss.client.model.CurseWardingBoxBlockEntityModel;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlockEntity;
import mod.azure.azurelib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class CurseWardingBoxBlockEntityRenderer extends GeoBlockRenderer<CurseWardingBoxBlockEntity> {
    public CurseWardingBoxBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(new CurseWardingBoxBlockEntityModel());
    }

    //TODO add item renderer on bone "item"
}
