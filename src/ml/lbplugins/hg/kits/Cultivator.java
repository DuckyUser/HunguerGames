package ml.lbplugins.hg.kits;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Crops;

import ml.lbplugins.hg.manager.Action;

public class Cultivator implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(e.getPlayer().getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(e.getPlayer().getName()).split(":");
		String kit1 = kitdojogador[0];
		if (kit1.equalsIgnoreCase("Cultivator")) {
			Block block = e.getBlock();
			if (block.getType() == Material.SAPLING) { 
				block.setType(Material.AIR);
				block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
			}
			else if (block.getType() == Material.CROPS) { 
				block.setData((byte)7);
			}

		}
	}

}
