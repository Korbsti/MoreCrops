package me.korbsti.morecrops.utils;

import me.korbsti.morecrops.MoreCrops;
import net.md_5.bungee.api.ChatColor;

public class Message {
	
	MoreCrops plugin;
	
	public Message(MoreCrops instance) {
		this.plugin = instance;
	}
	
	
	public String returnString(String str) {
		return ChatColor.translateAlternateColorCodes('&', plugin.configManager.yamlConfig.getString("messages." + str));
	}
	
	
}
