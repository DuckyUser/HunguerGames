package ml.lbplugins.hg.kits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import ml.lbplugins.hg.Main;

public class Demoman implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (ml.lbplugins.hg.manager.Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(e.getPlayer().getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(e.getPlayer().getName()).split(":");
		String kit1 = kitdojogador[0];
		if (kit1.equalsIgnoreCase("Demoman")) {
			if (block.getType() == Material.STONE_PLATE) {
				block.setMetadata("demoman", new FixedMetadataValue(Main.getInstance(), player));
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();
		if (e.getAction() == Action.PHYSICAL) {
			if (block.hasMetadata("demoman")) {
				if (ml.lbplugins.hg.manager.Action.getFechou() == false) {
					return;
				}
				if (!SelectorKIT.kit.containsKey(e.getPlayer().getName())) {
					return;
				}
				String[] kitdojogador = SelectorKIT.kit.get(e.getPlayer().getName()).split(":");
				String kit1 = kitdojogador[0];
				if (kit1.equalsIgnoreCase("Demoman")) {
					if (block.getRelative(BlockFace.DOWN).getType() == Material.GRAVEL) {
						player.getWorld().createExplosion(player.getLocation(), 4.0F);
					}
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player player = (Player) e.getEntity();
		if (ml.lbplugins.hg.manager.Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(player.getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(player.getName()).split(":");
		String kit1 = kitdojogador[0];
		if (kit1.equalsIgnoreCase("Demoman")) {
			if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
					|| e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
				double d = e.getDamage();
				double porcent = 50.0D;
				if (player.isBlocking()) {
					porcent = 70.D;
				}
				e.setDamage(e.getDamage() - d / 100.0D * porcent);
			}
		}
	}

}
