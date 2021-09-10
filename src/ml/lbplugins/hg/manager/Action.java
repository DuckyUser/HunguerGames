package ml.lbplugins.hg.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import ml.lbplugins.hg.Main;
import ml.lbplugins.hg.kits.SelectorKIT;

public class Action {

	public static boolean comecou;
	public static boolean fecharservidor;
	public static boolean reiniciando;
	public static boolean midle;
	public static int avisos;
	public static boolean fim;
	public static int tempoliberarpvp;
	public static int tempoentreanuncios;
	public static int tempomidle;
	public static int tempoarena;
	public static int time;

	public Action(boolean midle, int participantesmax, boolean comecou, boolean fecharservidor, boolean reiniciando,
			int avisos, boolean fim, int tempoentreanuncios, int tempoliberarpvp, int tempomidle, int tempoarena, int time) {
		Action.comecou = comecou;
		Action.fecharservidor = fecharservidor;
		Action.reiniciando = reiniciando;
		Action.avisos = avisos;
		Action.fim = fim;
		Action.midle = midle;
		Action.tempomidle = tempomidle;
		Action.tempoentreanuncios = tempoentreanuncios;
		Action.tempoliberarpvp = tempoliberarpvp;
		Action.tempoarena = tempoarena;
		Action.time = time;
	}

	public static int getTime() {
		return time;
	}
	
	public static int getTempoEntreAnuncios() {
		return tempoentreanuncios;
	}

	public static int getTempoArena() {
		return tempoarena;
	}

	public static int getTempoMidle() {
		return tempomidle;
	}

	public static int getLiberarPvP() {
		return tempoliberarpvp;
	}

	public static boolean getFim() {
		return fim;
	}

	public static boolean getReiniciando() {
		return reiniciando;
	}

	public static int getAvisos() {
		return avisos;
	}

	public static boolean getIniciou() {
		return comecou;
	}

	public static boolean getFechou() {
		return fecharservidor;
	}

	public static void start() {
		// AVISA OS JOGADORES SOBRE O START
		int participantes = Methodos.participantes.size();
		if (participantes < 1) {
			Bukkit.broadcastMessage("§aRestart counting! Insufficient players.");
			Methodos.preparar(Action.getAvisos());
			return;
		}
		// FECHA A SALA PARA NENHUM JOGADOR ENTRAR
		fecharservidor = true;
		Action.time = Action.getLiberarPvP();
		for (String p2 : Methodos.participantes) {
			Player p = Bukkit.getPlayer(p2);
			p.closeInventory();
			Main.up.build(p);
			SelectorKIT.giveKIT(p);
		}
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§f Collect resources! Invincibility ends in: §6"
				+ Utils.convert(Action.getLiberarPvP()));
		Bukkit.broadcastMessage("");
		int tempotodo = Action.getLiberarPvP() + Action.getTempoMidle();
		new BukkitRunnable() {

			@Override
			public void run() {
				Utils.spawnMidle();
			}
		}.runTaskLater(Main.getInstance(), 20L * tempotodo);
		new BukkitRunnable() {

			@Override
			public void run() {
				// POSTERIOR AO TEMPO DE START LIBERA O PVP
				Action.comecou = true;
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("§fPvP released! Good game");
				Bukkit.broadcastMessage("");
				for (String p2 : Methodos.participantes) {
					Player p = Bukkit.getPlayer(p2);
					p.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					Main.up.build(p);
					Utils.enviar("§4PvP released!", "§fLet the games begin!", 2, 2, 2, p);
				}
				Action.time = Action.getTempoMidle();
			}
		}.runTaskLater(Main.getInstance(), 20L * Action.getLiberarPvP());
	}

}
