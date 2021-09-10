package ml.lbplugins.hg.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;

public class Utils {

	public static void enviar(String title, String subtitle, int in, int out, int stay, Player p) {
		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
		IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subtitle + "'}");
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, in,
				stay, out);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
				subtitleJSON);
		connection.sendPacket(titlePacket);
		connection.sendPacket(subtitlePacket);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static void spawnMidle() {

		Player p = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random()))
				.findFirst().orElse(null);
		Double x = p.getLocation().getX() + 100.0;
		Double y = 67.0;
		Double z = p.getLocation().getZ() + 123.4;
		//Bukkit.broadcastMessage("");
		//Bukkit.broadcastMessage("�6�l[MINI FEAST]�e Corra para coletar itens especiais:");
		//Bukkit.broadcastMessage("�6�l[MINI FEAST]�e X: " + round(x, 1) + " Y: " + y + " Z: " + round(z, 1));
		//Bukkit.broadcastMessage("");
		Location loc = new Location(p.getWorld(), x, y, z);
		if (!loc.getChunk().isLoaded()) {
			loc.getChunk().load();
		}
		loc.getBlock().setType(Material.CHEST);
		Chest bau = (Chest) loc.getBlock().getState();
		bau.getInventory().addItem(new ItemStack(Material.DIAMOND));
		// seleciona os itens que vao para o bau
	}

	public static String convert(int secs) {
		long h = secs / 3600, i = secs - h * 3600, m = i / 60, s = i - m * 60;
		String timeF = "";

		if (m == 0) {
			timeF = timeF + "0:";
		}
		if (m != 0) {
			timeF = timeF + m + ":";
		}
		if (s != 0 && s < 10) {
			timeF = timeF + "0" + s;
		} else {
			if (s != 0) {
				timeF = timeF + s + "";
			}
		}
		if (s == 0) {
			timeF = timeF + "00";
		}
		return timeF;
	}
}
