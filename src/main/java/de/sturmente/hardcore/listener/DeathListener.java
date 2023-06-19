package de.sturmente.hardcore.listener;

import de.sturmente.hardcore.Hardcore_plugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Calendar;
import java.util.Date;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getEntity().getPlayer();

        // Set unban time to 24 hours after the death
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        Date date = calendar.getTime();

        // Ban player
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), Hardcore_plugin.BAN_REASON, date, "Hardcore Plugin");

        // If a player gets banned, they don't get kicked automatically, so they have to be kicked after they got banned
        player.kickPlayer(Hardcore_plugin.BAN_REASON);
    }

}
