package ml.lbplugins.hg.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ml.lbplugins.hg.manager.Action;

public class Barbarian implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (Action.getFechou() == false) {
			return;
		}
		if (e.getEntity().getKiller() instanceof Player) {
			if (!SelectorKIT.kit.containsKey(e.getEntity().getKiller().getName())) {
				return;
			}
			String[] kitdojogador = SelectorKIT.kit.get(e.getEntity().getKiller().getName()).split(":");
			String kit1 = kitdojogador[0];
			if (kit1.equalsIgnoreCase("Barbarian")) {
				if (p.getItemInHand().getType().equals(Material.WOOD_SWORD)) {
					p.getItemInHand().setType(Material.STONE_SWORD);
				} else {
					if (p.getItemInHand().getType().equals(Material.STONE_SWORD)) {
						p.getItemInHand().setType(Material.GOLD_SWORD);
					} else {
						if (p.getItemInHand().getType().equals(Material.GOLD_SWORD)) {
							p.getItemInHand().setType(Material.IRON_SWORD);
						} else {
							p.getItemInHand().setType(Material.DIAMOND_SWORD);
						}
					}
				}
			}
		}
	}
}
