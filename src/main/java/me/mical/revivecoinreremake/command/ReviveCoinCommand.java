package me.mical.revivecoinreremake.command;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.command.subcommands.BuyCmd;
import me.mical.revivecoinreremake.command.subcommands.GetCmd;
import me.mical.revivecoinreremake.command.subcommands.GiveCmd;
import me.mical.revivecoinreremake.command.subcommands.SetWarpCmd;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.CommandHandler;
import org.serverct.parrot.parrotx.command.subcommands.HelpCommand;
import org.serverct.parrot.parrotx.command.subcommands.ReloadCommand;

public class ReviveCoinCommand extends CommandHandler {

    public ReviveCoinCommand() {
        super(ReviveCoinReremake.getInst(), "revivecoin");
        register(new HelpCommand(plugin));
        register(new ReloadCommand(plugin, "ReviveCoin.admin"));
        register(new BuyCmd());
        register(new GetCmd());
        register(new GiveCmd());
        register(new SetWarpCmd());
    }
}
