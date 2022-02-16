package me.korbsti.morecrops.crop;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.MoreCrops;

public class CropManager {
	
	MoreCrops plugin;
	
	public CropManager(MoreCrops instance) {
		this.plugin = instance;
	}
	
	public void removeCrop(String str) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(plugin.cachingFiles) return;

				File file = new File(plugin.configManager.data + File.separator + str);
				
				if (file.exists()) {
					ifCropLastStage(str, file);
					file.delete();
					plugin.cropHash.remove(file);
					return;
					
				}
			}
		});
		
	}
	
	public boolean cropFileExists(String str) {
		return new File(plugin.configManager.data + File.separator + str).exists();
		
	}
	
	public void ifCropLastStage(String str, File file) {
		if(plugin.cachingFiles) return;
		String[] data = str.split("AbD4");
		Location l = new Location(Bukkit.getWorld(data[0]), Double.valueOf(data[1]), Double.valueOf(data[2]), Double
		        .valueOf(data[3].substring(0, data[3].length() - 4)));
		Crop crop = plugin.cropHash.get(file);
		Bukkit.getScheduler().runTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if (crop.getCurrentStage() >= crop.getStageAmount()) {
					l.getWorld().dropItemNaturally(l, plugin.returnSkull.returnHead(crop, crop.getStageAmount() - 1,crop.getDropAmount()));
					l.getWorld().dropItemNaturally(l, plugin.returnSkull.returnSeed(crop, 0, crop.getDropSeedAmount()));
					l.getBlock().setType(Material.AIR);
				} else {
					l.getWorld().dropItemNaturally(l, plugin.returnSkull.returnSeed(crop, 0,1));
					l.getBlock().setType(Material.AIR);
					
				}
				
			}
			
		});
		
	}
	
	public boolean newCropPlacement(Location l, ItemStack stack) {
		String str = l.getWorld().getName() + "AbD4" + l.getBlockX() + "AbD4" + l.getBlockY() + "AbD4" + l
		        .getBlockZ()
		        + ".yml";
		File file = new File(plugin.configManager.data + File.separator + str);
		
		if (!file.exists()) {
			try {
				Crop newCrop = new Crop(plugin);
				ItemMeta meta = stack.getItemMeta();
				for (Crop crops : plugin.crops) {
					
					if (meta.hasCustomModelData()) {
						if (crops.getID() == meta.getCustomModelData()) {
							if (!meta.getPersistentDataContainer().isEmpty()) {
								
								NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
								if (meta.getPersistentDataContainer().get(key, PersistentDataType.STRING).equals(
								        "false")) {
									String[] underBlocks = crops.getUnderBlocks().split(",");
									boolean contains = false;
									Material mat = l.getBlock().getLocation().add(0, -0.7, 0).getBlock()
									        .getType();
									for (String underBlock : underBlocks) {
										if (Material.getMaterial(underBlock).equals(mat)) {
											contains = true;
										}
									}
									if (!contains)
										return true;
									if(plugin.cachingFiles) return true;
									file.createNewFile();
									newCrop = crops;
									newCrop.setCropFile(file);
									newCrop.setCropYaml();
									newCrop.setSubStage(0);
									newCrop.setCurrentStage(0);
									newCrop.setFullyGrown(false);
									plugin.saveFiles.saveFile(newCrop.getCropFile(), newCrop);
									plugin.cropHash.put(newCrop.getCropFile(), newCrop);
									return false;
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return false;
		
	}
	
}
