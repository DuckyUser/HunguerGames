package ml.lbplugins.hg.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ml.lbplugins.hg.Main;
import ml.lbplugins.hg.kits.SelectorKIT;
import ml.lbplugins.hg.manager.Action;
import ml.lbplugins.hg.manager.Methodos;
import ml.lbplugins.hg.manager.Utils;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ScoreBoardManager {

	public void run() {
		(new BukkitRunnable() {
			public void run() {
				updatePlayer();

			}
		}).runTaskTimer(Main.getInstance(), 0L, 20L * 1);
	}

	public void build(Player p) {
		Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = score.registerNewObjective("sbanimado", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§bPrysolm.com");
		if (Action.comecou == false && Action.fecharservidor == true) {
			
			Team separator = score.registerNewTeam("sep");
			separator.addEntry("§0");
			separator.setPrefix("§7§l────");
			separator.setSuffix("§7§l────");

			Team grupo = score.registerNewTeam("jogadores");
			grupo.addEntry("§9");
			grupo.setPrefix("§6Online:");
			grupo.setSuffix(" §f" + Methodos.participantes.size());
			
			Team wins = score.registerNewTeam("wins");
			wins.addEntry("§d");
			wins.setPrefix("§6Kills:");
			wins.setSuffix("§f 0");
			
			Team jogadores = score.registerNewTeam("midle");
			jogadores.addEntry("§c");
			jogadores.setPrefix("§6PvP: ");
			jogadores.setSuffix("§f " + Action.getLiberarPvP());
			
			Team kit1 = score.registerNewTeam("kit1");
			kit1.addEntry("§3");
			kit1.setPrefix("§4In Progress");
			kit1.setSuffix("");

			Team kit2 = score.registerNewTeam("kit2");
			kit2.addEntry("§b");
			kit2.setPrefix("§fKit2:");
			kit2.setSuffix("§3 " + SelectorKIT.returnKit(p, "kit2"));

			Team kills = score.registerNewTeam("kills");
			kills.addEntry("§a");
			kills.setPrefix("§6A:");
			// puxar as kills
			kills.setSuffix(" §f30");

			Team site = score.registerNewTeam("site");
			site.addEntry("§1");
			site.setPrefix("§6B: ");
			site.setSuffix("§f60");

			Team separator2 = score.registerNewTeam("sep2");
			separator2.addEntry("§4");
			separator2.setPrefix("§7§l────");
			separator2.setSuffix("§7§l────");

			Team separator3 = score.registerNewTeam("sep3");
			separator3.addEntry("§f");
			separator3.setPrefix("§7§l────");
			separator3.setSuffix("§7§l────");
			
			obj.getScore("§4").setScore(10);
			obj.getScore("§9").setScore(9);
			obj.getScore("§d").setScore(8);
			obj.getScore("   ").setScore(7);
			obj.getScore("§0").setScore(6);
			obj.getScore("").setScore(5);
			obj.getScore("§a").setScore(4);
			obj.getScore("§1").setScore(3);
			obj.getScore("  ").setScore(2);
			obj.getScore("§f").setScore(1);
			obj.getScore("§c").setScore(0);
		} else {
			if (Action.comecou == true) {
				Team separator = score.registerNewTeam("sep");
				separator.addEntry("§0");
				separator.setPrefix("§7§l────");
				separator.setSuffix("§7§l────");

				Team grupo = score.registerNewTeam("jogadores");
				grupo.addEntry("§9");
				grupo.setPrefix("§6Online:");
				grupo.setSuffix(" §f" + Methodos.participantes.size());
				
				Team wins = score.registerNewTeam("wins");
				wins.addEntry("§d");
				wins.setPrefix("§6Kills:");
				wins.setSuffix("§f 0");
				
				Team jogadores = score.registerNewTeam("midle");
				jogadores.addEntry("§c");
				jogadores.setPrefix("§6Good Game ");
				
				Team kit2 = score.registerNewTeam("kit2");
				kit2.addEntry("§b");
				kit2.setPrefix("§fKit2:");
				kit2.setSuffix("§3 " + SelectorKIT.returnKit(p, "kit2"));

				Team kills = score.registerNewTeam("kills");
				kills.addEntry("§a");
				kills.setPrefix("§6A:");
				// puxar as kills
				kills.setSuffix(" §f30");

				Team site = score.registerNewTeam("site");
				site.addEntry("§1");
				site.setPrefix("§6B: ");
				site.setSuffix("§f60");
				
				Team separator2 = score.registerNewTeam("sep2");
				separator2.addEntry("§4");
				separator2.setPrefix("§7§l────");
				separator2.setSuffix("§7§l────");

				Team separator3 = score.registerNewTeam("sep3");
				separator3.addEntry("§f");
				separator3.setPrefix("§7§l────");
				separator3.setSuffix("§7§l────");
				
				obj.getScore("§4").setScore(10);
				obj.getScore("§9").setScore(9);
				obj.getScore("§d").setScore(8);
				obj.getScore("   ").setScore(7);
				obj.getScore("§f").setScore(6);
				obj.getScore(" ").setScore(5);
				obj.getScore("§a").setScore(4);
				obj.getScore("§1").setScore(3);
				obj.getScore("  ").setScore(2);
				obj.getScore("§0").setScore(1);
				obj.getScore("§c").setScore(0);
			} else {

				Team separator = score.registerNewTeam("sep");
				separator.addEntry("§0");
				separator.setPrefix("§7§l────");
				separator.setSuffix("§7§l────");

				Team grupo = score.registerNewTeam("jogadores");
				grupo.addEntry("§9");
				grupo.setPrefix("§6Online:");
				grupo.setSuffix(" §f" + Methodos.participantes.size());
				
				Team wins = score.registerNewTeam("wins");
				wins.addEntry("§d");
				wins.setPrefix("§6Kills:");
				wins.setSuffix("§f 0");
				
				Team jogadores = score.registerNewTeam("midle");
				jogadores.addEntry("§c");
				jogadores.setPrefix("§6Starts: ");
				jogadores.setSuffix("§f " + Utils.convert(Action.getAvisos() * Action.getTempoEntreAnuncios()));
				
				Team kit1 = score.registerNewTeam("kit1");
				kit1.addEntry("§3");
				kit1.setPrefix("§4In Progress");
				kit1.setSuffix("");

				Team kit2 = score.registerNewTeam("kit2");
				kit2.addEntry("§b");
				kit2.setPrefix("§fKit2:");
				kit2.setSuffix("§3 " + SelectorKIT.returnKit(p, "kit2"));

				Team kills = score.registerNewTeam("kills");
				kills.addEntry("§a");
				kills.setPrefix("§6A:");
				// puxar as kills
				kills.setSuffix(" §f30");

				Team site = score.registerNewTeam("site");
				site.addEntry("§1");
				site.setPrefix("§6B: ");
				site.setSuffix("§f60");
				Team separator2 = score.registerNewTeam("sep2");
				separator2.addEntry("§4");
				separator2.setPrefix("§7§l────");
				separator2.setSuffix("§7§l────");

				Team separator3 = score.registerNewTeam("sep3");
				separator3.addEntry("§f");
				separator3.setPrefix("§7§l────");
				separator3.setSuffix("§7§l────");
				
				obj.getScore("§4").setScore(10);
				obj.getScore("§9").setScore(9);
				obj.getScore("§d").setScore(8);
				obj.getScore("   ").setScore(7);
				obj.getScore("§f").setScore(6);
				obj.getScore("").setScore(5);
				obj.getScore("§a").setScore(4);
				obj.getScore("§1").setScore(3);
				obj.getScore("  ").setScore(2);
				obj.getScore("§0").setScore(1);
				obj.getScore("§c").setScore(0);
			}
		}
		p.setScoreboard(score);
	}

	public void updatePlayer() {
		int secondsLeft = Action.getTime() -1;
		Action.time = secondsLeft;
		Thread th = new Thread(new Runnable() {
			public void run() {
				for (String p2 : Methodos.participantes) {
					Player p = Bukkit.getPlayer(p2);
					Scoreboard score = p.getScoreboard();
					Team tf = score.getTeam("wins");
					if (Methodos.kills.containsKey(p.getName())) {
						tf.setSuffix("§f " + Methodos.kills.get(p.getName()));
					}
					if (Action.comecou == false && Action.fecharservidor == true) {
						Team t = score.getTeam("midle");
						t.setSuffix("§f " + Utils.convert(secondsLeft));
					} else {
						if (Action.comecou == true) {
							Team t = score.getTeam("midle");
							if (secondsLeft == 0) {
								Action.time = Action.tempoarena;
								if (Action.getFim() == false) {
									new BukkitRunnable() {

										@Override
										public void run() {
											Methodos.stopEvent();
											return;
										}
									}.runTaskLater(Main.getInstance(), 20L * Action.getTempoArena());
								}
								Action.fim = true;
							}
							Team t2 = score.getTeam("jogadores");
							t2.setSuffix(" §f" + Methodos.participantes.size());
						} else {
							Team t = score.getTeam("midle");
							t.setSuffix(" §f" + Utils.convert(secondsLeft));
							Team t2 = score.getTeam("jogadores");
							t2.setSuffix(" §f" + Methodos.participantes.size());
						}
					}
				}
			}
		});
		th.start();
	}

}
