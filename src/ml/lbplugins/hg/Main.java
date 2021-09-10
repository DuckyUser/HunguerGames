package ml.lbplugins.hg;

import org.bukkit.plugin.java.JavaPlugin;


import ml.lbplugins.hg.events.generalListener;
import ml.lbplugins.hg.kits.Archer;
import ml.lbplugins.hg.kits.Barbarian;
import ml.lbplugins.hg.kits.Beastmaster;
import ml.lbplugins.hg.kits.Berserker;

import ml.lbplugins.hg.kits.Cultivator;
import ml.lbplugins.hg.kits.Demoman;
import ml.lbplugins.hg.kits.Fisherman;
import ml.lbplugins.hg.kits.Grandpa;
import ml.lbplugins.hg.kits.Stomper;
import ml.lbplugins.hg.kits.Worm;
import ml.lbplugins.hg.manager.Action;
import ml.lbplugins.hg.manager.Chat;
import ml.lbplugins.hg.manager.Commands;
import ml.lbplugins.hg.manager.CustomJoinIten;
import ml.lbplugins.hg.manager.MethodosInPlay;
import ml.lbplugins.hg.score.ScoreBoardManager;

public class Main extends JavaPlugin {

	public static Main instance;
	public static ScoreBoardManager up = new ScoreBoardManager();

	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		instance = this;
		Action.avisos = 5;
		Action.tempoentreanuncios = 60;
		Action.tempoliberarpvp = getConfig().getInt("invincibility");
		Action.tempoarena = 800;
		loadManagers();
		loadKits();
		/**try {
			World source = Bukkit.getWorld("world");
			File sourceFolder = source.getWorldFolder();
			// The world to overwrite when copying
			//cria uma pasta
			File targetFolder = new File(Bukkit.getWorldContainer(), "hg");

			UtilsWorld.copyWorld(sourceFolder, targetFolder);
			Bukkit.getConsoleSender().sendMessage("�aMundo carregado.");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static Main getInstance() {
		return instance;
	}

	public void loadKits() {
		getServer().getPluginManager().registerEvents(new Grandpa(), this);
		getServer().getPluginManager().registerEvents(new Archer(), this);
		getServer().getPluginManager().registerEvents(new Barbarian(), this);
		getServer().getPluginManager().registerEvents(new Beastmaster(), this);
		getServer().getPluginManager().registerEvents(new Berserker(), this);
		getServer().getPluginManager().registerEvents(new Cultivator(), this);
		getServer().getPluginManager().registerEvents(new Demoman(), this);
		getServer().getPluginManager().registerEvents(new Fisherman(), this);
		getServer().getPluginManager().registerEvents(new Stomper(), this);
		getServer().getPluginManager().registerEvents(new Worm(), this);
	}

	public void loadManagers() {
		getServer().getPluginManager().registerEvents(new generalListener(), this);
		getServer().getPluginManager().registerEvents(new CustomJoinIten(), this);
		getServer().getPluginManager().registerEvents(new Chat(), this);
		getServer().getPluginManager().registerEvents(new MethodosInPlay(), this);
		getCommand("tst").setExecutor(new Commands());
	}
}
