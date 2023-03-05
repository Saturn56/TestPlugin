package de.saturnmc.saturn.minigameserver.mointainipaio.Listener;


import de.saturnmc.saturn.minigameserver.mointainipaio.Groups.GroupPrefix;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatSystem implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        event.setFormat(GroupPrefix.getGPrefix(player) + " <%1$s>" + ChatColor.WHITE + " %2$s");
    }
}