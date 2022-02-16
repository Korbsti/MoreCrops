package me.korbsti.morecrops.commands;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.MoreCrops;
import me.korbsti.morecrops.crop.Crop;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
	
	MoreCrops plugin;
	
	public Commands(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("crops")) {
			
			if (args.length <= 0) {
				sender.sendMessage(plugin.msg.returnString("invalidArgs"));
				return false;
			}
			
			if (args.length >= 3) {
				Player specified = Bukkit.getPlayer(args[1]);
				if (specified == null) {
					sender.sendMessage(plugin.msg.returnString("nonPlayer"));
					return false;
				}
				
				int amount = 1;
				
				try {
					amount = Integer.valueOf(args[3]);
				} catch (Exception e) {}
				
				if (args[0].equalsIgnoreCase("give")) {
					
					if(!sender.hasPermission("morecrops.give.crop")) {
						sender.sendMessage(plugin.msg.returnString("noPerm"));
						return false;
					}
					
					
					for (Crop crop : plugin.crops) {
						if (crop.getCropName().equalsIgnoreCase(args[2])) {
							try {
								//specified.getInventory().addItem(plugin.returnSkull.returnCommandHead(crop.getCropTextures().get(0), amount, crop.getID(), crop.getCropDisplayName()));
								specified.getInventory().addItem(plugin.returnSkull.returnHead(crop, 0, amount));

							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							sender.sendMessage(plugin.msg.returnString("giveCrop").replace("{crop}", crop.getCropName()).replace("{amount}", amount + ""));
							return true;
						}
					}
				}
			}
			if(args[0].equalsIgnoreCase("help")) {
				
				if(!sender.hasPermission("morecrops.help")) {
					sender.sendMessage(plugin.msg.returnString("noPerm"));
					return false;
				}
				
				
				for(Object str : plugin.configManager.yamlConfig.getList("messages.help")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(str)));
				}
				return true;
			}
				if(args[0].equalsIgnoreCase("purge")) {
				
				if(!sender.hasPermission("morecrops.purge")) {
					sender.sendMessage(plugin.msg.returnString("noPerm"));
					return false;
				}
				
				for (String str : new File(plugin.configManager.data).list()) {
					File f = new File(plugin.configManager.data + File.separator + str);
					File file = new File(plugin.configManager.data + File.separator + str);
					String[] fileName = file.getName().split("AbD4");
					World world = Bukkit.getWorld(fileName[0]);
					int cropX = Integer.valueOf(fileName[1]);
					int cropY = Integer.valueOf(fileName[2]);
					int cropZ = Integer.valueOf(fileName[3].substring(0, fileName[3].length()-4));
					Location l = new Location(world, cropX, cropY, cropZ);
					l.getBlock().setType(Material.AIR);
					f.delete();
				}
				plugin.cropHash.clear();
				
				sender.sendMessage(plugin.msg.returnString("purge"));
				Bukkit.getLogger().info("MoreCrops >> " + sender.getName() + " has deleted all crops in every world + wiped the data folder");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				
				if(!sender.hasPermission("morecrops.list")) {
					sender.sendMessage(plugin.msg.returnString("noPerm"));
					return false;
				}
				
				ArrayList<String> send = new ArrayList<String>();
				for(Crop crop : plugin.crops) {
					send.add(crop.getCropName());
				}
				sender.sendMessage(plugin.msg.returnString("cropsList").replace("{crops}", send + ""));
				return true;
			}
			
			sender.sendMessage(plugin.msg.returnString("invalidArgs"));
		}
		
		return false;
	}
	
}
