package com.cubicoder.vanillatech.inventory;

import com.cubicoder.vanillatech.tileentity.TileEntityAlloyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAlloyFurnace extends Container {

	public ContainerAlloyFurnace(InventoryPlayer playerInv, final TileEntityAlloyFurnace te) {
		IItemHandler inventory = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		//alloy furnace slots (using capabilities)
		addSlotToContainer(new SlotItemHandler(inventory, 0, 40, 17) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotItemHandler(inventory, 1, 72, 17) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotItemHandler(inventory, 2, 56, 53) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotItemHandler(inventory, 3, 116, 36) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});

		//player inventory slots
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		//hotbar slots
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
	
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
	
			slot.onTake(playerIn, itemstack1);
		}
	
		return itemstack;
	}
	
}
