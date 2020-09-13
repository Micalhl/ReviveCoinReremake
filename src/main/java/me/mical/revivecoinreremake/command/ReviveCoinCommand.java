package me.mical.revivecoinreremake.command;

import me.mical.revivecoinreremake.command.subcommands.BuyCmd;
import me.mical.revivecoinreremake.command.subcommands.GetCmd;
import me.mical.revivecoinreremake.command.subcommands.GiveCmd;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.CommandHandler;
import org.serverct.parrot.parrotx.command.subcommands.HelpCommand;
import org.serverct.parrot.parrotx.command.subcommands.ReloadCommand;

public class ReviveCoinCommand extends CommandHandler {

    public ReviveCoinCommand(PPlugin plugin, String mainCmd) {
        super(plugin, mainCmd);
        //registerSubCommand("give", new GiveCmd());
        register(new HelpCommand(plugin));
        register(new ReloadCommand(plugin, "ReviveCoin.admin"));
        register(new BuyCmd(plugin));
        register(new GetCmd(plugin));
        register(new GiveCmd(plugin));
    }
}
