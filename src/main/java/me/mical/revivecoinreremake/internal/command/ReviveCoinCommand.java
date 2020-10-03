package me.mical.revivecoinreremake.internal.command;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.internal.command.subcommands.BuyCmd;
import me.mical.revivecoinreremake.internal.command.subcommands.GetCmd;
import me.mical.revivecoinreremake.internal.command.subcommands.GiveCmd;
import me.mical.revivecoinreremake.internal.command.subcommands.SetWarpCmd;
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
