package ml.lbplugins.hg.kits;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import ml.lbplugins.hg.manager.Action;

public class Stomper implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerFallStomper(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player player = (Player) e.getEntity();
		if (Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(player.getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(player.getName()).split(":");
		String kit1 = kitdojogador[0];
		String kit2 = kitdojogador[1];
		if (kit1.equalsIgnoreCase("Stomper")) {
			for (Entity et : player.getNearbyEntities(6.0D, 3.0D, 6.0D)) {
				if (et instanceof Player) {
					Player d = (Player) et;
					if (d == player)
						continue;
					if (d.isSneaking()) {
						d.damage(0.1D, player);
						d.damage(3.9D);
					} else {
						d.damage(0.1D, player);
						d.damage(player.getFallDistance() - 8.1F);
					}
				}
			}
			Location player_location = player.getLocation();
			int radius = 2;
			for (int i = 0; i < 6; i++) {
				for (double x = -radius; x <= radius; x = x + 1.0D) {
					for (double z = -radius; z <= radius; z = z + 1.0D) {
						Location effect_location = new Location(player_location.getWorld(), player_location.getX() + x,
								player_location.getY(), player_location.getZ() + z);
						effect_location.getWorld().playEffect(effect_location, Effect.WITCH_MAGIC, 500);
					}
				}
			}
			if (e.getDamage() > 4.0D) {
				e.setDamage(4.0D);
			}
		}
	}

}
