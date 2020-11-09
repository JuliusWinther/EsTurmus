package mw.nome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

import mw.kingdoms.Main;

public class Library {

	private static Plugin pl = Main.getPlugin(Main.class);

	// STANDARD LIBRARY
    //===========================================
	
	//UPDATE GUI
	public static void updateGUI(Inventory inv, Player player) {
		
		player.openInventory(inv);
		player.updateInventory();
		
	}
	
	//FIRST CAPITAL LETTER
	public static String capitalizeFirstLetter(String str) {
		
		if(str.length() > 1)
		 return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
		else
		 return str.substring(0).toUpperCase();
	}
	
	//GIVE COLORED ARMOR
	public static ItemStack giveColorArmor(Material m, Color c, String name) {
		ItemStack i = new ItemStack(m, 1);
		LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
		meta.setColor(c);
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		return i;
	}
	
	//CREATE OBJECT FOR CHESTS
	public static void createDisplay(Material m, int q, byte v, Inventory inv, int slot, String name, List<String> lore) {

		ItemStack item = new ItemStack(m, q, v);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

		ArrayList<String> Lore = new ArrayList<String>(); // Elenco delle strighe che saranno nella lore
		for (String l : lore) // per ogni stringa in lore crea una stringa l e la aggiunge a Lore
			Lore.add(ChatColor.translateAlternateColorCodes('&', l));
		meta.setLore(Lore);

		item.setItemMeta(meta);
		inv.setItem(slot, item);

	}
	
	//GENERATE ITEM META
	public static ItemMeta generateMeta(ItemStack i, String name, List<String> lore, boolean unbreakable,
			boolean showData) {

		ItemMeta meta = i.getItemMeta();

		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

		ArrayList<String> Lore = new ArrayList<String>(); // Elenco delle strighe che saranno nella lore
		for (String l : lore) // per ogni stringa in lore crea una stringa l e la aggiunge a Lore
			Lore.add(ChatColor.translateAlternateColorCodes('&', l));
		meta.setLore(Lore);

		meta.spigot().setUnbreakable(unbreakable);

		if (!(showData)) {
			meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		}

		return meta;

	}
	

	//GET LAST WATCHED BLOCK
	public final static Block getTargetBlock(Player player, int range) {
		BlockIterator iter = new BlockIterator(player, range);
		Block lastBlock = iter.next();
		while (iter.hasNext()) {
			lastBlock = iter.next();
			if (lastBlock.getType() == Material.AIR) {
				continue;
			}
			break;
		}
		return lastBlock;
	}

	
	//CHECK IF VALUE IS A DOUBLE
	public static boolean isDouble(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	//ORDINA HASHMAP PER VALORI
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm, boolean reverse) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		if (reverse) {

			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});

		} else {

			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o1.getValue()).compareTo(o2.getValue());
				}
			});
		}

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	
	//VERIFICA LE DIPENDENZE
	public static boolean verifyDep(boolean plugin, String name, boolean showMsg) {

		if (plugin) {

			if (showMsg)
				pl.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&e{" + Main.consolePrefix + "} : &r&a" + name + " abilitato correttamente!\n\n"));

			return true;

		} else {

			if (showMsg)
				pl.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&e{" + Main.consolePrefix + "} : &r&c" + name + " non trovato, disabilitando la funzione...\n\n"));

			return false;

		}

	}
}
