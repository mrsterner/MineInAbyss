package dev.sterner.mineinabyss.common.curse.curses;

import dev.sterner.mineinabyss.common.curse.Curse;
import dev.sterner.mineinabyss.common.curse.CurseIntensity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class WeakCurse extends Curse {
    public WeakCurse(CurseIntensity curseIntensity) {
        super(curseIntensity);
    }

    @Override
    public void tickAscensionEffect(Level level, LivingEntity livingEntity) {
        super.tickAscensionEffect(level, livingEntity);

    }
}
