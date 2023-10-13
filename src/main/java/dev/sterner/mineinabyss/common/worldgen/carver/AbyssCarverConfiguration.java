package dev.sterner.mineinabyss.common.worldgen.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class AbyssCarverConfiguration extends CarverConfiguration {

    public static final Codec<AbyssCarverConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(CarverConfiguration.CODEC.forGetter((configuration) -> {
            return configuration;
        }), FloatProvider.CODEC.fieldOf("vertical_rotation").forGetter((configuration) -> {
            return configuration.verticalRotation;
        }), AbyssCarverConfiguration.AbyssShapeConfiguration.CODEC.fieldOf("shape").forGetter((configuration) -> {
            return configuration.shape;
        })).apply(instance, AbyssCarverConfiguration::new);
    });

    public final FloatProvider verticalRotation;
    public final AbyssCarverConfiguration.AbyssShapeConfiguration shape;

    public AbyssCarverConfiguration(float pProbability, HeightProvider pY, FloatProvider pYScale, VerticalAnchor pLavaLevel, CarverDebugSettings pDebugSettings, HolderSet<Block> pReplaceable, FloatProvider pVerticalRotation, AbyssCarverConfiguration.AbyssShapeConfiguration pShape) {
        super(pProbability, pY, pYScale, pLavaLevel, pDebugSettings, pReplaceable);
        this.verticalRotation = pVerticalRotation;
        this.shape = pShape;
    }

    public AbyssCarverConfiguration(CarverConfiguration config, FloatProvider p_158981_, AbyssCarverConfiguration.AbyssShapeConfiguration p_158982_) {
        this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, p_158981_, p_158982_);
    }

    public static class AbyssShapeConfiguration {
        public static final Codec<AbyssCarverConfiguration.AbyssShapeConfiguration> CODEC = RecordCodecBuilder.create(instance -> {
            return instance.group(FloatProvider.CODEC.fieldOf("distance_factor").forGetter((configuration) -> {
                return configuration.distanceFactor;
            }), FloatProvider.CODEC.fieldOf("thickness").forGetter((configuration) -> {
                return configuration.thickness;
            }), ExtraCodecs.NON_NEGATIVE_INT.fieldOf("width_smoothness").forGetter((configuration) -> {
                return configuration.widthSmoothness;
            }), FloatProvider.CODEC.fieldOf("horizontal_radius_factor").forGetter((configuration) -> {
                return configuration.horizontalRadiusFactor;
            }), Codec.FLOAT.fieldOf("vertical_radius_default_factor").forGetter((configuration) -> {
                return configuration.verticalRadiusDefaultFactor;
            }), Codec.FLOAT.fieldOf("vertical_radius_center_factor").forGetter((configuration) -> {
                return configuration.verticalRadiusCenterFactor;
            })).apply(instance, AbyssCarverConfiguration.AbyssShapeConfiguration::new);
        });
        public final FloatProvider distanceFactor;
        public final FloatProvider thickness;
        public final int widthSmoothness;
        public final FloatProvider horizontalRadiusFactor;
        public final float verticalRadiusDefaultFactor;
        public final float verticalRadiusCenterFactor;

        public AbyssShapeConfiguration(FloatProvider distanceFactor, FloatProvider thickness, int widthSmoothness, FloatProvider horizontalRadiusFactor, float verticalRadiusDefaultFactor, float verticalRadiusCenterFactor) {
            this.widthSmoothness = widthSmoothness;
            this.horizontalRadiusFactor = horizontalRadiusFactor;
            this.verticalRadiusCenterFactor = verticalRadiusCenterFactor;
            this.distanceFactor = distanceFactor;
            this.thickness = thickness;
            this.verticalRadiusDefaultFactor = verticalRadiusDefaultFactor;
        }
    }
}
