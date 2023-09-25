package dev.sterner.mineinabyss.core.event;

import dev.sterner.mineinabyss.client.gui.hud.CurseHudRenderer;
import dev.sterner.mineinabyss.client.renderer.block.CurseWardingBoxBlockEntityRenderer;
import dev.sterner.mineinabyss.registry.MIABlockEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MIAClientSetupEvent {


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), "curse", (gui, poseStack, partialTick, width, height) ->
                CurseHudRenderer.renderCurseHud(gui, poseStack, width, height));

    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(MIABlockEntityTypes.CURSE_WARDING_BOX.get(), CurseWardingBoxBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {

    }
}
