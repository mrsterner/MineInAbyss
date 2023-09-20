package dev.sterner.mineinabyss.common.curse;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class Curse {
    public CurseIntensity intensity;

    public Curse(CurseIntensity curseIntensity){
        this.intensity = curseIntensity;
    }

    public CurseIntensity getIntensity(){
        return intensity;
    }

    public void tickEffect(Level world, LivingEntity livingEntity){

    }

    public void tickClient(ClientLevel clientWorld){

    }

    public void ascensionEffectClient(ClientLevel clientWorld){

    }

    public void ascensionEffect(Level world, LivingEntity livingEntity){

    }
}