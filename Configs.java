package mw.nome;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Configs implements Listener {

	private Plugin pl = Main.getPlugin(Main.class);
	
	public static FileConfiguration lgCfg;
	public static File lgFile;
	
	public static FileConfiguration cfg;
	public static File cfgFile;

	public Configs() {

		setup();
		
		saveMsgFile();
		reloadMsgFile();
		msgFileInit();
		getMsgFile().options().copyDefaults(true);
		saveMsgFile();
		reloadMsgFile();
		
		pl.saveConfig();
		pl.reloadConfig();
		cfgInit();
		pl.getConfig().options().copyDefaults(true);
		pl.saveConfig();
		pl.reloadConfig();

	}

	public void setup() {

		if (!pl.getDataFolder().exists()) {

			pl.getDataFolder().mkdir();

		}
				
		
//======================================================================================================
		
		lgFile = new File(pl.getDataFolder(), "msg.yml");

		if (!lgFile.exists()) {

			try {

				lgFile.createNewFile();
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e{"+Main.consolePrefix+"} : &r&aFile 'msg' creato!\n"));
				
			} catch (IOException e) {

				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e{"+Main.consolePrefix+"} : &r&cErrore nel creare il file 'msg'!"));

			}

		}

		lgCfg = YamlConfiguration.loadConfiguration(lgFile);
		
//======================================================================================================
		
		cfgFile = new File(pl.getDataFolder(), "config.yml");

		if (!cfgFile.exists()) {

			try {

				cfgFile.createNewFile();
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e{"+Main.consolePrefix+"} : &r&aFile 'config' creato!\n"));

			} catch (IOException e) {

				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e{"+Main.consolePrefix+"} : &r&cErrore nel creare il file 'config'!\n"));

			}

		}

		cfg = YamlConfiguration.loadConfiguration(cfgFile);
		
		
//======================================================================================================
	}
	
	public static FileConfiguration getMsgFile() {

		return lgCfg;

	}

	
	public static void saveMsgFile() {

		try {

			lgCfg.save(lgFile);

		} catch (IOException e) {

			/*
			 * Bukkit.getServer().getConsoleSender()
			 * .sendMessage(ChatColor.translateAlternateColorCodes('&',
			 * "&cCould not save block."));
			 */

		}
	}
	
	public static void reloadMsgFile() {

		lgCfg = YamlConfiguration.loadConfiguration(lgFile);

		/*
		 * Bukkit.getServer().getConsoleSender()
		 * .sendMessage(ChatColor.translateAlternateColorCodes('&',
		 * "&2Config has been reloaded!"));
		 */

	}


	// FILE INIT
	
	public void msgFileInit() {
		

	}
	
	public void cfgInit() {
		
		pl.getConfig().addDefault("Enable-Plugin", true);

	}

}
