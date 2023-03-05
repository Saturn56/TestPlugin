package de.saturnmc.saturn.minigameserver.mointainipaio.Groups;


import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class GroupCommand implements CommandExecutor {

    //without namechanges
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int PLevel = -2;

        Player psender = null;

        if (sender.getName().equals("Saturn753")) {
            psender = (Player) sender;
            PLevel =200;
        }else if (sender instanceof  Player){
            psender = (Player) sender;
            PLevel = GroupLevel.getGLevel(Main.getInstance().getGroup(psender.getUniqueId().toString()));
        }else{
            PLevel = 1000;
        }

        //psender.sendMessage(String.valueOf(PLevel));
        //psender.sendMessage(Main.getInstance().getGroup(psender.getUniqueId().toString()));


        if (PLevel < 70  & psender.getName() != "Saturn753") {
            psender.sendMessage("du hast keine rechte dazu");
            return false;
        }


        String parameter = "0";
        String player = "0";
        String group = "0";
        Integer AEingabe = 0;


        if (args.length < 1) {
            psender.sendMessage("Bitte gib eine Anweisung(<set/get/list>) ein.");
        } else {
            parameter = args[0];
        }

        if (parameter == "get") {
            AEingabe = 1;
        } else if (parameter == "set") {
            AEingabe = 2;
        } else if (parameter == "list") {
            AEingabe = 0;
        }

        if (args.length < 2) {
            if (AEingabe >= 1) {
                psender.sendMessage("Bitte gib einen Namen ein.");
            }
        } else if (args.length >= 2) {
            player = args[1];
        }

        if (args.length < 3) {
            if (AEingabe >= 2) {
                psender.sendMessage("Bitte gib eine Gruppe ein.");
            }
        } else if (args.length >= 3) {
            group = args[2];
        }


        if (Objects.equals(parameter, "set")) {
            //get the UUID
            String PlayerUUID = Main.getInstance().getPofString(args[1]).getUniqueId().toString();
            if (PLevel <= GroupLevel.getGLevel(args[2]) & psender.getName() != "Saturn753"){
                psender.sendMessage("Du brauchst ein höheres Level");
                return false;
            }

            Player pplayer = (Player) sender;

            if (PLevel <= GroupLevel.getGLevel(Main.getInstance().getGroup(PlayerUUID)) & psender.getName() != "Saturn753"){
                psender.sendMessage("Du brauchst ein höheres Level.");
                return false;
            }

            psender.sendMessage("der Rang von "+ args[1] + " wurde zu" + args[2] + " geändert");

            Main.getInstance().setGroup(PlayerUUID, args[2], (Player) sender);
            return true;
        }

        if (Objects.equals(parameter, "get")) {
            String PlayerUUID = Main.getInstance().getPofString(args[1]).getUniqueId().toString();
            String string = Main.getInstance().getGroup(PlayerUUID);
            psender.sendMessage(string);
            return true;
        }

        if (Objects.equals(parameter, "list")) {
            List<String> list = Main.getInstance().getGroupList();
            psender.sendMessage(String.valueOf(list));


            psender.sendMessage("not fully implemented yet");
            return true;
        }


        return false;
    }
}
