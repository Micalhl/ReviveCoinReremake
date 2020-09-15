package me.mical.revivecoinreremake.command.subcommands;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.utils.CoinUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class GiveCmd extends BaseCommand {

    public GiveCmd() {
        super(ReviveCoinReremake.getInst(), "give", 2);
        describe("赠与他人指定数量的复活币");
        perm("ReviveCoin.give");
        mustPlayer(false);
    }

    @Override
    protected void call(String[] args) {
        try {
            Player user0 = Bukkit.getPlayer(args[0]);
            int coins = Integer.parseInt(args[1]);
            if (sender instanceof Player) {
                CoinUtils.give(user, user0, coins);
                int current = CoinUtils.get(user);
                String msg = plugin.getLang().data.get(plugin.localeKey, "Lang", "successful-give-player-coin", user0, coins, current);
                I18n.send(user, info(msg));
                String msg2 = plugin.getLang().data.get(plugin.localeKey, "Lang", "receive-coin", user, coins);
                I18n.send(user0, info(msg2));
            } else {
                CoinUtils.add(user0, coins);
                String msg = plugin.getLang().data.get(plugin.localeKey, "Lang", "receive-coin", "Console", coins);
                I18n.send(user0, msg);
            }
        } catch (NumberFormatException exception) {
            String msg = plugin.getLang().data.get(plugin.localeKey, "Lang", "illgeal-argument");
            I18n.send(user, error(msg));
        }
    }
}
