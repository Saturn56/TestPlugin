package de.saturnmc.saturn.minigameserver.mointainipaio.Joke;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import de.saturnmc.saturn.minigameserver.mointainipaio.Main;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class wikinger implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (sender instanceof  Player player) {
            CraftPlayer craftPlayer = (CraftPlayer) Main.getInstance().getPofString(args[0]);
            //NMS representation of the MC server
            MinecraftServer server = craftPlayer.getHandle().getServer();
            //NMS representation of the MC world
            ServerLevel level = craftPlayer.getHandle().getLevel();

            //Create a new NPC named Billy Bob, with a new GameProfile to uniquely identify them
            GameProfile profile = new GameProfile(UUID.fromString("341716307fa048379d075333682b3983"), "Steve");
            ServerPlayer npc = new ServerPlayer(server, level, new GameProfile(UUID.randomUUID(), "Billy Bob"), null);
            //Set their position. They will be here when we call the packets below to spawn them
            npc.setPos(craftPlayer.getLocation().getBlockX(), craftPlayer.getLocation().getBlockY(), craftPlayer.getLocation().getBlockZ());

            //set the npc skin
            /* Retrieving skins:
             *  - First thing to understand is that skins have to be "signed" by Mojang. Each skin has two parts: texture and signature
             *  - You can retrieve this data from:
             *    https://sessionserver.mojang.com/session/minecraft/profile/UUID?unsigned=false
             *  - UUID = the uuid of a player without the dashes
             *  - UUID data can only be retrieved every 60 seconds (if same uuid)
             */

            String signature = "qlgRc5t4s5wocUsZJ7et395tJkdvEMgF3ySEvbGpu82cQK1qUPKX3LrPzVk0yfT9pKskSLFwBrmyEa+ItMCnPEbfVukz+6/+HNQCkbrCUZ1uxKaHUxIjMWBR+Q6J3RNx2hTuu/XfhANs4tSlSYNjF9w7CpD2nWvU/A+ML+o4vAVjw2+tmy+0FzhSCJXsi6KcWU+hSoW2bPYKPqyl96gQJHS7KZh8m9Zfr/5m6PsMrx9cBDyHl8jo5NAUw8df6KsPXDMoUupFg76cnvu97ULnxUAAvyZuF02NSU0tVO9z0/9E/CE4R6rMpaEzBV+QtSC9eN9Pgxc3M+zcca5mf2p5bW/qHJRCHmTi62+Y7aP9A+NkH9ZI5NaakS38lwY7/cQ2abjeo/y24dasq4EZRV79NDbGD/4zENTNKm5WNsQDhGYoIDBc6R1ER7fdiW4ZgDzysD3HqtP83h3Uh8j3J7bA2FkUbFbljsyxOay6TbnTIXlSLy2W4CXpx8GXKI8fg7ixubG+xieSGT55SA2fNUOo2cL+tNJvHQvAvvOwCYjV4pKkAopC9Iq5FRuhz/MrA2kUClW734exKRnoIGtJ3LmHKzzBaAxv9t4VhV3LAwnvDEfb/FFYcOR0x9/F7xFeqQny0SBnODbJWKGWwBkbToYzqyA6Um1tlxSRRMoRrngdnxY=";
            String value = "ewogICJ0aW1lc3RhbXAiIDogMTY2MjU3MzYyNDYyNiwKICAicHJvZmlsZUlkIiA6ICIzNDE3MTYzMDdmYTA0ODM3OWQwNzUzMzM2ODJiMzk4MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCZXJnYmF1ZXJfMjIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODhmZDEzYTAyMzg3MWQ1MGU3MWM4NWYyZTgxNmE5ZDQ4NTI5N2ZlY2VjYzBmYWM1ZmM0ZTcyM2IzODFhY2Y5ZiIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=";
            //npc.getGameProfile().getProperties().put("textures", new Property("textures", texture, signature));

            //Send the packets to artificially spawn this entity, only the client we are sending the packet to will know of it's existence
            ServerGamePacketListenerImpl ps = craftPlayer.getHandle().connection;
            //Player Info Packet
            //Sent by the server to update the user list (<tab> in the client).
            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
            //Spawn Player packet
            ps.send(new ClientboundAddPlayerPacket(npc));

            //Give the player items and armor
            ItemStack item = new ItemStack(Material.DIAMOND_AXE);
            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(Pair.of(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(item)))));
            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(Pair.of(EquipmentSlot.OFFHAND, CraftItemStack.asNMSCopy(item)))));

            //add it to the list of NPCs so we can access it in our movement listener


        }
        return false;
    }
}
