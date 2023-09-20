package dev.sterner.mineinabyss.common.curse;

import dev.sterner.mineinabyss.capability.MIALivingEntityDataCapability;
import dev.sterner.mineinabyss.common.util.Constants;
import dev.sterner.mineinabyss.common.util.CurseUtils;
import dev.sterner.mineinabyss.registry.MIARegistries;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CurseManager {
    private final int MAX_COOLDOWN = 2 * 20;
    private boolean isInCurse = false;
    private boolean wasInCurse = false;
    private boolean isImmune = false;
    private Curse curse = MIARegistries.NONE.get();
    private int checkCooldown = 0;
    private int currentY;
    private TimeSpentOnY timeSpentOnY;

    public CurseManager() {

    }

    public static void tickServer(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if (!(livingEntity instanceof Player)) {
            return;//TODO remove replace with humanoid
        }

        LazyOptional<MIALivingEntityDataCapability> capabilityOptional = MIALivingEntityDataCapability.getCapabilityOptional(livingEntity);
        if (capabilityOptional.isPresent()) {
            CurseManager manager = capabilityOptional.orElse(new MIALivingEntityDataCapability()).manager;

            manager.checkCooldown++;

            manager.tryAddCurse(livingEntity);

            if (manager.isInCurse()) {
                manager.tryUpdateCurseIntensity(livingEntity);

                CurseUtils.updateLowestY(manager, livingEntity);

                //Common
                manager.curse.tickEffect(livingEntity.getCommandSenderWorld(), livingEntity);

                if (CurseUtils.checkAscending(manager, livingEntity)) {
                    manager.curse.tickAscensionEffect(livingEntity.getCommandSenderWorld(), livingEntity);
                }

                //Client
                if (livingEntity.level() instanceof ClientLevel clientWorld) {
                    manager.curse.tickClientEffect(clientWorld);
                    if (CurseUtils.checkAscending(manager, livingEntity)) {
                        manager.curse.tickAscensionEffectClient(clientWorld);
                    }
                }
            }
        }
    }

    private void tryUpdateCurseIntensity(LivingEntity livingEntity) {
        Curse worldCurse = CurseUtils.getWorldCurse(livingEntity.level(), livingEntity.blockPosition());
        if (worldCurse != getCurse() && getCurse().getIntensity().getId() < worldCurse.getIntensity().getId()) {
            setCurse(worldCurse);
        }
    }

    private void tryAddCurse(LivingEntity livingEntity) {
        if (checkCooldown > MAX_COOLDOWN) {
            checkCooldown = 0;
            boolean bl = CurseUtils.checkIfInCurse(livingEntity.level(), livingEntity, livingEntity.blockPosition());
            if (bl != isInCurse()) {
                setInCurse(bl);
            }
        }
    }

    public void writeCurseToNbt(CurseManager manager, CompoundTag nbt) {
        nbt.putInt(Constants.Nbt.COOLDOWN, manager.checkCooldown);
        nbt.putInt(Constants.Nbt.CURRENT_Y, manager.currentY);
        nbt.putBoolean(Constants.Nbt.IMMUNE, manager.isImmune);
        nbt.putBoolean(Constants.Nbt.IS_IN_CURSE, manager.isInCurse);

        if (manager.curse != null) {
            nbt.putString(Constants.Nbt.CURSE_INTENSITY, MIARegistries.CURSE_REGISTRY.get().getDelegateOrThrow(manager.curse).get().toString());
        }

        if (manager.timeSpentOnY != null) {
            nbt.putInt(Constants.Nbt.TIME_Y, manager.timeSpentOnY.y());
            nbt.putLong(Constants.Nbt.TIME_AGE, manager.timeSpentOnY.age());
        }
    }

    public CurseManager readCurseFromNbt(CurseManager manager, CompoundTag nbt) {
        manager.checkCooldown = nbt.getInt(Constants.Nbt.COOLDOWN);
        manager.timeSpentOnY = new TimeSpentOnY(nbt.getInt(Constants.Nbt.TIME_Y), nbt.getLong(Constants.Nbt.TIME_AGE));
        manager.currentY = nbt.getInt(Constants.Nbt.CURRENT_Y);
        manager.isImmune = nbt.getBoolean(Constants.Nbt.IMMUNE);
        manager.isInCurse = nbt.getBoolean(Constants.Nbt.IS_IN_CURSE);
        manager.curse = MIARegistries.CURSE_REGISTRY.get().getDelegateOrThrow(new ResourceLocation(nbt.getString(Constants.Nbt.CURSE))).get();

        return manager;
    }

    public boolean isInCurse() {
        return true;
        //TODO return isInCurse;
    }

    public void setInCurse(boolean inCurse) {
        isInCurse = inCurse;
        sync();
    }

    public Curse getCurse() {
        return curse;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
        sync();
    }

    public boolean isImmune() {
        return isImmune;
    }

    public void setImmune(boolean immune) {
        isImmune = immune;
        sync();
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
        sync();
    }

    public TimeSpentOnY getTimeSpentOnY() {
        return timeSpentOnY;
    }

    public void setTimeSpentOnY(TimeSpentOnY timeSpentOnY) {
        this.timeSpentOnY = timeSpentOnY;
        sync();
    }

    public void setTimeSpentOnY(int y, long age) {
        setTimeSpentOnY(new TimeSpentOnY(y, age));
    }

    public boolean isWasInCurse() {
        return wasInCurse;
    }

    public void setWasInCurse(boolean wasInCurse) {
        this.wasInCurse = wasInCurse;
        sync();
    }

    private void sync() {
        //TODO
    }

    public record TimeSpentOnY(int y, long age) {
    }
}
