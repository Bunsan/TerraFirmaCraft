/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util.climate;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

public final class ClimateTFC
{
    private static final ClimateCache CACHE = new ClimateCache();

    public static float getActualTemp(World world, BlockPos pos)
    {
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        if (data.isInitialized())
        {
            return ClimateHelper.actualTemp(data.getRegionalTemp(), pos.getY(), pos.getZ());
        }
        return getActualTemp(pos);
    }

    public static float getActualTemp(BlockPos pos)
    {
        return ClimateHelper.actualTemp(CACHE.get(pos).getRegionalTemp(), pos.getY(), pos.getZ());
    }

    public static float getDailyTemp(World world, BlockPos pos)
    {
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        if (data.isInitialized())
        {
            return ClimateHelper.dailyTemp(data.getRegionalTemp(), pos.getZ());
        }
        return getDailyTemp(pos);
    }

    public static float getDailyTemp(BlockPos pos)
    {
        return ClimateHelper.dailyTemp(CACHE.get(pos).getRegionalTemp(), pos.getZ());
    }

    public static float getMonthlyTemp(World world, BlockPos pos)
    {
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        if (data.isInitialized())
        {
            return ClimateHelper.monthlyTemp(data.getRegionalTemp(), pos.getZ());
        }
        return getMonthlyTemp(pos);
    }

    public static float getMonthlyTemp(BlockPos pos)
    {
        return ClimateHelper.monthlyTemp(CACHE.get(pos).getRegionalTemp(), pos.getZ());
    }

    public static float getAvgTemp(World world, BlockPos pos)
    {
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        if (data.isInitialized())
        {
            return data.getAverageTemp();
        }
        return getAvgTemp(pos);
    }

    public static float getAvgTemp(BlockPos pos)
    {
        return ClimateHelper.monthFactor(CACHE.get(pos).getRegionalTemp(), Month.AVERAGE_TEMPERATURE_MODIFIER, pos.getZ());
    }

    public static float getRainfall(World world, BlockPos pos)
    {
        return ChunkDataTFC.getRainfall(world, pos);
    }

    public static float getRainfall(BlockPos pos)
    {
        return CACHE.get(pos).getRainfall();
    }

    public static void update(ChunkPos pos, float temperature, float rainfall)
    {
        CACHE.update(pos, temperature, rainfall);
    }

    private ClimateTFC() {}
}
