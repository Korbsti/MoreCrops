package me.korbsti.morecrops.events;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.korbsti.morecrops.MoreCrops;

public class BlockExplode implements Listener {
	
	MoreCrops plugin;
	
	public BlockExplode(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockExplode(EntityExplodeEvent e) {
		for (Block b : e.blockList()) {
			Location l = b.getLocation();
			String str = b.getWorld().getName() + "AbD4" + l.getBlockX() + "AbD4" + l.getBlockY() + "AbD4" + l
			        .getBlockZ() + ".yml";
			File file = new File(plugin.configManager.data + File.separator + str);
			if (file.exists()) {
				b.setType(Material.AIR);
				file.delete();
				plugin.cropHash.remove(file);
			}
		}
	}
	
}
