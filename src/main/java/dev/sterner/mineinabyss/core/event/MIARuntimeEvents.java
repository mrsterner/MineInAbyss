package dev.sterner.mineinabyss.core.event;

import dev.sterner.mineinabyss.capability.MIALivingEntityDataCapability;
import dev.sterner.mineinabyss.capability.MIAWorldDataCapability;
import dev.sterner.mineinabyss.common.cradle.CradleManager;
import dev.sterner.mineinabyss.common.curse.CurseManager;
import dev.sterner.mineinabyss.compat.terrablender.MIARegion;
import dev.sterner.mineinabyss.core.listener.MeatToEntityDataReloadListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;


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
        CurseManager.tick(event);
        CradleManager.tick(event);
        MIALivingEntityDataCapability.tick(event);
    }

    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        MeatToEntityDataReloadListener.register(event);
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        MIALivingEntityDataCapability.syncEntityCapability(event);
    }

    @SubscribeEvent
    public static void dropEvent(LivingDropsEvent livingDropsEvent) {
        MIALivingEntityDataCapability.onDeath(livingDropsEvent);
    }

    @SubscribeEvent
    public void onParallelDispatched(ParallelDispatchEvent event) {
        event.enqueueWork(() -> {
            try {
                Class<?> aClass = Class.forName("terrablender.api.Region");
                if (aClass != null) {
                    try {
                        Class<?> clazz = Class.forName("dev.sterner.mineinabyss.compat.terrablender.MIARegion");
                        ((MIARegion) clazz.getConstructor().newInstance()).init(event);
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
