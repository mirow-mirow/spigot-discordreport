package mrw.discordreport.discordreport;

import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import mrw.discordreport.discordreport.commands.ReportCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordReport extends JavaPlugin {

    private static DiscordReport instance;
    private static TemmieWebhook temmieWebhook;
    public static final String PREFIX = "§8| DiscordReport > §7";

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        login();
        saveConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void login() {
        try {
            temmieWebhook = new TemmieWebhook(getConfig().getString("Discordwebhooklink"));
        } catch (Exception ex) {
            ReportCommand.isEnabled = false;

        }

    }

    public static DiscordReport getInstance() {
        return instance;
    }

    public static TemmieWebhook getTemmieWebhook() {
        return temmieWebhook;
    }
}
