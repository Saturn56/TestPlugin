package de.saturnmc.saturn.minigameserver.mointainipaio.scorboard;

import de.saturnmc.saturn.minigameserver.mointainipaio.Listener.JoinListener;
import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class TestScoreboard extends scorboardBuilder{

    private int socialId;

    public TestScoreboard(Player player) {
        super(player, ChatColor.RED + "MountainIP");

        socialId = 0;
        run();
    }

    @Override
    public void createScoreboard() {

        setScore("", 8);
        setScore(ChatColor.DARK_GRAY.toString(), 7);
        setScore(ChatColor.BLUE + "Dein Rang" + ChatColor.DARK_GRAY + ":", 6);
        if(JoinListener.joinName().isOp()) {
            setScore(ChatColor.RED + "Operator", 5);
        } else {
            setScore(ChatColor.GRAY + "Spieler", 5);
        }
        setScore(ChatColor.GRAY.toString(), 4);
        setScore(ChatColor.AQUA + "MountainIP.ploudos.me", 3);
        setScore(ChatColor.RED.toString(), 2);
        setScore(ChatColor.RED + JoinListener.joinName().getName(), 1);
        setScore(ChatColor.AQUA.toString(), 0);
    }

    @Override
    public void update(){

    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                switch (socialId) {
                    case 0:
                        removeScore(3);
                        setScore(ChatColor.AQUA + "MountainIP.ploudos.me", 3);

                        break;
                    case 1:
                        removeScore(3);
                        setScore(ChatColor.DARK_PURPLE + "MountainIP.ploudos.me", 3);
                        break;
                    case 2:
                        removeScore(3);
                        setScore(ChatColor.DARK_RED + "MountainIP.ploudos.me", 3);
                        break;
                }

                socialId++;

                if (socialId >= 3) {
                    socialId = 0;
                }

            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }
}