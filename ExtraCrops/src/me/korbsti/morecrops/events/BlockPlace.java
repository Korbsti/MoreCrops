package me.korbsti.morecrops.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.korbsti.morecrops.MoreCrops;

public class BlockPlace implements Listener {
	
	MoreCrops plugin;
	
	public BlockPlace(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlockPlaced().getType() == Material.PLAYER_HEAD || e.getBlockPlaced().getType() == Material.PLAYER_WALL_HEAD) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD || e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PLAYER_WALL_HEAD) {
				ItemStack stack = e.getPlayer().getInventory().getItemInMainHand();
				if (stack != null && stack.getItemMeta() != null) {
					if (!stack.getItemMeta().getPersistentDataContainer().isEmpty()) {
						NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
						if (stack.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
							if (stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING)
							        .equals("true")) {
								e.setCancelled(true);
								e.getPlayer().sendMessage(plugin.msg.returnString("cannotPlaceGrownCrop"));
								return;
							}
							boolean bool = plugin.fileManager.newCropPlacement(e.getBlockPlaced().getLocation(), stack);
							if (bool) {
								e.setCancelled(true);
								e.getPlayer().sendMessage(plugin.msg.returnString("cannotPlaceCropOnBlock"));
								return;
							}
						}
					}
				}
			}
			if (e.getPlayer().getInventory().getItemInOffHand().getType() == Material.PLAYER_HEAD || e.getPlayer().getInventory().getItemInOffHand().getType() == Material.PLAYER_WALL_HEAD) {
				ItemStack stack2 = e.getPlayer().getInventory().getItemInOffHand();
				if (stack2 != null && stack2.getItemMeta() != null) {
					if (!stack2.getItemMeta().getPersistentDataContainer().isEmpty()) {
						NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
						if (stack2.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
							if (stack2.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING)
							        .equals("true")) {
								e.setCancelled(true);
								e.getPlayer().sendMessage(plugin.msg.returnString("cannotPlaceGrownCrop"));
								return;
							}
							boolean bool = plugin.fileManager.newCropPlacement(e.getBlockPlaced().getLocation(), stack2);
							if (bool) {
								e.setCancelled(true);
								e.getPlayer().sendMessage(plugin.msg.returnString("cannotPlaceCropOnBlock"));
								return;
							}
						}
					}
				}
			}
		}
	}
	
}
