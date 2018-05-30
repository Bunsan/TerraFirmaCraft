package net.dries007.tfc.objects.blocks.plant.crops;

import java.util.EnumMap;
import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import net.dries007.tfc.objects.Agriculture.Crop;

public class BlockCropsTFC extends BlockBush implements IGrowable
{
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 7);

    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};


    private static final EnumMap<Crop, BlockCropsTFC> MAP = new EnumMap<>(Crop.class);

    public static BlockCropsTFC get(Crop crop)
    {
        return MAP.get(crop);
    }

    public final Crop crop;

    public BlockCropsTFC(Crop crop)
    {
        super(Material.PLANTS);
        if (MAP.put(crop, this) != null) throw new IllegalStateException("There can only be one.");
        this.crop = crop;
        //this.setCreativeTab(CT_PLANTS);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.125D, 0.875D); //CROPS_AABB[(state.getValue(this.getStageProperty())).intValue()];
    }


    protected PropertyInteger getStageProperty()
    {
        return STAGE;
    }

    protected int getStage(IBlockState state)
    {
        return (state.getValue(this.getStageProperty())).intValue();
    }

    public IBlockState withStage(int stage)
    {
        return this.getDefaultState().withProperty(this.getStageProperty(), Integer.valueOf(stage));
    }

    public int getMaxStage()
    {
        return crop.maxStage;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    { return true; }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    { return true; }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        int i = this.getStage(state) + 1;
        int j = this.getMaxStage();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, this.withStage(i), 2);
    }

    //protected int getBonemealAgeIncrease(World worldIn) { return MathHelper.getInt(worldIn.rand, 2, 5); }


    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STAGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(STAGE);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STAGE);
    }
}
