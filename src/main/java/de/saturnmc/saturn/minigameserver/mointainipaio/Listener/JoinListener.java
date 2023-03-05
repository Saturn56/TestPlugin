package de.saturnmc.saturn.minigameserver.mointainipaio.Listener;

import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import de.saturnmc.saturn.minigameserver.mointainipaio.scorboard.TestScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class JoinListener implements Listener {

    private static Player p;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.getName() == "Saturn753"){
            String rprefix = ChatColor.BLUE + "[Coder]";
        }


        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Hallo und Herzlich Wilkommen auf meinem Server");
        event.setJoinMessage(ChatColor.RED + player.getName() + ChatColor.WHITE + " hat den Server betreten.");
        //TestInvCommand.giveNav(player);


        if (player.isOp()){
            player.setDisplayName("" + player.getDisplayName());
        }
        String name = ChatColor.RED + "Saturn";
        p = player;
        new TestScoreboard(player);
        //player.getInventory().setItem(0,Navi());
        List<String> SPStatus;


    }

    public void onJoint(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (player.getName() == "Saturn753"){
            String rprefix = ChatColor.BLUE + "[Coder]";
        }


        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Hallo und Herzlich Wilkommen auf meinem Server");
        event.setJoinMessage(ChatColor.RED + player.getName() + ChatColor.WHITE + " hat den Server betreten.");
        //TestInvCommand.giveNav(player);


        if (player.isOp()){
            player.setDisplayName("" + player.getDisplayName());
        }
        String name = ChatColor.RED + "Saturn";
        p = player;
        new TestScoreboard(player);
        //player.getInventory().setItem(0,Navi());
        List<String> SPStatus;


    }

    public static Player joinName() {
        return p;
    }

    public static ItemStack Navi(){
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Navi");
        itemMeta.setCustomModelData(987654321);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
