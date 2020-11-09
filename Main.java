package mw.nome;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static final String prefix = "&a&l[]:&r ";
	public static final String consolePrefix = "";


	@Override
	public void onEnable() {
		
		if (!(getConfig().getBoolean("Enable-Plugin"))) {
			getServer().getPluginManager().disablePlugin(this);
		}


		getServer().getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"====================================================="
								+ "\n\n\n&e{"+consolePrefix+"} : &r&aPlugin Abilitato correttamente!!!"
								+ "\n\n&r&5MysteryWorld &r&f Services" + "\n&r&5Autore: &r&fMM_Winther"
								+ "\n\n&r&cPer richieste di Supporto:" + "\n&fDiscord: Winther#7361"
								+ "\n&fTelegram: @MM_Winther\n\n"));

		// CONFIG
		loadConfig();

		// INIZIALIZZAZIONE OGGETTI
		loadItems();

		// EVENTI
		loadEvents();

		// COMANDI
		loadCommands();

		// PLUGIN	
		getServer().getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "====================================================="));

	}

	@Override
	public void onDisable() {

		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
				"\n====================================================="
						+ "\n\n\n&e{"+consolePrefix+"} : &r&cPlugin Disabilitato correttamente!!!"
						+ "\n\n&r&5MysteryWorld &r&f Services" + "\n&r&5Autore: &r&fMM_Winther"
						+ "\n\n&r&cPer richieste di Supporto:" + "\n&fDiscord: Winther#7361"
						+ "\n&fTelegram: @MM_Winther\n\n" + "====================================================="));

		saveConfig();
		Configs.saveMsgFile();

		// PLUGIN

	}

	private void loadItems() {}

	private void loadEvents() {

		//getServer().getPluginManager().registerEvents(new Event(), this);
		
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&e{"+consolePrefix+"} : &r&aTutti gli eventi registrati correttamente!"));

	}

	private void loadCommands() {

		//this.getCommand("cmd").setExecutor(new Commands());

		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&e{"+consolePrefix+"} : &r&aTutti i comandi registrati correttamente!\n\n"));

	}

	private void loadConfig() {

		Configs cfg = new Configs();

		saveConfig();

	}

}
