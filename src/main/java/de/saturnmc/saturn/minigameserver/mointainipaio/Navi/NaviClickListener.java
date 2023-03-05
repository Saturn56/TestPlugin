package de.saturnmc.saturn.minigameserver.mointainipaio.Navi;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class NaviClickListener implements Listener {
    @EventHandler
    public void onNavClick(PlayerInteractEvent event) {
        if (!event.hasItem()){
            return;
        }
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();

        if (itemStack == null){
            return;
        }
        if(action != Action.LEFT_CLICK_AIR & action != Action.LEFT_CLICK_BLOCK & action != Action.RIGHT_CLICK_AIR & action != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(!itemMeta.hasCustomModelData()){
            return;
        }
        if (itemMeta.getCustomModelData() != 987654321){
            return;
        }
        player.openInventory(inventoryNav());
    }

    public Inventory inventoryNav(){
        Inventory inv = Bukkit.createInventory(null, 54, "Navigator");
        for (int i = 0; i < 54; i++) {
            inv.setItem(i, createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", 0));
        }
        inv.setItem(8, createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ",11));
        inv.setItem(23, createItem(Material.FURNACE, "SUtils", 22));


        return inv;
    }


    public ItemStack createItem(Material material, String name, int data){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(data);
        item.setItemMeta(itemMeta);
        return item;
    }

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
        if(e.getCurrentItem().getType() == Material.NETHER_STAR & e.getCurrentItem().getItemMeta().getCustomModelData() == 987654321) {
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
        if(e.getItem().getType() == Material.NETHER_STAR & e.getItem().getItemMeta().getCustomModelData() == 987654321) {
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
        if(e.getItemDrop().getItemStack().getType() == Material.NETHER_STAR & e.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 987654321) {
            e.setCancelled(true);
        }

    }


}
