package dev.sterner.mineinabyss.capability;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.abyss.Abyss;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.network.PacketDistributor;
import team.lodestar.lodestone.capability.LodestoneWorldDataCapability;
import team.lodestar.lodestone.systems.capability.LodestoneCapability;
import team.lodestar.lodestone.systems.capability.LodestoneCapabilityProvider;

import java.util.ArrayList;
import java.util.List;

public class MIAWorldDataCapability implements LodestoneCapability {

    private List<Abyss> abyssList = new ArrayList<>();

    public static Capability<MIAWorldDataCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public MIAWorldDataCapability() {
    }

    public void addAbyss(Abyss abyss){
        abyssList.add(abyss);
        //TODO sync?
    }


    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(MIAWorldDataCapability.class);
    }

    public static void attachWorldCapability(AttachCapabilitiesEvent<Level> event) {
        final MIAWorldDataCapability capability = new MIAWorldDataCapability();
        event.addCapability(MineInAbyss.id("world_data"), new LodestoneCapabilityProvider<>(MIAWorldDataCapability.CAPABILITY, () -> capability));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

    public static LazyOptional<MIAWorldDataCapability> getCapabilityOptional(Level level) {
        return level.getCapability(CAPABILITY);
    }

}
