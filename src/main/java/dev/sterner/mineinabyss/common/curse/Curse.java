package dev.sterner.mineinabyss.common.curse;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class Curse {
    public CurseIntensity intensity;

    public Curse(CurseIntensity curseIntensity) {
        this.intensity = curseIntensity;
    }

    public CurseIntensity getIntensity() {
        return intensity;
    }

    public void tickEffect(Level world, LivingEntity livingEntity) {

    }

    public void tickClientEffect(ClientLevel clientWorld) {

    }

    public void tickAscensionEffectClient(ClientLevel clientWorld) {

    }

    public void tickAscensionEffect(Level world, LivingEntity livingEntity) {

    }
}