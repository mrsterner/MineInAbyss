package dev.sterner.mineinabyss.common.cradle;

import dev.sterner.mineinabyss.common.util.Constants;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CradleManager {
    private boolean isHost = false;

    public CradleManager() {

    }

    public static void tick(LivingEvent.LivingTickEvent event) {

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
