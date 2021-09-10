package ml.lbplugins.hg.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;

import ml.lbplugins.hg.manager.Action;

public class Fisherman implements Listener {

	@EventHandler
	public void FishermanAbility(PlayerFishEvent e) {
		if (!(e.getCaught() instanceof Player))
			return;
		Player p = e.getPlayer();
		Player caught = (Player) e.getCaught();
		if (Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(p.getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(p.getName()).split(":");
		String kit1 = kitdojogador[0];
		String kit2 = kitdojogador[1];
		if (kit1.equalsIgnoreCase("Fisherman") || kit2.equalsIgnoreCase("Boxer")) {
			if (p.getItemInHand().getType() != Material.FISHING_ROD) return;
			caught.teleport(p.getLocation());
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (Action.getFechou() == false) {
			return;
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			if (!SelectorKIT.kit.containsKey(e.getDamager().getName())) {
				return;
			}
			String[] kitdojogador = SelectorKIT.kit.get(e.getDamager().getName()).split(":");
			String kit1 = kitdojogador[0];
			String kit2 = kitdojogador[1];
			if (kit1.equalsIgnoreCase("Fisherman") || kit2.equalsIgnoreCase("Boxer")) {
				Player p = (Player) e.getDamager();
				if (p.getItemInHand().getType().equals(Material.FISHING_ROD)) {

				}
			}
		}
	}

}
