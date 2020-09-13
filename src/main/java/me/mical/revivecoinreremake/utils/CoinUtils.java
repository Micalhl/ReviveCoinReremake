package me.mical.revivecoinreremake.utils;

import me.mical.revivecoinreremake.event.ReviveCoinAddEvent;
import me.mical.revivecoinreremake.event.ReviveCoinGiveOtherEvent;
import me.mical.revivecoinreremake.event.ReviveCoinReduceEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoinUtils{

    public static void add(Player user, int coins) {
        DatabaseUtils.setCoins(user.getUniqueId(), get(user) + coins);
        ReviveCoinAddEvent event = new ReviveCoinAddEvent(user, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void give(Player user1, Player user2, int coins) {
        DatabaseUtils.setCoins(user1.getUniqueId(), get(user1) - coins);
        DatabaseUtils.setCoins(user2.getUniqueId(), get(user2) + coins);
        ReviveCoinGiveOtherEvent event = new ReviveCoinGiveOtherEvent(user1, user2, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void take(Player user, int coins) {
        DatabaseUtils.setCoins(user.getUniqueId(), get(user) - coins);
        ReviveCoinReduceEvent event = new ReviveCoinReduceEvent(user, coins);
        Bukkit.getPluginManager().callEvent(event);
    }

    public static boolean has(Player user, int coins) {
        return coins <= get(user);
    }

    public static int get(Player user) {
        return DatabaseUtils.getCoins(user.getUniqueId());
    }
}
