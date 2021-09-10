package ml.lbplugins.hg.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectorKIT {

	public static HashMap<String, String> kit = new HashMap<String, String>();

	public static String returnKit(Player p, String kitin) {

		if (kit.containsKey(p.getName())) {
			String[] kitfinal = kit.get(p.getName()).split(":");
			switch (kitin) {
			case "kit1":
				kitin = kitfinal[0];
				break;
			default:
				break;
			}
		} else {
			kitin = "Nenhum";
		}
		return kitin;
	}

	// pvp, minerador, lenhador, boxer, arqueiro, gelado, domesticador
	// poseidon, viking, teleport, grandpa, thor

	public static void giveKIT(Player p) {
		p.getInventory().clear();
		for (String kitget : kit.keySet()) {
			String[] kitdojogador = kit.get(kitget).split(":");
			String kit1 = kitdojogador[0];
			String kit2 = kitdojogador[1];
			if (kit1.equalsIgnoreCase("PvP") || kit2.equalsIgnoreCase("PvP")) {
				p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
			}
			if (kit1.equalsIgnoreCase("Archer") || kit2.equalsIgnoreCase("Minerador")) {
				p.getInventory().addItem(new ItemStack(Material.BOW));
				p.getInventory().addItem(new ItemStack(Material.ARROW, 10));
			}
			if (kit1.equalsIgnoreCase("Barbarian")) {
				p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
			}
			if (kit1.equalsIgnoreCase("Beastmaster")) {
				p.getInventory().addItem(new ItemStack(Material.BONE, 3));
				p.getInventory().addItem(new ItemStack(Material.MONSTER_EGG, 3, EntityType.WOLF.getTypeId()));
			}
			if (kit1.equalsIgnoreCase("Grandpa")) {
				ItemStack g = new ItemStack(Material.STICK);
				ItemMeta gm =g.getItemMeta();
				gm.addEnchant(Enchantment.KNOCKBACK, 2, true);
				g.setItemMeta(gm);
				p.getInventory().addItem(g);
			}
		}
	}

	public static void openKITS(Player p, int nivel) {

		ItemStack pvp = new ItemStack(Material.STONE_SWORD);
		ItemMeta pvpm = pvp.getItemMeta();
		pvpm.setDisplayName("PvP");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Start with honor and");
		lore.add("§7receive a simple pvp sword");
//		pvpm.setLore(lore);
		pvp.setItemMeta(pvpm);

		ItemStack minerador = new ItemStack(Material.BOW);
		ItemMeta mineradorm = minerador.getItemMeta();
		mineradorm.setDisplayName("Archor");
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add("§7Sua melhor arma § a");
		lore2.add("§7minera§§o. A picareta de");
		lore2.add("§7ferro ir§ te ajudar nessa miss§o.");
	//	mineradorm.setLore(lore2);
		minerador.setItemMeta(mineradorm);

		ItemStack lenhador = new ItemStack(Material.WOOD_SWORD);
		ItemMeta lenhadorm = lenhador.getItemMeta();
		lenhadorm.setDisplayName("Barbarian");
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add("§7Colete madeiras com");
		lore3.add("§7muita r§pidez. N§o perca");
		lore3.add("§7tempo com isso.");
		//lenhadorm.setLore(lore3);
		lenhador.setItemMeta(lenhadorm);

		ItemStack boxer = new ItemStack(Material.SPIDER_EYE);
		ItemMeta boxerm = boxer.getItemMeta();
		boxerm.setDisplayName("Berserker");
		ArrayList<String> lore4 = new ArrayList<String>();
		lore4.add("§7De socos fortes sem itens");
		lore4.add("§7na m§o.");
	//	boxerm.setLore(lore4);
		boxer.setItemMeta(boxerm);

		ItemStack arqueiro = new ItemStack(Material.BONE);
		ItemMeta arqueirom = arqueiro.getItemMeta();
		arqueirom.setDisplayName("Beastmaster");
		ArrayList<String> lore5 = new ArrayList<String>();
		lore5.add("§7Alcance seus inimigos de");
		lore5.add("§7longe. Voc§ recebe um arco");
		lore5.add("§7com algumas flechas.");
		//arqueirom.setLore(lore5);
		arqueiro.setItemMeta(arqueirom);

		@SuppressWarnings("deprecation")
		ItemStack domesticador = new ItemStack(Material.SAPLING);
		ItemMeta domesticadorm = domesticador.getItemMeta();
		domesticadorm.setDisplayName("Cultivator");
		ArrayList<String> lore6 = new ArrayList<String>();
		lore6.add("§7Lobos s§o seus maiores");
		lore6.add("§7aliados nessa luta, eles");
		lore6.add("§7podem lutar para voc§.");
	//	domesticadorm.setLore(lore6);
		domesticador.setItemMeta(domesticadorm);
		
		ItemStack poseidon = new ItemStack(Material.GOLD_PLATE);
		ItemMeta poseidonm = poseidon.getItemMeta();
		poseidonm.setDisplayName("Demoman");
		ArrayList<String> lore7 = new ArrayList<String>();
		lore7.add("§7Fique mais forte dentro da");
		lore7.add("§7§gua.");
		//poseidonm.setLore(lore7);
		poseidon.setItemMeta(poseidonm);
		
		ItemStack viking = new ItemStack(Material.FISHING_ROD);
		ItemMeta vikingm = viking.getItemMeta();
		vikingm.setDisplayName("Fisherman");
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add("§7Com machados de v§rios");
		lore1.add("§7tipos voc§ fica muito mais");
		lore1.add("§7forte.");
		//vikingm.setLore(lore1);
		viking.setItemMeta(vikingm);
		
		ItemStack grandpa = new ItemStack(Material.STICK);
		ItemMeta grandpam = grandpa.getItemMeta();
		grandpam.setDisplayName("Grandpa");
		ArrayList<String> lore8 = new ArrayList<String>();
		lore8.add("§7Ganhe um stick e seja capaz");
		lore8.add("§7de empurrar seus inimigos");
		lore8.add("§7para longe.");
		//grandpam.setLore(lore8);
		grandpa.setItemMeta(grandpam);
		
		ItemStack stomper = new ItemStack(Material.DIRT);
		ItemMeta stomperm = stomper.getItemMeta();
		stomperm.setDisplayName("Stomper");
		stomper.setItemMeta(stomperm);
		

		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§8Kit [" + nivel + "]");
		inv.setItem(10, pvp);
		inv.setItem(11, minerador);
		inv.setItem(12, lenhador);
		inv.setItem(13, boxer);
		inv.setItem(14, arqueiro);
		inv.setItem(15, domesticador);
		inv.setItem(16, poseidon);
		inv.setItem(19, viking);
		inv.setItem(20, grandpa);
		inv.setItem(21, stomper);
		
		p.openInventory(inv);
	}
}
