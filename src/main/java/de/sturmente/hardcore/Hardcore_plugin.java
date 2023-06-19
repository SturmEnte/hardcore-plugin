package de.sturmente.hardcore;

import de.sturmente.hardcore.listener.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hardcore_plugin extends JavaPlugin {

    public static final String BAN_REASON = "You died!";

    @Override
    public void onEnable() {
        System.out.println("Hello World!");

        // Load listeners
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
