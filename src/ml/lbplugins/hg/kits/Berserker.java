package ml.lbplugins.hg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ml.lbplugins.hg.manager.Action;

public class Berserker implements Listener {

	@EventHandler
	public void deathPlayer(PlayerDeathEvent e) {
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
			if (kit1.equalsIgnoreCase("Berserker")) {
				e.getEntity().getKiller().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2, 15));
				e.getEntity().getKiller().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2, 15));
			}
		}
	}

}
