package net.dries007.tfc.objects.blocks.plant.fruittrees;

import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.Agriculture.FruitTree;
import net.dries007.tfc.util.OreDictionaryHelper;

public class BlockFruitLeaves extends BlockLeaves
{
    private static final EnumMap<FruitTree, BlockFruitLeaves> MAP = new EnumMap<>(FruitTree.class);

    public static BlockFruitLeaves get(FruitTree fruitTree)
    {
        return MAP.get(fruitTree);
    }

    public final FruitTree fruitTree;

    public BlockFruitLeaves(FruitTree fruitTree)
    {
        this.fruitTree = fruitTree;
        if (MAP.put(fruitTree, this) != null) throw new IllegalStateException("There can only be one.");
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, false));
        leavesFancy = true; // there doesn't seem to be an even for catching changing this, so lets not bother
        OreDictionaryHelper.register(this, "tree", "leaves");
        OreDictionaryHelper.register(this, "tree", "leaves", fruitTree);
        Blocks.FIRE.setFireInfo(this, 30, 60);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 0b01) == 0b01).withProperty(CHECK_DECAY, (meta & 0b10) == 0b10);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(DECAYABLE) ? 0b01 : 0) | (state.getValue(CHECK_DECAY) ? 0b10 : 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        //Player will take damage when falling through leaves if fall is over 9 blocks, fall damage is then set to 0.
        entityIn.fall((entityIn.fallDistance - 6), 1.0F); // TODO: 17/4/18 Balance fall distance reduction.
        entityIn.fallDistance = 0;
        //Entity motion is reduced by leaves.
        entityIn.motionX *= 0.1D;
        entityIn.motionY *= 0.1D;
        entityIn.motionZ *= 0.1D;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return ImmutableList.of(new ItemStack(this)); // TODO: 29/4/18 should probably return null
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockFruitLeaves.get(fruitTree)); // TODO: 29/4/18 should probably drop nothing or just a stick
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return null;
    }
}