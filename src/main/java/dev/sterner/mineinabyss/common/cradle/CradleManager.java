package dev.sterner.mineinabyss.common.cradle;

import dev.sterner.mineinabyss.capability.MIALivingEntityDataCapability;
import dev.sterner.mineinabyss.common.util.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CradleManager {
    private boolean isHost = false;

    public CradleManager() {

    }

    public static void tick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        MIALivingEntityDataCapability capability = MIALivingEntityDataCapability.getCapability(livingEntity);
        CradleManager cradleManager = capability.cradleManager;
        if (cradleManager.isHost()) {
            //TODO Do Tick
        }
    }

    public void writeCurseToNbt(CompoundTag tag) {
        tag.putBoolean(Constants.Nbt.COOLDOWN, isHost());
    }

    public void readCurseFromNbt(CompoundTag tag) {
        setHost(tag.getBoolean(Constants.Nbt.HOST));
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }
}
