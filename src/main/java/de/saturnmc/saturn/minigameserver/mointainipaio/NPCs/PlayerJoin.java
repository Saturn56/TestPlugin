package de.saturnmc.saturn.minigameserver.mointainipaio.NPCs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import javax.swing.text.Position;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        MainLobbyNPC1(e.getPlayer());
        MainLobbyNPC2(e.getPlayer());
        MainLobbyNPC3(e.getPlayer());
        p.sendMessage("halloadfsgdhfj");
    }

    private static String[] getSkin(Player player, String name) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid
                    + "?unsigned=false");
            InputStreamReader reader1 = new InputStreamReader(url2.openStream());
            JsonObject property = new JsonParser().parse(reader1).getAsJsonObject().get("properties")
                    .getAsJsonArray().get(0).getAsJsonObject();
            String texture = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();
            return new String[] {texture, signature};


        }catch (Exception e){
            player.sendMessage("error");
            return new String[] {"hallo", "Hallo"};
        }
    }


    private static void MainLobbyNPC1(Player player){
        CraftPlayer craftPlayer = (CraftPlayer) player;
        MinecraftServer server = craftPlayer.getHandle().getServer();
        ServerLevel level = craftPlayer.getHandle().getLevel();

        ServerPlayer npc = new ServerPlayer(server, level, new GameProfile(UUID.randomUUID(), "Kit Skywars"), null);

        npc.setPos(6.5, -50, 0.5);

        Main.getInstance().getNpcs().add(npc);



        //npc.getGameProfile().getProperties().put("textures", new Property("textures", texture, signature));

        ServerGamePacketListenerImpl ps = craftPlayer.getHandle().connection;
        ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        ps.send(new ClientboundAddPlayerPacket(npc));
    }

    private static void MainLobbyNPC2(Player player){

        CraftPlayer craftPlayer = (CraftPlayer) player;
        MinecraftServer server = craftPlayer.getHandle().getServer();
        ServerLevel level = craftPlayer.getHandle().getLevel();

        GameProfile profile = new GameProfile(UUID.randomUUID(), "Kit Skywars");
        ServerPlayer npc = new ServerPlayer(server, level, profile, null);

        npc.setPos(0.5, -50, -5.5);


        Main.getInstance().getNpcs().add(npc);

        ServerGamePacketListenerImpl ps = craftPlayer.getHandle().connection;
        ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        ps.send(new ClientboundAddPlayerPacket(npc));
    }

    private static void MainLobbyNPC3(Player player){
        CraftPlayer craftPlayer = (CraftPlayer) player;
        MinecraftServer server = craftPlayer.getHandle().getServer();
        ServerLevel level = craftPlayer.getHandle().getLevel();

        GameProfile profile = new GameProfile(UUID.randomUUID(), "Kit Skywars");
        ServerPlayer npc = new ServerPlayer(server, level, profile, null);

        npc.setPos(-5.5, -50, 0.5);


        Main.getInstance().getNpcs().add(npc);

        ServerGamePacketListenerImpl ps = craftPlayer.getHandle().connection;
        ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        ps.send(new ClientboundAddPlayerPacket(npc));
    }

    private static void MainLobbyNPC4(Player player){

    }
}
