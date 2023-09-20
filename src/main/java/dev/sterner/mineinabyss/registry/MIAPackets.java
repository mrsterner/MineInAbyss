package dev.sterner.mineinabyss.registry;

import dev.sterner.mineinabyss.MineInAbyss;
import dev.sterner.mineinabyss.common.networking.SyncLivingCapabilityDataPacket;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = MineInAbyss.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MIAPackets {

    public static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel MIA_CHANNEL = NetworkRegistry.newSimpleChannel(MineInAbyss.id("main"), () -> MIAPackets.PROTOCOL_VERSION, MIAPackets.PROTOCOL_VERSION::equals, MIAPackets.PROTOCOL_VERSION::equals);

    @SubscribeEvent
    public static void registerNetworkStuff(FMLCommonSetupEvent event) {
        int index = 0;

        SyncLivingCapabilityDataPacket.register(MIA_CHANNEL, index++);
    }
}
