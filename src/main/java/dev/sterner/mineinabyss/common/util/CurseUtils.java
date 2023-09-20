package dev.sterner.mineinabyss.common.util;

import dev.sterner.mineinabyss.capability.MIAWorldDataCapability;
import dev.sterner.mineinabyss.common.curse.Curse;
import dev.sterner.mineinabyss.common.curse.CurseManager;
import dev.sterner.mineinabyss.registry.MIARegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class CurseUtils {

    public boolean canBeAbyssChunk(Level world, ChunkPos chunkPos) {
        Optional<MIAWorldDataCapability> worldDataCapabilityOptional = null;//TODO
        if (worldDataCapabilityOptional.isPresent()) {
            MIAWorldDataCapability worldAbyssComponent = worldDataCapabilityOptional.get();
            boolean bl = tooCloseAbyss(worldAbyssComponent, chunkPos);
            if (!bl) {
                tryGenerateAbyss(worldAbyssComponent, chunkPos);
            }
        }
        return false;
    }

    private void tryGenerateAbyss(MIAWorldDataCapability world, ChunkPos chunkPos) {

    }

    private boolean tooCloseAbyss(MIAWorldDataCapability world, ChunkPos chunkPos) {

        return false;//TODO cheat
    }

    //TODO implement
    public static Curse getWorldCurse(Level world, BlockPos blockPos) {
        return MIARegistries.NONE.get();
    }

    //TODO implement
    public static boolean checkIfInCurse(Level world, LivingEntity livingEntity, BlockPos blockPos) {
        return false;
    }

    public static boolean checkAscending(CurseManager manager, LivingEntity livingEntity) {
        if (manager.getTimeSpentOnY() == null) {
            return false;
        }

        if (livingEntity.tickCount - manager.getTimeSpentOnY().age() > 20 * 10) {
            manager.setTimeSpentOnY(livingEntity.getBlockY(), livingEntity.tickCount);
        }

        return livingEntity.getBlockY() - manager.getTimeSpentOnY().y() > 8;
    }

    public static void updateLowestY(CurseManager manager, LivingEntity livingEntity) {
        if (manager.getTimeSpentOnY() == null) {
            manager.setTimeSpentOnY(livingEntity.getBlockY(), livingEntity.tickCount);
        }

        int y = livingEntity.blockPosition().getY();
        if (y < manager.getTimeSpentOnY().y()) {
            manager.setTimeSpentOnY(y, manager.getTimeSpentOnY().age());
        }
    }
}
