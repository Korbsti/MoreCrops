package me.korbsti.morecrops;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.crop.Crop;

public class Startup {
	
	MoreCrops plugin;
	
	public Startup(MoreCrops instance) {
		this.plugin = instance;
	}
	
	
	@SuppressWarnings("unchecked")
	public void onStartup() throws MalformedURLException {
		for(Object str : plugin.configManager.yamlConfig.getConfigurationSection("crops.").getKeys(false)) {
			Crop crop = new Crop(plugin);
			
			crop.setCropPotionEffect(new ArrayList<String>());
			crop.setTexture(new ArrayList<String>());
			crop.setPlayerProfile(new ArrayList<PlayerProfile>());
			crop.setPlayerTextures(new ArrayList<PlayerTextures>());
			
			crop.setCropName(str.toString());
			crop.setCropID(plugin.configManager.yamlConfig.getInt("crops." + str + ".id"));
			crop.setCropDisplayName(plugin.configManager.yamlConfig.getString("crops." + str + ".displayName"));
			crop.setCurrentStage(0);
			crop.setSubStage(0);
			crop.setGrowthSpeed(plugin.configManager.yamlConfig.getInt("crops." + str + ".growthSpeed"));
			crop.setGrowthPerStage(plugin.configManager.yamlConfig.getInt("crops." + str + ".growthPerStage"));
			crop.setStageAmount(plugin.configManager.yamlConfig.getInt("crops." + str + ".stageAmount"));
			crop.setUnderBlocks(plugin.configManager.yamlConfig.getString("crops." + str + ".underBlock"));
			crop.setDropAmount(plugin.configManager.yamlConfig.getInt("crops." + str + ".dropAmount"));
			crop.setHungerFeed(plugin.configManager.yamlConfig.getInt("crops." + str + ".hungerFeed"));
			crop.setDropSeedAmount(plugin.configManager.yamlConfig.getInt("crops." + str + ".dropSeedAmount"));
			crop.setCropPotionEffect((ArrayList<String>) plugin.configManager.yamlConfig.getList("crops." + str + ".potionEffects"));
			crop.setTexture((ArrayList<String>) plugin.configManager.yamlConfig.getList("crops." + str + ".textures"));
			ArrayList<PlayerProfile> tempProfiles = new ArrayList<PlayerProfile>();
			ArrayList<PlayerTextures> tempTextures = new ArrayList<PlayerTextures>();
			for (int i = 0; i < crop.getStageAmount(); i++) {
				
				PlayerProfile f = Bukkit.createPlayerProfile(UUID.randomUUID(), "");
				PlayerTextures t = f.getTextures();
				t.setSkin(new URL(crop.getCropTextures().get(i)));
				
				tempProfiles.add(f);
				tempTextures.add(t);
				
			}
			crop.setPlayerProfile(tempProfiles);
			crop.setPlayerTextures(tempTextures);
			
			
			plugin.crops.add(crop);
		}
		Bukkit.getLogger().info("MoreCrops >> Successfully Cached Crops");
	}
	
	
	
}
