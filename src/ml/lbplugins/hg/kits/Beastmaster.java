package ml.lbplugins.hg.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import ml.lbplugins.hg.manager.Action;

public class Beastmaster implements Listener {

	@EventHandler
	public void Interactpl(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(e.getPlayer().getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(e.getPlayer().getName()).split(":");
		String kit1 = kitdojogador[0];
		if (kit1.equalsIgnoreCase("Beastmaster")) {
		}
	}

}
