package dev.sterner.mineinabyss.compat.terrablender;

import com.ibm.icu.impl.Pair;
import dev.sterner.mineinabyss.registry.MIABiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class BiomeClimateHandler {
    //temperature
    //humidity
    //continentalness
    //erosion
    //depth
    //weirdness
    //offset
    public static final Climate.ParameterPoint LAYER_1 = Climate.parameters(
            Climate.Parameter.span(-1.0F, 1.0F),
            Climate.Parameter.span(-1.0F, 1.0F),
            Climate.Parameter.span(0.7F, 1.0F),
            Climate.Parameter.span(
                    Climate.Parameter.span(-1.0F, -0.78F),
                    Climate.Parameter.span(-0.78F, -0.375F)
            ),
            Climate.Parameter.span(0.2F, 0.9F),
            Climate.Parameter.span(-1.0F, 1.0F),
            0.0F
    );

    public static void init(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        consumer.accept(Pair.of(LAYER_1, MIABiomes.LAYER_1));
    }
}
