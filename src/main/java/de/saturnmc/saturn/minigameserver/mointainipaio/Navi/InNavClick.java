package de.saturnmc.saturn.minigameserver.mointainipaio.Navi;

import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InNavClick implements Listener {

    @EventHandler
    public void onInNavClick(InventoryClickEvent event){

        if (event.getClickedInventory() == null){
            return;
        }

        Inventory inventory = event.getClickedInventory();
        if (inventory.getItem(8) == null){
            return;
        }
        ItemStack itemStack = inventory.getItem(8);

        ItemMeta itemMeta = itemStack.getItemMeta();
        Player player = (Player) event.getWhoClicked();
        int level = itemMeta.getCustomModelData();
        if (level != 11){
            return;
        }
        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();
        if (slot == 23){
            player.sendMessage("Hallo");
            Main.getInstance().sendToServer(player, "SUtils");
        }
        event.setCancelled(true);
    }
}
