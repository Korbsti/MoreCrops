package me.korbsti.morecrops;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.crop.Crop;

public class CacheCrops {
	
	MoreCrops plugin;
	
	public CacheCrops(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@SuppressWarnings("unchecked")
	public void cacheCrops() {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable(){

			@Override
			public void run() {
				for (String str : new File(plugin.configManager.data).list()) {
					Crop crop = new Crop(plugin);
					
					File file = new File(plugin.configManager.data + File.separator + str);
					String[] fileName = file.getName().split("AbD4");
					World world = Bukkit.getWorld(fileName[0]);
					int cropX = Integer.valueOf(fileName[1]);
					int cropY = Integer.valueOf(fileName[2]);
					int cropZ = Integer.valueOf(fileName[3].substring(0, fileName[3].length()-4));
										
					if (world != null) {
						Location l = new Location(world, cropX, cropY, cropZ);
						crop.setCropFile(file);
						crop.setCropYaml();
						crop.setCropID(crop.getCropYaml().getInt("id"));
						crop.setCropName(crop.getCropYaml().getString("cropName"));
						crop.setStageAmount(crop.getCropYaml().getInt("stageAmount"));
						crop.setGrowthPerStage(crop.getCropYaml().getInt("growthPerStage"));
						crop.setGrowthSpeed(crop.getCropYaml().getInt("growthSpeed"));
						crop.setCurrentStage(crop.getCropYaml().getInt("currentStage"));
						crop.setSubStage(crop.getCropYaml().getInt("subStage"));
						crop.setHungerFeed(crop.getCropYaml().getInt("hungerFeed"));
						crop.setDropSeedAmount(crop.getCropYaml().getInt("dropSeedAmount"));
						crop.setDropAmount(crop.getCropYaml().getInt("dropAmount"));
						crop.setCropDisplayName(crop.getCropYaml().getString("displayName"));
						crop.setFullyGrown(crop.getCropYaml().getBoolean("fullyGrown"));

						crop.setCropPotionEffect(new ArrayList<String>());
						crop.setTexture(new ArrayList<String>());
						crop.setPlayerProfile(new ArrayList<PlayerProfile>());
						crop.setPlayerTextures(new ArrayList<PlayerTextures>());
						
						
						crop.setCropPotionEffect((ArrayList<String>) crop.getCropYaml().getList("potionEffects"));
						crop.setTexture((ArrayList<String>) crop.getCropYaml().getList("textures"));
						
						for(Crop startupCrop : plugin.crops) {
							if(startupCrop.getID() == crop.getID()) {
								crop.setPlayerProfile(startupCrop.getPlayerProfiles());
								crop.setPlayerTextures(startupCrop.getPlayerTextures());
								break;
							}
						}
						plugin.cropHash.put(crop.getCropFile(), crop);
					}
					
				}
				plugin.cachingFiles = false;
			}
			
			
			
		});
	}
	
}
