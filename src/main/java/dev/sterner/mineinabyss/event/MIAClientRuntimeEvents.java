package dev.sterner.mineinabyss.event;

import dev.sterner.mineinabyss.common.curse.CurseManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MIAClientRuntimeEvents {

    @SubscribeEvent
    public static void renderOverlay(RenderGuiOverlayEvent.Post event) {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void lateRenderTick(TickEvent.RenderTickEvent event) {
    }
}
