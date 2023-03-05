package de.saturnmc.saturn.minigameserver.mointainipaio.Listener;


import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class ItemListener implements Listener {

    List<Integer> cmdList = Main.getInstance().getConfig().getIntegerList("Code.antDrop");

    @EventHandler
    public void LockInv(InventoryClickEvent e) {
        if (e.getWhoClicked().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if (e.getCurrentItem() == null){
            return;
        }
        if (!e.getCurrentItem().hasItemMeta()){
            return;
        }
        if (!e.getCurrentItem().getItemMeta().hasCustomModelData()){
            return;
        }
        if(cmdList.contains(e.getCurrentItem().getItemMeta().getCustomModelData())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void LockInve(PlayerInteractEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if (e.getItem() == null){
            return;
        }
        if(!e.getItem().getItemMeta().hasCustomModelData()){
            return;
        }
        if(cmdList.contains(e.getItem().getItemMeta().getCustomModelData())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void LockInvento(PlayerDropItemEvent e){
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE){
            return;
        }
        if(!e.getItemDrop().getItemStack().getItemMeta().hasCustomModelData()){
            return;
        }
        if(cmdList.contains(e.getItemDrop().getItemStack().getItemMeta().getCustomModelData())) {
            e.setCancelled(true);
        }

    }
}
