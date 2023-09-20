package dev.sterner.mineinabyss.common.abyss;


import dev.sterner.mineinabyss.common.curse.Curse;
import dev.sterner.mineinabyss.common.util.Constants;
import dev.sterner.mineinabyss.registry.MIARegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class AbyssLayer {

    public int bottomY;
    public int topY;
    public Curse curse;

    public AbyssLayer(int topY, int bottomY, Curse curse) {
        this.topY = topY;
        this.bottomY = bottomY;
        this.curse = curse;
    }

    public static void writeAbyssLayerToNbt(AbyssLayer layerInfo, CompoundTag nbt) {
        nbt.putInt(Constants.Nbt.TOP_Y, layerInfo.topY);
        nbt.putInt(Constants.Nbt.BOTTOM_Y, layerInfo.bottomY);
        nbt.putString(Constants.Nbt.CURSE_INTENSITY, MIARegistries.CURSE_REGISTRY.get().getDelegateOrThrow(layerInfo.curse).get().toString());
    }

    public static void readAbyssLayerToNbt(AbyssLayer layerInfo, CompoundTag nbt) {
        layerInfo.bottomY = nbt.getInt(Constants.Nbt.BOTTOM_Y);
        layerInfo.topY = nbt.getInt(Constants.Nbt.TOP_Y);
        layerInfo.curse = MIARegistries.CURSE_REGISTRY.get().getDelegateOrThrow(new ResourceLocation(nbt.getString(Constants.Nbt.CURSE))).get();
    }
}