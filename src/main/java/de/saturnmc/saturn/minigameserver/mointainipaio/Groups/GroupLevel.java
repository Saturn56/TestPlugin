package de.saturnmc.saturn.minigameserver.mointainipaio.Groups;


import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GroupLevel {
    public static int getGLevel(String gName){
        if (Objects.equals(gName, "Admin+")){
            int string = 100;
            return string;
        }else if (Objects.equals(gName, "Coder+")){
            int string = 100;
            return string;
        }else if (Objects.equals(gName, "Admin")){
            int string = 90;
            return string;
        }else if (Objects.equals(gName, "Coder")){
            int string = 80;
            return string;
        }else if (Objects.equals(gName, "Mod")){
            int string = 70;
            return string;
        }else if (Objects.equals(gName, "Premium+")){
            int string = 5;
            return string;
        }else if (Objects.equals(gName, "Premium")){
            int string = 4;
            return string;
        }else if (Objects.equals(gName, "VIP+")){
            int string = 3;
            return string;
        }else if (Objects.equals(gName, "VIP")){
            int string = 2;
            return string;
        }else if (Objects.equals(gName, "Banned")){
            int string = 0;
            return string;
        }else{
            int string = 1;
            return string;
        }
    }

}
