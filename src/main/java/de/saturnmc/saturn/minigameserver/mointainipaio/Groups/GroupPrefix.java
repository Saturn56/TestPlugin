package de.saturnmc.saturn.minigameserver.mointainipaio.Groups;

import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import it.unimi.dsi.fastutil.chars.Char2DoubleMaps;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GroupPrefix {
    public static String getGPrefix(Player player){

        String PlayerUUID = player.getUniqueId().toString();
        if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Admin+")){
            String string = ChatColor.BOLD + "[" + ChatColor.RED + "Admin" + ChatColor.WHITE + "" + ChatColor.BOLD + "]" + ChatColor.GOLD;
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Coder+")){
            String string = ChatColor.BOLD + "[" + ChatColor.RED + "Coder" + ChatColor.WHITE + "" + ChatColor.BOLD + "]" + ChatColor.GOLD;
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Admin")){
            String string = ChatColor.WHITE + "[" + ChatColor.RED + "Admin" + ChatColor.WHITE + "]" + ChatColor.GREEN;
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Coder")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "Coder" + ChatColor.WHITE + "]" + ChatColor.GREEN;
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Mod")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "Moderator" + ChatColor.WHITE + "]" + ChatColor.GREEN;
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Premium+")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "Premium+" + ChatColor.WHITE + "]";
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "Premium")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "Premium" + ChatColor.WHITE + "]";
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "VIP+")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "VIP+" + ChatColor.WHITE + "]";
            return string;
        }else if (Objects.equals(Main.getInstance().getGroup(PlayerUUID), "VIP")){
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "VIP" + ChatColor.WHITE + "]";
            return string;
        }else {
            String string = ChatColor.WHITE + "[" + ChatColor.BLUE + "Spieler" + ChatColor.WHITE + "]";
            return string;
        }
    }

}
