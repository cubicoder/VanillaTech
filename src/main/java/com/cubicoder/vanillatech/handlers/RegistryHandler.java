package com.cubicoder.vanillatech.handlers;

import com.cubicoder.vanillatech.VanillaTech;
import com.cubicoder.vanillatech.block.BlockAlloyFurnace;
import com.cubicoder.vanillatech.init.ModBlocks;
import com.cubicoder.vanillatech.tileentity.TileEntityAlloyFurnace;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		final Block[] blocks = {
				new BlockAlloyFurnace("alloyfurnace", "alloy_furnace")//,
				//new Block()
		};
		
		event.getRegistry().registerAll(blocks);
		
		registerTileEntities();
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		final Item[] items = {
				
		};
		
		final Item[] itemBlocks = {
				new ItemBlock(ModBlocks.ALLOY_FURNACE).setRegistryName(ModBlocks.ALLOY_FURNACE.getRegistryName())
		};
		
		event.getRegistry().registerAll(items);
		event.getRegistry().registerAll(itemBlocks);
	}
	
	private static void registerTileEntities() {
		registerTileEntity(TileEntityAlloyFurnace.class, "alloy_furnace");
	}
	
	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name) {
		GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(VanillaTech.MODID, name));
	}
	
}
