package ml.lbplugins.hg.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ml.lbplugins.hg.Main;
import ml.lbplugins.hg.kits.SelectorKIT;

public class Methodos {

	// playerpickupevent
	// tomarsopa

	public static ArrayList<String> participantes = new ArrayList<String>();
	public static HashMap<String, Integer> kills = new HashMap<String, Integer>();

	public static void fimHunguer() {

	}

	public static void preparar(int avisos) {
		int tempoavisos = Action.getTempoEntreAnuncios();

		if (avisos == 0) {
			Action.start();
			return;
		}
		if (avisos == Action.getAvisos()) {
			Action.time = tempoavisos * Action.getAvisos();
			Main.up.run();
		}
		// Bukkit.broadcastMessage("");
		// Bukkit.broadcastMessage("§c§l[SpiderMC] §fIniciando partida em: §a" +
		// Utils.convert(tempoavisos*avisos));
		// Bukkit.broadcastMessage("");
		new BukkitRunnable() {
			public void run() {
				preparar(avisos - 1);
			}
		}.runTaskLater(Main.getInstance(), tempoavisos * 20L);
	}

	public static void stopEvent() {
		Action.reiniciando = true;
		Bukkit.getConsoleSender().sendMessage("§eRestarting server.");

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer("§aRestarting server");
		}
		Action.comecou = false;
		Action.fecharservidor = false;
		Action.fim = false;
		Action.reiniciando = false;
		Action.midle = false;
		SelectorKIT.kit.clear();
		participantes.clear();
		kills.clear();
		World world = Bukkit.getWorld(Main.getInstance().getConfig().getString("World-Name"));
		String worldName = world.getName(); // The world name

		if (!Bukkit.unloadWorld(world, false))

			Bukkit.getConsoleSender().sendMessage("§aReiniciando o mundo...");
		File worldFolder = new File(Main.getInstance().getDataFolder().getParentFile().getParentFile(), worldName); // World
		// folder
		worldFolder.delete(); // Delete world folder

		Bukkit.createWorld(new WorldCreator(worldName)); // Create the world
		Bukkit.getServer().getScheduler().cancelTasks(Main.getInstance());
	}

	public static void checkFinal(Player p) {
		if (Methodos.participantes.contains(p.getName())) {
			Methodos.participantes.remove(p.getName());
		}
		if (Action.getIniciou() == true) {
			if (participantes.size() == 1) {
				// RESTOU UM JOGADOR VITORIOSO
				@SuppressWarnings("unused")
				String winer = participantes.get(0);
				// RECOMPENSA
				stopEvent();

			}
		}
	}

	public static void checkJoin(Player p) {
		if (Methodos.participantes.size() == 0) {
			preparar(Action.getAvisos());
		}
		// VERIFICA SE O JOGADOR J§ ENTROU ANTERIORMENTE NA SALA
		if (!Methodos.participantes.contains(p.getName())) {
			Methodos.participantes.add(p.getName());
		}
		// DEFINE A SCOREBOARD
		Main.up.build(p);
		// RECOME§A AS CONFIGURA§§ES DO JOGADOR
		p.updateInventory();
		p.setGameMode(GameMode.SURVIVAL);
		CustomJoinIten.setItem(p);
		if (!Main.getInstance().getConfig().contains("Spawn")) {
			p.sendMessage("§4--------------------------");
			p.sendMessage("§cSet game spawn");
			p.sendMessage("§4--------------------------");
		} else {
			World w = Bukkit.getWorld(Main.getInstance().getConfig().getString("Spawn.W"));
			Double x = Main.getInstance().getConfig().getDouble("Spawn.X");
			Double y = Main.getInstance().getConfig().getDouble("Spawn.Y");
			Double z = Main.getInstance().getConfig().getDouble("Spawn.Z");
			Location loc = new Location(w, x, y, z);
			p.teleport(loc);
		}
	}

}
