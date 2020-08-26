package cn.galaxy233.revivecoinreremake.features;

import cn.galaxy233.revivecoinreremake.api.CoinAPI;
import cn.galaxy233.revivecoinreremake.event.ReviveCoinAddEvent;
import cn.galaxy233.revivecoinreremake.event.ReviveCoinGiveOtherEvent;
import cn.galaxy233.revivecoinreremake.event.ReviveCoinReduceEvent;
import cn.galaxy233.revivecoinreremake.utils.DatabaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReviveCoin implements CoinAPI {

    private static ReviveCoin instance;

    public static ReviveCoin getReviveCoin() {
        if (instance == null)
            instance = new ReviveCoin();
        return instance;
    }

    @Override
    public void add(Player user, int coins) {
        DatabaseUtils.setCoins(user.getUniqueId(), get(user) + coins);
        ReviveCoinAddEvent event = new ReviveCoinAddEvent(user, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void give(Player user1, Player user2, int coins) {
        DatabaseUtils.setCoins(user1.getUniqueId(), get(user1) - coins);
        DatabaseUtils.setCoins(user2.getUniqueId(), get(user2) + coins);
        ReviveCoinGiveOtherEvent event = new ReviveCoinGiveOtherEvent(user1, user2, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void take(Player user, int coins) {
        DatabaseUtils.setCoins(user.getUniqueId(), get(user) - coins);
        ReviveCoinReduceEvent event = new ReviveCoinReduceEvent(user, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public boolean has(Player user, int coins) {
        return coins <= get(user);
    }

    @Override
    public int get(Player user) {
        return DatabaseUtils.getCoins(user.getUniqueId());
    }
}
