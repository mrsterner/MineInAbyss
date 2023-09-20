package dev.sterner.mineinabyss.event;

import dev.sterner.mineinabyss.client.gui.hud.CurseHudRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MIAClientSetupEvent {


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), "curse", (gui, poseStack, partialTick, width, height) ->
                CurseHudRenderer.renderCurseHud(gui, poseStack, width, height));

    }
}
