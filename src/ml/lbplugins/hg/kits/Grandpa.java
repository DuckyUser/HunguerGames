package ml.lbplugins.hg.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import ml.lbplugins.hg.manager.Action;

public class Grandpa implements Listener {

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
			if (kit1.equalsIgnoreCase("Grandpa") || kit2.equalsIgnoreCase("Grandpa")) {
				Player mat = (Player)e.getDamager();
				if (mat.getItemInHand() != null && mat.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
					if (Math.random() < 0.1) {
						Vector unitVector = new Vector(e.getEntity().getLocation().getDirection().getX(), 0,
								e.getEntity().getLocation().getDirection().getZ());
						unitVector = unitVector.normalize();
						e.getEntity().setVelocity(unitVector.multiply(2));
					}
				}
			}
		}

	}
}
