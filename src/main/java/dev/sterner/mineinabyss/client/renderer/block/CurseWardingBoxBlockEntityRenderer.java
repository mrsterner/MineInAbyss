package dev.sterner.mineinabyss.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.sterner.mineinabyss.client.model.CurseWardingBoxBlockEntityModel;
import dev.sterner.mineinabyss.common.block.CurseWardingBoxBlockEntity;
import mod.azure.azurelib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;


public class CurseWardingBoxBlockEntityRenderer extends GeoBlockRenderer<CurseWardingBoxBlockEntity> {
    public CurseWardingBoxBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(new CurseWardingBoxBlockEntityModel());
    }

    @Override
    protected void rotateBlock(Direction facing, PoseStack poseStack) {
        switch (facing) {
            case SOUTH:
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                poseStack.translate(-1, 0, -1);
                break;
            case WEST:
                poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
                poseStack.translate(-1, 0, 0);
                break;
            case NORTH:
                poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
                poseStack.translate(0, 0, 0);
                break;
            case EAST:
                poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
                poseStack.translate(0, 0, -1);
                break;
            default:
                break;
        }

    }
    //TODO add item renderer on bone "item"
}
