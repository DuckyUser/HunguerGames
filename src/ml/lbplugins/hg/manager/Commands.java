package ml.lbplugins.hg.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import ml.lbplugins.hg.Main;

public class Commands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
			
			if (cmd.getName().equals("tst")) {
				
				Player p = (Player)sender;
				
				if (!p.hasPermission("tst.staff")) {
					return true;
				}
				
				Main.getInstance().getConfig().set("Spawn.W", p.getLocation().getWorld().getName());
				Main.getInstance().getConfig().set("Spawn.X", p.getLocation().getX());
				Main.getInstance().getConfig().set("Spawn.Y", p.getLocation().getY());
				Main.getInstance().getConfig().set("Spawn.Z", p.getLocation().getZ());
				Main.getInstance().saveConfig();
				p.sendMessage("§aSaved");
			}
		}
		
		return false;
	}
	

}
