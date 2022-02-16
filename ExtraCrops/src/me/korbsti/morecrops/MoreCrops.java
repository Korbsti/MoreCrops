package me.korbsti.morecrops;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.korbsti.morecrops.FileHandler.SaveFile;
import me.korbsti.morecrops.commands.Commands;
import me.korbsti.morecrops.configmanager.ConfigManager;
import me.korbsti.morecrops.crop.Crop;
import me.korbsti.morecrops.crop.CropManager;
import me.korbsti.morecrops.events.BlockBreak;
import me.korbsti.morecrops.events.BlockExplode;
import me.korbsti.morecrops.events.BlockPlace;
import me.korbsti.morecrops.events.PlayerClick;
import me.korbsti.morecrops.heads.ReturnCropHead;
import me.korbsti.morecrops.updater.CropUpdater;
import me.korbsti.morecrops.updater.ReCacheFiles;
import me.korbsti.morecrops.utils.Message;

public class MoreCrops extends JavaPlugin {
	
	public ReturnCropHead returnSkull = new ReturnCropHead(this);
	public ConfigManager configManager = new ConfigManager(this);
	public Startup startup = new Startup(this);
	public Message msg = new Message(this);
	public CropManager fileManager = new CropManager(this);
	public CropUpdater cropUpdater = new CropUpdater(this);
	public CacheCrops cacheCrops = new CacheCrops(this);
	public SaveFile saveFiles = new SaveFile(this);
	public ReCacheFiles reCache = new ReCacheFiles(this);

	
	public ArrayList<Crop> crops = new ArrayList<Crop>();
	public ConcurrentHashMap<File, Crop> cropHash = new ConcurrentHashMap<File, Crop>();
	
	
	public boolean cachingFiles = false;
	
	@Override
	public void onEnable() {
		try {
					
			configManager.loadConfig();
			startup.onStartup();
			cacheCrops.cacheCrops();
			cropUpdater.repeatCropUpdate();	
			reCache.reCacheFiles();
			Bukkit.getLogger().info("MoreCrops >> Successfully Cached Crop Files");
			PluginManager manager = Bukkit.getPluginManager();
			manager.registerEvents(new BlockBreak(this), this);
			manager.registerEvents(new BlockPlace(this), this);
			manager.registerEvents(new BlockExplode(this), this);
			manager.registerEvents(new PlayerClick(this), this);
			getCommand("crops").setExecutor(new Commands(this));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
