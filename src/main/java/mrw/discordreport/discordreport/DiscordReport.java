package mrw.discordreport.discordreport;

import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordReport extends JavaPlugin {

    private static DiscordReport instance;

    public static final String PREFIX = "§8| DiscordReport > §7";

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DiscordReport getInstance() {
        return instance;
    }
}
