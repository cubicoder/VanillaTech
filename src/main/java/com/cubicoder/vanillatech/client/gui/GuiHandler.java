package com.cubicoder.vanillatech.client.gui;

import com.cubicoder.vanillatech.client.gui.inventory.GuiAlloyFurnace;
import com.cubicoder.vanillatech.inventory.ContainerAlloyFurnace;
import com.cubicoder.vanillatech.tileentity.TileEntityAlloyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int ALLOY_FURNACE = 0;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {

		case ALLOY_FURNACE:
			return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace) world.getTileEntity(new BlockPos(x, y, z)));

		default:
			return null;

		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		
		case ALLOY_FURNACE:
			return new GuiAlloyFurnace(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		
		default:
			return null;
		
		}
	}

}
