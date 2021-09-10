package ml.lbplugins.hg.manager;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ml.lbplugins.hg.kits.SelectorKIT;

public class CustomJoinIten implements Listener {

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (Action.getFechou() == false) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onTabComplete(PlayerChatTabCompleteEvent e) {
		e.getTabCompletions().clear();
	}


	@EventHandler
	public void onHit(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Kit [1]")) {
			if (e.getCurrentItem().getType()==null)return;
			e.setCancelled(true);
			if (SelectorKIT.kit.containsKey(p.getName())) {
				String kitdojogador = SelectorKIT.kit.get(p.getName());
				String[] kits = kitdojogador.split(":");
				String kit1 = e.getCurrentItem().getItemMeta().getDisplayName();
				String kit2 = kits[1];
				if (kit1.equalsIgnoreCase(kit2)) {
					p.closeInventory();
					p.sendMessage("§cN§o pode utilizar o mesmo kit!");
					return;
				}
				String finalkit = kit1 + ":" + kit2;
				SelectorKIT.kit.put(p.getName(), finalkit);
				p.sendMessage("§aSelected kit:§f " + kit1);
			} else {
				String kit1 = e.getCurrentItem().getItemMeta().getDisplayName();
				String finalkit = kit1 + ":" + "Nenhum";
				SelectorKIT.kit.put(p.getName(), finalkit);
				p.sendMessage("§aSelected kit:§f " + kit1);
			}
			p.closeInventory();
		}
		/*if (e.getInventory().getTitle().equalsIgnoreCase("§8Kit [2]")) {
			e.setCancelled(true);
			if (SelectorKIT.kit.containsKey(p.getName())) {
				String kitdojogador = SelectorKIT.kit.get(p.getName());
				String[] kits = kitdojogador.split(":");
				String kit1 = kits[0];
				String kit2 = e.getCurrentItem().getItemMeta().getDisplayName();
				if (kit1.equalsIgnoreCase(kit2)) {
					p.closeInventory();
					p.sendMessage("§cN§o pode utilizar o mesmo kit!");
					return;
				}
				String finalkit = kit1 + ":" + kit2;
				SelectorKIT.kit.put(p.getName(), finalkit);
				p.sendMessage("§aKit 2 selecionado:§a " + kit2);
			} else {
				String kit2 = e.getCurrentItem().getItemMeta().getDisplayName();
				String finalkit = "Nenhum" + ":" + kit2;
				SelectorKIT.kit.put(p.getName(), finalkit);
				p.sendMessage("§aKit 2 selecionado:§a " + kit2);
			}
			p.closeInventory();
		}*/
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Action.getFechou() == false) {
			if (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_AIR
					|| e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand() != null && p.getItemInHand().getType().equals(Material.CHEST)) {
					if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
						if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6Choose Kit")) {
							SelectorKIT.openKITS(p, 1);
						}
						if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6Selecionador de KIT [2]")) {
							SelectorKIT.openKITS(p, 2);
						}
					}
				}
			}
		}
	}

	public static void setItem(Player p) {
		p.getInventory().clear();
		for (ItemStack i2 : p.getInventory().getArmorContents()) {
			i2.setType(Material.AIR);
		}
		ItemStack i = new ItemStack(Material.CHEST);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("§6Choose Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§fClick to select your kit.");
		im.setLore(lore);
		i.setItemMeta(im);

		ItemStack i2 = new ItemStack(Material.CHEST);
		ItemMeta im2 = i2.getItemMeta();
		im2.setDisplayName("§6Selecionador de KIT [2]");
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add("§fClique para selecionar seu kit.");
		im2.setLore(lore2);
		i2.setItemMeta(im2);

		p.getInventory().setItem(0, i);
	//	p.getInventory().setItem(1, i2);
	}
}
