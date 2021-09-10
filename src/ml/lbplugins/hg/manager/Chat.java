package ml.lbplugins.hg.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

@SuppressWarnings("deprecation")
public class Chat implements Listener {

	@EventHandler
	public void onc(PlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		for (Player p2 : Bukkit.getOnlinePlayers()) {
			String prefix = PermissionsEx.getUser(p.getName()).getPrefix().replace("&", "§");
			p2.sendMessage(prefix + "" + p.getName() + ":§f " + e.getMessage().toLowerCase());
		}
	}

}
