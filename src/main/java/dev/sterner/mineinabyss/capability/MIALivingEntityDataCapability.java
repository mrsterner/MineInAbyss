package dev.sterner.mineinabyss.capability;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.curse.CurseManager;
import dev.sterner.mineinabyss.common.networking.SyncLivingCapabilityDataPacket;
import dev.sterner.mineinabyss.common.util.Constants;
import dev.sterner.mineinabyss.registry.MIAPackets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.network.PacketDistributor;
import team.lodestar.lodestone.systems.capability.LodestoneCapability;
import team.lodestar.lodestone.systems.capability.LodestoneCapabilityProvider;

public class MIALivingEntityDataCapability implements LodestoneCapability {

    public CurseManager manager = new CurseManager();

    public boolean isRevived = false;
    public long revivedTimer = -1;

    public static Capability<MIALivingEntityDataCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public MIALivingEntityDataCapability() {

    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(MIALivingEntityDataCapability.class);
    }

    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof LivingEntity) {
            final MIALivingEntityDataCapability capability = new MIALivingEntityDataCapability();
            event.addCapability(MineInAbyss.id("living_data"), new LodestoneCapabilityProvider<>(MIALivingEntityDataCapability.CAPABILITY, () -> capability));
        }
    }

    /**
     * When a mob is spawned with the curse warding box, set its life to be temporary.
     *
     * @param livingEntity revived entity
     * @param time time in ticks the mob will stay alive
     */
    public static void setRevived(LivingEntity livingEntity, long time){
        MIALivingEntityDataCapability capability = getCapability(livingEntity);
        capability.isRevived = true;
        capability.revivedTimer = time;
        if (livingEntity.level() instanceof ServerLevel) {
            sync(livingEntity);
        }
    }

    /**
     * removed or resets the revived status, not killing the entity in the process
     *
     * @param livingEntity entity to remove status from
     */
    public static void removeRevived(LivingEntity livingEntity){
        MIALivingEntityDataCapability capability = getCapability(livingEntity);
        capability.isRevived = false;
        capability.revivedTimer = -1;
    }

    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Mob) {
            MIALivingEntityDataCapability capability = getCapability(livingEntity);
            if (capability.isRevived) {
                if (capability.revivedTimer > 0) {
                    capability.revivedTimer--;
                } else {
                    //TODO change to curse damage type
                    removeRevived(livingEntity);
                    livingEntity.hurt(livingEntity.damageSources().magic(), Float.MAX_VALUE);
                }
                if (livingEntity.level() instanceof ServerLevel) {
                    sync(livingEntity);
                }
            }
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(Constants.Nbt.IS_REVIVED, this.isRevived);
        tag.putLong(Constants.Nbt.REVIVED_TIMER, this.revivedTimer);

        manager.writeCurseToNbt(manager, tag);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.isRevived = tag.getBoolean(Constants.Nbt.IS_REVIVED);
        this.revivedTimer = tag.getInt(Constants.Nbt.REVIVED_TIMER);

        manager.readCurseFromNbt(manager, tag);
    }

    public static void syncEntityCapability(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof LivingEntity livingEntity) {
            if (livingEntity.level() instanceof ServerLevel) {
                MIALivingEntityDataCapability.sync(livingEntity);
            }
        }
    }

    public static void sync(LivingEntity entity) {
        getCapabilityOptional(entity).ifPresent(
                c -> MIAPackets.MIA_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity),
                        new SyncLivingCapabilityDataPacket(entity.getId(), c.serializeNBT())));
    }

    public static LazyOptional<MIALivingEntityDataCapability> getCapabilityOptional(LivingEntity entity) {
        return entity.getCapability(CAPABILITY);
    }

    public static MIALivingEntityDataCapability getCapability(LivingEntity entity) {
        return entity.getCapability(CAPABILITY).orElse(new MIALivingEntityDataCapability());
    }
}
