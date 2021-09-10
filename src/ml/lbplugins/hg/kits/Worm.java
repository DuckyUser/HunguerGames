package ml.lbplugins.hg.kits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ml.lbplugins.hg.manager.Action;

public class Worm implements Listener {
	@EventHandler
	public void onBlock(BlockDamageEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (Action.getFechou() == false) {
			return;
		}
		if (!SelectorKIT.kit.containsKey(player.getName())) {
			return;
		}
		String[] kitdojogador = SelectorKIT.kit.get(player.getName()).split(":");
		String kit1 = kitdojogador[0];
		String kit2 = kitdojogador[1];
		if (kit1.equalsIgnoreCase("Worm")) {
			if (block.getType() == Material.DIRT) {
				boolean dr = true;
				if (((CraftPlayer) player).getHealth() < 20.0D) {
					double hp = ((CraftPlayer) player).getHealth() + 1.0D;
					if (hp > 20.0D) {
						hp = 20.0D;
					}
					player.setHealth((int) hp);
					dr = true;
				} else if (player.getFoodLevel() < 20) {
					player.setFoodLevel(player.getFoodLevel() + 1);
					dr = true;
				} else {
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1), false);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1), false);
					block.setType(Material.AIR);
					if (dr) {
						block.getWorld().dropItemNaturally(block.getLocation().add(0.5D, 0.0D, 0.5D),
								new ItemStack(Material.DIRT));
					}
				}
			}
		}
	}
}
