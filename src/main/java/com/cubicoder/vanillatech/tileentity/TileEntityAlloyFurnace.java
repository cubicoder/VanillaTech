package com.cubicoder.vanillatech.tileentity;

import com.cubicoder.vanillatech.VanillaTech;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileEntityAlloyFurnace extends TileEntity implements ITickable {
	
	private ItemStackHandler inputSlots = new ItemStackHandler(2);
	private ItemStackHandler outputSlot = new ItemStackHandler(1);
	private ItemStackHandler fuelSlot = new ItemStackHandler(1);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag(VanillaTech.MODID + ":input", inputSlots.serializeNBT());
		compound.setTag(VanillaTech.MODID + ":output", outputSlot.serializeNBT());
		compound.setTag(VanillaTech.MODID + ":fuel", fuelSlot.serializeNBT());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey(VanillaTech.MODID + ":input")) {
			inputSlots.deserializeNBT(compound.getCompoundTag(VanillaTech.MODID + ":input"));
			outputSlot.deserializeNBT(compound.getCompoundTag(VanillaTech.MODID + ":output"));
			fuelSlot.deserializeNBT(compound.getCompoundTag(VanillaTech.MODID + ":fuel"));
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			this.markDirty();
			if (world != null && world.getBlockState(pos).getBlock() != getBlockType()) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new CombinedInvWrapper(inputSlots, fuelSlot, outputSlot));
			} else if (facing == null) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new CombinedInvWrapper(inputSlots, fuelSlot, outputSlot));
			} else if (facing == EnumFacing.UP) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputSlots);
			} else if (isHorizontal(facing)) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(fuelSlot);
			} else if (facing == EnumFacing.DOWN) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputSlot);
			}
		}
		return super.getCapability(capability, facing);
	}

	private boolean isHorizontal(EnumFacing facing) {
		if (facing == EnumFacing.NORTH) return true;
		if (facing == EnumFacing.SOUTH) return true;
		if (facing == EnumFacing.EAST) return true;
		if (facing == EnumFacing.WEST) return true;
		return false;
	}
	
	@Override
	public void update() {

	}

}
