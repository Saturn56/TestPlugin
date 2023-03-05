package de.saturnmc.saturn.minigameserver.mointainipaio.Navi;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class getNavi {

    public static ItemStack Navi(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Navi");
        return itemStack;
    }
}
