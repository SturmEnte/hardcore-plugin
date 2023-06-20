package de.sturmente.hardcore.listener;

import de.sturmente.hardcore.Hardcore_plugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getEntity().getPlayer();

        // Set unban time to 24 hours after the death
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        Date date = calendar.getTime();

        Location location = player.getLocation();
        Block block = location.getBlock();
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();
        ItemStack[] contents = player.getInventory().getContents();
        if (contents.length > 27) {
            ItemStack[] newContents = Arrays.copyOfRange(contents, 0, 27);
            chest.getInventory().setContents(newContents);
        } else {
            chest.getInventory().setContents(contents);
        }
        BlockData data = Bukkit.createBlockData(Material.CHEST);
        ((Directional) data).setFacing(BlockFace.NORTH);
        block.setBlockData(data);

        Location location2 = location.clone().add(1, 0, 0);
        Block block2 = location2.getBlock();
        block2.setType(Material.CHEST);
        Chest chest2 = (Chest) block2.getState();
        if (contents.length > 27) {
            ItemStack[] newContents = Arrays.copyOfRange(contents, 27, contents.length);
            chest2.getInventory().setContents(newContents);
        } else {
            chest2.getInventory().setContents(new ItemStack[0]);
        }
        BlockData data2 = Bukkit.createBlockData(Material.CHEST);
        ((Directional) data2).setFacing(BlockFace.NORTH);
        block2.setBlockData(data2);

        player.getInventory().clear();

        // Ban player
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), Hardcore_plugin.BAN_REASON, date, "Hardcore Plugin");

        // If a player gets banned, they don't get kicked automatically, so they have to be kicked after they got banned
        player.kickPlayer(Hardcore_plugin.BAN_REASON);
        
        playerDeathEvent.setDeathMessage(Hardcore_plugin.PREFIX + player.getName() + " died and got banned for the next 24 hours!");
    }

}
