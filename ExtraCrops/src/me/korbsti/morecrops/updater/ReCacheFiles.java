package me.korbsti.morecrops.updater;

import org.bukkit.Bukkit;

import me.korbsti.morecrops.MoreCrops;

public class ReCacheFiles {
	
	MoreCrops plugin;
	
	
	public ReCacheFiles(MoreCrops instance) {
		this.plugin = instance;
	}
	
	
	
	
	public void reCacheFiles() {
		int x = plugin.configManager.yamlConfig.getInt("settings.reCacheUpdater");
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {

			@Override
			public void run() {
				
				plugin.cachingFiles = true;
				plugin.cropHash.clear();
				plugin.cacheCrops.cacheCrops();				
				
				
				
			}
			
			
			
		}, 0, x);
	}
	
	
	
	
	
}
