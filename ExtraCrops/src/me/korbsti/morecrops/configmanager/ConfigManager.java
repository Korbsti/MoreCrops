package me.korbsti.morecrops.configmanager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import me.korbsti.morecrops.MoreCrops;

public class ConfigManager {
	
	MoreCrops plugin;
	
	public String directory = System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "MoreCrops";
	public String data = directory + File.separator + "data";
	public String config = directory + File.separator + "config.yml";
	public File fileConfig;
	public YamlConfiguration yamlConfig;
	
	public ConfigManager(MoreCrops instance) {
		this.plugin = instance;
	}
	
	
	public void loadConfig() throws IOException {
		new File(directory).mkdir();
		
		fileConfig = new File(plugin.getDataFolder(), "config.yml");
		if(!fileConfig.exists()) {
			plugin.saveDefaultConfig();
		}

		yamlConfig = YamlConfiguration.loadConfiguration(fileConfig);

		new File(data).mkdir();
		
	}
	
	
	
}
