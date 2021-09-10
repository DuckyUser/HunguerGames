package ml.lbplugins.hg.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Archer implements Listener {

	public static void loadArcher() {
		ItemStack bottle = new ItemStack(Material.ARROW, 4);

		ShapedRecipe expBottle = new ShapedRecipe(bottle);

		expBottle.shape("% ", " % ", "  %");

		expBottle.setIngredient('%', Material.STICK);

		Bukkit.getServer().addRecipe(expBottle);

	}

}
