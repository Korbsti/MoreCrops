package me.korbsti.morecrops.events;

import java.net.MalformedURLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.korbsti.morecrops.MoreCrops;
import me.korbsti.morecrops.crop.Crop;

public class PlayerClick implements Listener {
	
	MoreCrops plugin;
	
	public PlayerClick(MoreCrops instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void playerInteract(PlayerInteractEvent e) throws NumberFormatException, MalformedURLException {
		
		Player p = e.getPlayer();
	
		
		
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			
			ItemStack stack = e.getPlayer().getInventory().getItemInMainHand();
			if (stack != null) {
				if (stack.getType() == Material.PLAYER_HEAD && stack.getItemMeta().hasCustomModelData()) {
					int amount = stack.getAmount();
					for (Crop crop : plugin.crops) {
						NamespacedKey key = new NamespacedKey(plugin, "fully-grown");
						if (crop.getID() == stack.getItemMeta().getCustomModelData() && stack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("true")) {
							p.setFoodLevel(p.getFoodLevel() + crop.getHungerFeed());
							for (String str : crop.getPotionEffects()) {
								if (!str.equals("null")) {
									String[] data = str.split(" ");
									p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
								}
							}
							if (amount - 1 == 0) {
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
								
							} else {
								ItemStack otherStack = stack.clone();
								otherStack.setAmount(stack.getAmount()-1);
								p.getInventory().setItemInMainHand(otherStack);
							}
						}
					}
				}
			}
		}
		
	}
	
}
