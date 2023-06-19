package de.sturmente.hardcore;

import de.sturmente.hardcore.listener.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hardcore_plugin extends JavaPlugin {

    public static final String PREFIX = "[Hardcore Plugin] ";
    public static final String BAN_REASON = "You died!";

    @Override
    public void onEnable() {
        // Load listeners
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        System.out.println(PREFIX + "Loaded plugin successfully!");
    }
}
