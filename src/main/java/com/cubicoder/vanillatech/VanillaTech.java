package com.cubicoder.vanillatech;

import org.apache.logging.log4j.Logger;

import com.cubicoder.vanillatech.client.gui.GuiHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = VanillaTech.MODID, name = VanillaTech.NAME, version = VanillaTech.VERSION, acceptedMinecraftVersions = VanillaTech.MC_VERSION)
public class VanillaTech {

	public static final String MODID = "vanillatech";
	public static final String NAME = "Vanilla Tech";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";
	
	//proxy
	
	@Instance(MODID)
	public static VanillaTech instance;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
