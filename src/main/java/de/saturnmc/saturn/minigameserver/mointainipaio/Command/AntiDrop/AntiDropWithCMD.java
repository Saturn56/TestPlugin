package de.saturnmc.saturn.minigameserver.mointainipaio.Command.AntiDrop;

import de.saturnmc.saturn.minigameserver.mointainipaio.Groups.GroupLevel;
import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.security.ProtectionDomain;
import java.util.List;
import java.util.Objects;

public class AntiDropWithCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 2){
            return false;
        }

        FileConfiguration config = Main.getInstance().getConfig();
        int PLevel = 0;
        if (sender instanceof Player player) {

            PLevel = GroupLevel.getGLevel(Main.getInstance().getGroup(player.getUniqueId().toString()));
            if(PLevel < 80){
                player.sendMessage("du hast keine rechte dazu");
                return false;
            }
        }

        Integer integer = Integer.valueOf(args[1]);
        List<Integer> cmdList = Main.getInstance().getConfig().getIntegerList("Code.antDrop");
        if(Objects.equals(args[0], "add") & !cmdList.contains(integer)) {
            cmdList.add(integer);
        } else if (Objects.equals(args[0], "remove") & cmdList.contains(integer)) {
            cmdList.remove(integer);
        } else {
            return false;
        }
        Main.getInstance().getConfig().set("Code.antDrop", cmdList);
        config.options().copyDefaults(true);
        Main.getInstance().saveConfig();

        return true;
    }
}
