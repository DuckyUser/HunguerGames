package ml.lbplugins.hg.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;

import ml.lbplugins.hg.Main;
import ml.lbplugins.hg.manager.Action;
import ml.lbplugins.hg.manager.Methodos;
import ml.lbplugins.hg.manager.MethodosInPlay;

public class generalListener implements Listener {

	// moveevent muito longe da localiza§§o inicial

	@EventHandler
	public void onPlaceBlocK(BlockPlaceEvent e) {
		if (Action.getFechou() == false) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void cre(CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Monster) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void semChuva(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (Action.getFechou() == false) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (Action.getIniciou() == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		if (Action.getIniciou() == true) {
			Methodos.participantes.remove(p.getName());
			int restantes = Methodos.participantes.size();
			if (e.getEntity().getKiller() instanceof Player) {
				Bukkit.broadcastMessage("§8" + p.getName() + "§e just died to §7" + e.getEntity().getKiller()
						+ "§e. Remain:§6 " + restantes);
				MethodosInPlay.darkill(e.getEntity().getKiller());
			} else {
				Bukkit.broadcastMessage("§8" + p.getName() + "§e died of unknown causes. Remain:§6 " + restantes);
				// move pro lobby
			}
			Methodos.checkFinal(p);
		}
	}

	@EventHandler
	public void onHitFriend(EntityDamageByEntityEvent e) {
		// VERIFICA SE O PVP J§ FOI LIBERADO PARA HITAR O INIMIGO
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			if (Action.getIniciou() == false) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		for (String cmds : Main.getInstance().getConfig().getStringList("Block_CMDS")) {
			if (e.getMessage().contains(cmds.toLowerCase())) {
				e.setCancelled(true);
			}
			return;
		}

	}

	@EventHandler
	public void cmderrado(PlayerCommandPreprocessEvent e) {
		if ((!e.isCancelled()) || (e.isAsynchronous())) {
			String cmd = e.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if ((topic == null)) {
				e.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void onQuitPlayer(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		Methodos.checkFinal(p);
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getTypeId() == 282 && (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_AIR
				|| e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)) {// check right click and item in
																						// hand is soup
			e.setCancelled(true);// cancel the drinking
			if (p.getHealth() < 20.0) {// check his health wont be above max when finish drinking
				double vidanova = (p.getHealth() > 17.0 ? p.getMaxHealth() : p.getHealth() + 3.0);
				p.setHealth(vidanova);// add health - 6.0 means 3 hearts, u have to put .0 beacuse of the
										// update
				ItemStack is = new ItemStack(0); // create itemstack
				is.setTypeId(281); // make the itemstack empty bowl
				if (p.getItemInHand().getAmount() > 1) {// check if there is a stack of soups
					p.getInventory().addItem(is);// add the empty bowl to inventory
					is.setTypeId(282);// change the empty bowl to soup to lower the current soups amount
					is.setAmount(p.getItemInHand().getAmount() - 1);// set the soup's amount to the soups in hand amount
																	// -1
					p.setItemInHand(is);// set item in hand to new soup stack
				} else// if he has only 1 soup in hand
					p.setItemInHand(is);// replace item in hand to empty bowl
			}
		}
	}

	// board limited
	@EventHandler
	public void onMovea(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (Main.getInstance().getConfig().contains("Spawn")) {
			Double x = Main.getInstance().getConfig().getDouble("Spawn.X");
			Double z = Main.getInstance().getConfig().getDouble("Spawn.Z");
			if (p.getLocation().getBlockX() > x + 501) {
				p.damage(4.0D);
				p.sendMessage("§cVocê está na borda do mundo!");
			} else if (p.getLocation().getBlockX() < -(501 - x)) {
				p.damage(4.0D);
				p.sendMessage("§cVocê está na borda do mundo!");
			}
			if (p.getLocation().getBlockZ() > z + 501) {
				p.damage(4.0D);
				p.sendMessage("§cVocê está na borda do mundo!");
			} else if (p.getLocation().getBlockZ() < -(501 - z)) {
				p.damage(4.0D);
				p.sendMessage("§cVocê está na borda do mundo!");
			}
		}
	}

	@EventHandler
	public void onJoinFato(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Methodos.checkJoin(p);
		e.setJoinMessage(null);
		// TabTitleManager.sendTablist(p, "§c§lSpider\n",
		// "\n§cSite: §eloja.spidermc.com.br\n§cDiscord:§e discord.gg/spidermc");
	}

	@EventHandler
	public void onJoinPlayer(AsyncPlayerPreLoginEvent e) {
		if (Action.getReiniciando() == true) {
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§eRestarting Server...");
			return;
		}
		if (Action.getFechou() == true) {
			// final String name = e.getName();
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
					"§cThe event has already started... try again later.");
			return;
		}
	}

}
