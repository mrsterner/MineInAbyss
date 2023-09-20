package dev.sterner.mineinabyss.event;

import dev.sterner.mineinabyss.capability.MIALivingEntityDataCapability;
import dev.sterner.mineinabyss.capability.MIAWorldDataCapability;
import dev.sterner.mineinabyss.common.curse.CurseManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class MIARuntimeEvents {

    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        MIALivingEntityDataCapability.attachEntityCapability(event);
    }

    @SubscribeEvent
    public static void attachWorldCapability(AttachCapabilitiesEvent<Level> event) {
        MIAWorldDataCapability.attachWorldCapability(event);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        CurseManager.tickServer(event);
    }
}
