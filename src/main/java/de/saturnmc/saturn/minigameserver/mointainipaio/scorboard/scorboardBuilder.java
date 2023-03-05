package de.saturnmc.saturn.minigameserver.mointainipaio.scorboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class scorboardBuilder {
    private final Scoreboard scoreboard;
    private final Objective objective;

    private final Player player;


    public scorboardBuilder(Player player, String displayName) {
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.scoreboard = player.getScoreboard();

        if (this.scoreboard.getObjective("display") != null){
            this.scoreboard.getObjective("display").unregister();
        }

        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        createScoreboard();
    }



    public void createScoreboard() {

    }

    public void update() {

    }

    public void setDisplayName(String displayName){
        this.objective.setDisplayName(displayName);
    }

    public void setScore(String content, int score){
        Team team = getTeamByScore(score);

        if(team == null) {
            return;
        }

        team.setPrefix(content);
        showScore(score);
    }

    public void removeScore(int score){
        hideScore(score);
    }

    private void hideScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if(name == null) {
            return;
        }

        if(!objective.getScore(name.getEntryName()).isScoreSet()) {
            return;
        }

        scoreboard.resetScores(name.getEntryName());
    }

    private void showScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if(name == null) {
            return;
        }

        if(objective.getScore(name.getEntryName()).isScoreSet()) {
            return;
        }

        objective.getScore(name.getEntryName()).setScore(score);
    }

    private Team getTeamByScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if(name == null) {
            return null;
        }

        Team team = scoreboard.getEntryTeam(name.getEntryName());

        if(team != null) {
            return team;
        }

        team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    private EntryName getEntryNameByScore(int score) {
        for(EntryName name : EntryName.values()) {
            if(score == name.getEntry()) {
                return name;
            }
        }

        return null;
    }
}
