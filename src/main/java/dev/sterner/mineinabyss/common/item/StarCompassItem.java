package dev.sterner.mineinabyss.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.handlers.screenparticle.ParticleEmitterHandler;
import team.lodestar.lodestone.setup.LodestoneScreenParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.ScreenParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;
import team.lodestar.lodestone.systems.particle.screen.ScreenParticleHolder;

import java.awt.*;

public class StarCompassItem extends Item implements ParticleEmitterHandler.ItemParticleSupplier {
    public StarCompassItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void spawnEarlyParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        spawnParticles(target, level, partialTick, stack, x, y);
    }

    @Override
    public void spawnLateParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        spawnParticles(target, level, partialTick, stack, x, y);
    }

    public void spawnParticles(ScreenParticleHolder target, Level level, float partialTick, ItemStack stack, float x, float y) {
        float time = level.getGameTime() + partialTick;
        Color firstColor = new Color(15712278);
        Color secondColor = new Color(4607909);
        float alphaMultiplier = 0.5f;
        final int yOffset = 0;
        final int xOffset = 0;
        final SpinParticleData.SpinParticleDataBuilder spinDataBuilder = SpinParticleData.create(0, 1).setSpinOffset(0.025f * time % 6.28f).setEasing(Easing.EXPO_IN_OUT);
        ScreenParticleBuilder.create(LodestoneScreenParticleRegistry.STAR, target)
                .setTransparencyData(GenericParticleData.create(0.09f * alphaMultiplier, 0f).setEasing(Easing.QUINTIC_IN).build())
                .setScaleData(GenericParticleData.create((float) (1.5f + Math.sin(time * 0.1f) * 0.125f), 0).build())
                .setColorData(ColorParticleData.create(firstColor, secondColor).setCoefficient(1.25f).build())
                .setLifetime(6)
                .setRandomOffset(0.05f)
                .setSpinData(spinDataBuilder.build())
                .spawnOnStack(xOffset, yOffset)
                .setScaleData(GenericParticleData.create((float) (1.4f - Math.sin(time * 0.075f) * 0.125f), 0).build())
                .setColorData(ColorParticleData.create(secondColor, firstColor).build())
                .setSpinData(spinDataBuilder.setSpinOffset(0.785f - 0.01f * time % 6.28f).build())
                .spawnOnStack(xOffset, yOffset);
    }
}
