package me.korbsti.morecrops.updater;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.MoreCrops;
import me.korbsti.morecrops.crop.Crop;

public class CropUpdater {
	
	MoreCrops plugin;
	
	public CropUpdater(MoreCrops instance) {
		this.plugin = instance;
	}
	
	public void repeatCropUpdate() {
		int cropUpdater = plugin.configManager.yamlConfig.getInt("settings.updater");
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				for (Entry<File, Crop> crop : plugin.cropHash.entrySet()) {
					try {
						if(plugin.cachingFiles) return;
						updateStages(crop.getValue());
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}, 0, cropUpdater);
	}
	
	public void updateStages(Crop crop) {
		
		if (crop.getStageAmount() != crop.getCurrentStage() && !crop.getFullyGrown()) {
			if (crop.getSubStage() >= crop.getGrowthPerStage()) {
				crop.setCurrentStage(crop.getCurrentStage() + 1);
				
				if(crop.getStageAmount() != crop.getCurrentStage()) {
					String[] data = crop.getCropFile().getName().split("AbD4");
					Location l = new Location(Bukkit.getWorld(data[0]), Double.valueOf(data[1]), Double.valueOf(data[2]),Double.valueOf(data[3].substring(0, data[3].length() - 4)));
					if (l.getWorld().getChunkAt(l).isLoaded()) {
						Bukkit.getScheduler().runTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								if (l.getBlock().getState() instanceof Skull) {
									Skull skull = (Skull) l.getBlock().getState();
									try {
										skull.setOwnerProfile(crop.getPlayerProfiles().get(crop.getCurrentStage()));
										skull.update(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								
							}
						});
					}
				}
				
				crop.setSubStage(0);
			} else {
				crop.setSubStage(crop.getGrowthSpeed() + crop.getSubStage());
			}
			try {
				plugin.saveFiles.saveFile(crop.getCropFile(), crop);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			if (crop.getStageAmount() == crop.getCurrentStage() && !crop.getFullyGrown()) {
				String[] data = crop.getCropFile().getName().split("AbD4");
				Location l = new Location(Bukkit.getWorld(data[0]), Double.valueOf(data[1]), Double.valueOf(data[2]),
				        Double.valueOf(data[3].substring(0, data[3].length() - 4)));
				
				
				if (l.getWorld().getChunkAt(l).isLoaded()) {
					Bukkit.getScheduler().runTask(plugin, new Runnable() {
						
						@Override
						public void run() {
							// l.getBlock().setType(Material.PLAYER_HEAD);
							if (l.getBlock().getState() instanceof Skull) {
								Skull skull = (Skull) l.getBlock().getState();
								try {
									skull.setOwnerProfile(crop.getPlayerProfiles().get(crop.getStageAmount() - 1));
									skull.update(true);
									crop.setFullyGrown(true);
									Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
										
										@Override
										public void run() {
											try {
												plugin.saveFiles.saveFile(crop.getCropFile(), crop);
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
						}
					});
				}
			}
		}
		
	}
	
}
