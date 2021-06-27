package mrw.discordreport.discordreport.commands;
import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed;
import mrw.discordreport.discordreport.DiscordReport;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 27.06.2021
 *
 * @author notMirow (email.mirowyt@gmail.com)
 */
public class ReportCommand implements CommandExecutor {

    public static boolean isEnabled = true;
    public String middleMessage = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender);
            if (args.length > 1) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                String oName = offlinePlayer.getName();
                for (int i = 0; i < args.length; i++) {
                    middleMessage = middleMessage + " " + args[i];
                }
                middleMessage.replaceAll(oName, " ");
                sendReport(oName, player, middleMessage);
            }
        }
        return false;
    }

    public void sendReport(String reportedPlayer, Player reportSender, String msg) {
        List<FieldEmbed> fieldEmbedList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateNow = LocalDateTime.now();
        String currentDate = dtf.format(dateNow);
        FieldEmbed titelAndDescriptionField = new FieldEmbed("Report", "A player got Reported on the Server.", false);
        FieldEmbed reportSenderField = new FieldEmbed("The following player executed the Report Command", reportSender.getName(), false);
        FieldEmbed reportedPlayerField = new FieldEmbed("The following player got reported", reportedPlayer, false);
        FieldEmbed reportCauseField = new FieldEmbed("The player got reported because of", msg, false);
        FieldEmbed reportDateField = new FieldEmbed("The player got reported at", currentDate, false);
        fieldEmbedList.add(titelAndDescriptionField);
        fieldEmbedList.add(reportSenderField);
        fieldEmbedList.add(reportedPlayerField);
        fieldEmbedList.add(reportCauseField);
        fieldEmbedList.add(reportDateField);
        DiscordEmbed de = DiscordEmbed.builder().fields(fieldEmbedList).build();
        DiscordMessage dm = DiscordMessage.builder()
                .username("Report | Log")
                .content("")
                .avatarUrl("http://img06.deviantart.net/a35d/i/2016/056/c/3/temmie___undertale_by_tartifondue-d9t3h1h.png")
                .embeds(Arrays.asList(de))
                .build();
        DiscordReport.getTemmieWebhook().sendMessage(dm);
    }
}
