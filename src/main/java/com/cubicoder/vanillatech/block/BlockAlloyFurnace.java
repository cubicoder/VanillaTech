package com.cubicoder.vanillatech.block;

import java.util.Random;

import com.cubicoder.vanillatech.VanillaTech;
import com.cubicoder.vanillatech.client.gui.GuiHandler;
import com.cubicoder.vanillatech.init.ModBlocks;
import com.cubicoder.vanillatech.tileentity.TileEntityAlloyFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAlloyFurnace extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	//public static final PropertyBool LIT = PropertyBool.create("active");
	
	public BlockAlloyFurnace(String translationKey, String registryName) {
		super(Material.IRON);
		setTranslationKey(VanillaTech.MODID + "." + translationKey);
		setRegistryName(registryName);
		
		setHardness(5.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 1);
		setSoundType(SoundType.METAL);
		setCreativeTab(CreativeTabs.REDSTONE);

		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));//.withProperty(LIT, false));
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAlloyFurnace();
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.ALLOY_FURNACE);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING/*, LIT*/});
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (!playerIn.isSneaking()) {
				playerIn.openGui(VanillaTech.instance, GuiHandler.ALLOY_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	
}
