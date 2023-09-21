package dev.sterner.mineinabyss.event;

import dev.sterner.mineinabyss.client.gui.hud.CurseHudRenderer;
import dev.sterner.mineinabyss.registry.MIAItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.handlers.ThrowawayBlockDataHandler;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.setup.LodestoneParticleRegistry;
import team.lodestar.lodestone.setup.LodestoneScreenParticleRegistry;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MIAClientSetupEvent {


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), "curse", (gui, poseStack, partialTick, width, height) ->
                CurseHudRenderer.renderCurseHud(gui, poseStack, width, height));

    }
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ParticleEmitterHandler.registerParticleEmitters(event);

        var i = MIAItems.STAR_COMPASS.get();
        ParticleEmitterHandler.ItemParticleSupplier emitter = (ParticleEmitterHandler.ItemParticleSupplier) i;
        ParticleEmitterHandler.registerItemParticleEmitter(i, emitter);
    }
}
