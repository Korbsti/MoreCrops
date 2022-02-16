package me.korbsti.morecrops.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;

import me.korbsti.morecrops.MoreCrops;

public class BlockBreak implements Listener {
	
	MoreCrops plugin;
	
	public BlockBreak(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent e) {
		Location l = e.getBlock().getLocation();
		String str =  e.getBlock().getWorld().getName() + "AbD4" + l.getBlockX() + "AbD4" + l.getBlockY() + "AbD4" + l.getBlockZ() + ".yml";
		e.setCancelled(plugin.fileManager.cropFileExists(str));
		plugin.fileManager.removeCrop(str);
	}
	
	
}
