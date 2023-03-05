package de.saturnmc.saturn.minigameserver.mointainipaio;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.saturnmc.saturn.minigameserver.mointainipaio.Command.AntiDrop.AntiDropWithCMD;
import de.saturnmc.saturn.minigameserver.mointainipaio.Groups.GroupCommand;
import de.saturnmc.saturn.minigameserver.mointainipaio.Joke.wikinger;
import de.saturnmc.saturn.minigameserver.mointainipaio.Listener.ChatSystem;
import de.saturnmc.saturn.minigameserver.mointainipaio.Listener.ItemListener;
import de.saturnmc.saturn.minigameserver.mointainipaio.Listener.JoinListener;
import de.saturnmc.saturn.minigameserver.mointainipaio.NPCs.PlayerJoin;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin implements PluginMessageListener {

    private static Main instance;

    private List<ServerPlayer> npcs = new ArrayList<>();

    FileConfiguration config = this.getConfig();

    public String[] PStats;

    public void onLoad() {
        instance = this;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "hallo");
        Commands();
        Listener();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        saveConfig();
        Logger.getLogger("Version 1.1");

        //LobbyNPCs.onServerLoad();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public void Commands(){
        this.getCommand("group").setExecutor(new GroupCommand());
        this.getCommand("joke").setExecutor(new wikinger());
        this.getCommand("AntiDrop").setExecutor(new AntiDropWithCMD());
    }


    public void Listener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new ChatSystem(), this);
        //pluginManager.registerEvents(new NaviClickListener(), this);
        //pluginManager.registerEvents(new InNavClick(), this);
        pluginManager.registerEvents(new ItemListener(), this);
        pluginManager.registerEvents(new PlayerJoin(), this);
    }



    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Mountain-Bot" + ChatColor.DARK_GRAY + "]";
    }


    /*@Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }*/

    public void sendToServer(Player player, String server){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        // If you don't care about the player
        // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        // Else, specify them

        player.sendPluginMessage((Plugin) this,"BungeeCord", out.toByteArray());
    }

    public ArrayList<String> getPermission(Player player) {
        ArrayList<String> permissions = new ArrayList<>();
        if(getConfig().contains(player.getUniqueId().toString() + ".permissions")){
            permissions = (ArrayList<String>) getConfig().getStringList(player.getUniqueId().toString() + ".permissions");
        }
        return permissions;
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        Bukkit.broadcastMessage(ChatColor.BLUE + in.readUTF());
        String[] servers = in.readUTF().split(", ");
    }

    public void setGroup(String player, String group, Player sender){
        if (config.contains("group." + player)){
            config.set("group." + player, group);
        } else {
            config.addDefault("group." + player, group);}
        List<String> list = config.getStringList("group.list");
        if (!list.contains(group)){
            list.add(group);

            config.set("group_list", list);
        }
        config.options().copyDefaults(true);
        saveConfig();
    }
    public String getGroup(String player){
        String group = (String) config.get("group." + player);
        return group;
    }

    public Player getPofString(String playerName){
        Player player = getServer().getPlayer(playerName);
        return player;
    }

    public List<Integer> antiDropCMD;


    public List<String> getGroupList(){
        List<String> list = config.getStringList("group_list");
        return list;
    }


    public void SaveConfig(){
        saveConfig();
    }

    public List<ServerPlayer> getNpcs() {
        return npcs;
    }





}