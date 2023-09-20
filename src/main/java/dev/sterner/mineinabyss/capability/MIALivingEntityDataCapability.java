package dev.sterner.mineinabyss.capability;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.networking.SyncLivingCapabilityDataPacket;
import dev.sterner.mineinabyss.registry.MIAPackets;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.network.PacketDistributor;
import team.lodestar.lodestone.systems.capability.LodestoneCapability;
import team.lodestar.lodestone.systems.capability.LodestoneCapabilityProvider;

public class MIALivingEntityDataCapability implements LodestoneCapability {

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

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {

    }

    public static void sync(LivingEntity entity) {
        getCapabilityOptional(entity).ifPresent(
                c -> MIAPackets.MIA_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity),
                        new SyncLivingCapabilityDataPacket(entity.getId(), c.serializeNBT())));
    }

    public static LazyOptional<MIALivingEntityDataCapability> getCapabilityOptional(LivingEntity entity) {
        return entity.getCapability(CAPABILITY);
    }
}
