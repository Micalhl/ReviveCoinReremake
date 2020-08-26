package cn.galaxy233.revivecoinreremake.command.subcommands;

import cn.galaxy233.revivecoinreremake.ReviveCoinReremake;
import cn.galaxy233.revivecoinreremake.utils.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.I18n;

public class BuyCmd extends BaseCommand {

    public BuyCmd(PPlugin plugin) {
        super(plugin, "buy", 2);
        describe("为指定玩家购买指定数量的复活币");
        perm("ReviveCoin.buy");
        mustPlayer(true);

    }

    @Override
    protected void call(String[] args) {
        try {
            Player user0 = Bukkit.getPlayer(args[0]);
            double money = ReviveCoinReremake.getVaultUtil().getBalances(user);
            int coins = Integer.parseInt(args[1]);
            double cost = Configuration.BUY_COINS_TAKE_MONEY * coins;
            if (money >= cost) {
                ReviveCoinReremake.getVaultUtil().take(user, money);
                money = ReviveCoinReremake.getVaultUtil().getBalances(user);
                String msg = plugin.lang.getRawWithFormat(plugin.localeKey, "Lang", "successful-buy-coin", user0, coins, money);
                I18n.send(user, info(msg));
                String msg2 = plugin.lang.getRawWithFormat(plugin.localeKey, "Lang", "receive-coin", user, coins);
                I18n.send(user0, info(msg2));
            } else {
                String msg = plugin.lang.getRaw(plugin.localeKey, "Lang", "have-no-enough-money");
                I18n.send(user, warn(msg));
            }
        } catch (NumberFormatException exception) {
            String msg = plugin.lang.getRaw(plugin.localeKey, "Lang", "illgeal-argument");
            I18n.send(user, error(msg));
        }
    }
}


