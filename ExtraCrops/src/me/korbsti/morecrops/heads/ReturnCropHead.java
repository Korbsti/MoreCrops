package me.korbsti.morecrops.heads;

import java.net.MalformedURLException;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataType;

import me.korbsti.morecrops.MoreCrops;
import me.korbsti.morecrops.crop.Crop;
import net.md_5.bungee.api.ChatColor;

public class ReturnCropHead {
	
	MoreCrops plugin;
	
	public ReturnCropHead(MoreCrops instance) {
		this.plugin = instance;
	}
	
	
	
	public ItemStack returnHead(Crop crop, int textureInt, int amount) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', crop.getCropDisplayName()));
		
		NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, crop.getFullyGrown() + "");
		
		meta.setCustomModelData(crop.getID());
		meta.setOwnerProfile(crop.getPlayerProfiles().get(textureInt));
		head.setItemMeta(meta);
		return head;
	}
	
	
	public ItemStack returnSeed(Crop crop, int textureInt, int amount) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', crop.getCropDisplayName()));
		
		NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "false");
		
		meta.setCustomModelData(crop.getID());
		meta.setOwnerProfile(crop.getPlayerProfiles().get(textureInt));
		head.setItemMeta(meta);
		return head;
	}
	
	
	
}
