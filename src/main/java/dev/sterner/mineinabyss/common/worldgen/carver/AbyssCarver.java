package dev.sterner.mineinabyss.common.worldgen.carver;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

import java.util.function.Function;

public class AbyssCarver extends WorldCarver<AbyssCarverConfiguration> {
    public AbyssCarver(Codec<AbyssCarverConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean carve(CarvingContext pContext, AbyssCarverConfiguration pConfig, ChunkAccess pChunk, Function<BlockPos, Holder<Biome>> pBiomeAccessor, RandomSource pRandom, Aquifer pAquifer, ChunkPos pChunkPos, CarvingMask pCarvingMask) {
        int range = (this.getRange() * 2 - 1) * 16;
        double xCoord = pChunkPos.getBlockX(pRandom.nextInt(16));
        int yCoord = pConfig.y.sample(pRandom, pContext);
        double zCoord = pChunkPos.getBlockZ(pRandom.nextInt(16));
        float yaw = pRandom.nextFloat() * ((float) Math.PI * 2F);
        float pitch = pConfig.verticalRotation.sample(pRandom);
        double yScale = pConfig.yScale.sample(pRandom);
        float thickness = pConfig.shape.thickness.sample(pRandom);
        int branchLength = (int) ((float) range * pConfig.shape.distanceFactor.sample(pRandom));
        int branchIndex = 0;
        this.performCarving(pContext, pConfig, pChunk, pBiomeAccessor, pRandom.nextLong(), pAquifer, xCoord, yCoord, zCoord, thickness, yaw, pitch, branchIndex, branchLength, yScale, pCarvingMask);
        return true;
    }

    private void performCarving(CarvingContext pContext, AbyssCarverConfiguration pConfig, ChunkAccess pChunk, Function<BlockPos, Holder<Biome>> pBiomeAccessor, long pSeed, Aquifer pAquifer, double pX, double pY, double pZ, float pThickness, float pYaw, float pPitch, int pBranchIndex, int pBranchCount, double pHorizontalVerticalRatio, CarvingMask pCarvingMask) {
        RandomSource randomSource = RandomSource.create(pSeed);
        float[] widthFactors = this.initWidthFactors(pContext, pConfig, randomSource);
        float f = 0.0F;
        float f1 = 0.0F;

        for (int i = pBranchIndex; i < pBranchCount; ++i) {
            double horizontalRadius = 1.5D + (double) (Mth.sin((float) i * (float) Math.PI / (float) pBranchCount) * pThickness);
            double verticalRadius = horizontalRadius * pHorizontalVerticalRatio;
            horizontalRadius *= pConfig.shape.horizontalRadiusFactor.sample(randomSource);
            verticalRadius = this.updateVerticalRadius(pConfig, randomSource, verticalRadius, (float) pBranchCount, (float) i);
            float cosPitch = Mth.cos(pPitch);
            float sinPitch = Mth.sin(pPitch);
            pX += Mth.cos(pYaw) * cosPitch;
            pY += sinPitch;
            pZ += Mth.sin(pYaw) * cosPitch;
            pPitch *= 0.7F;
            pPitch += f1 * 0.05F;
            pYaw += f * 0.05F;
            f1 *= 0.8F;
            f *= 0.5F;
            f1 += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 2.0F;
            f += (randomSource.nextFloat() - randomSource.nextFloat()) * randomSource.nextFloat() * 4.0F;
            if (randomSource.nextInt(4) != 0) {
                if (!canReach(pChunk.getPos(), pX, pZ, i, pBranchCount, pThickness)) {
                    return;
                }

                this.carveEllipsoid(pContext, pConfig, pChunk, pBiomeAccessor, pAquifer, pX, pY, pZ, horizontalRadius, verticalRadius, pCarvingMask, (p1, p2, p3, p4, p5) -> {
                    return this.shouldSkip(p1, widthFactors, p2, p3, p4, p5);
                });
            }
        }
    }

    @Override
    public boolean isStartChunk(AbyssCarverConfiguration pConfig, RandomSource pRandom) {
        return pRandom.nextFloat() <= pConfig.probability;
    }

    private float[] initWidthFactors(CarvingContext pContext, AbyssCarverConfiguration pConfig, RandomSource pRandom) {
        int i = pContext.getGenDepth();
        float[] afloat = new float[i];
        float f = 1.0F;

        for(int j = 0; j < i; ++j) {
            if (j == 0 || pRandom.nextInt(pConfig.shape.widthSmoothness) == 0) {
                f = 1.0F + pRandom.nextFloat() * pRandom.nextFloat();
            }

            afloat[j] = f * f;
        }

        return afloat;
    }

    private double updateVerticalRadius(AbyssCarverConfiguration pConfig, RandomSource pRandom, double pVerticalRadius, float pBranchCount, float pCurrentBranch) {
        float f = 1.0F - Mth.abs(0.5F - pCurrentBranch / pBranchCount) * 2.0F;
        float f1 = pConfig.shape.verticalRadiusDefaultFactor + pConfig.shape.verticalRadiusCenterFactor * f;
        return (double)f1 * pVerticalRadius * (double)Mth.randomBetween(pRandom, 0.75F, 1.0F);
    }

    private boolean shouldSkip(CarvingContext pContext, float[] pWidthFactors, double pRelativeX, double pRelativeY, double pRelativeZ, int pY) {
        int i = pY - pContext.getMinGenY();
        return (pRelativeX * pRelativeX + pRelativeZ * pRelativeZ) * (double)pWidthFactors[i - 1] + pRelativeY * pRelativeY / 6.0D >= 1.0D;
    }
}
