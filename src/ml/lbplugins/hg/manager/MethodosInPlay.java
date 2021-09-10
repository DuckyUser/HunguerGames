package ml.lbplugins.hg.manager;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class MethodosInPlay implements Listener {

	@SuppressWarnings("rawtypes")
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		if (p.getItemInHand() != null && p.getItemInHand().getType().equals(Material.COMPASS)) {
			List<Entity> near = p.getNearbyEntities((double) 300, (double) 300, (double) 300);
			Iterator var6 = near.iterator();

			while (var6.hasNext()) {
				Entity entity = (Entity) var6.next();
				if (entity instanceof Player) {
					Player nearestPlayer = (Player) entity;
					p.setCompassTarget(nearestPlayer.getLocation());
					p.sendMessage("§7Pointing out§f " + nearestPlayer.getDisplayName());
					return;
				}
			}
		}
	}

	public static void darkill(Player p) {
		if (Methodos.kills.containsKey(p.getName())) {
			int kills = Methodos.kills.get(p.getName()) + 1;
			Methodos.kills.put(p.getName(), kills);
		}
	}

}
