package me.mical.revivecoinreremake.utils;

import me.mical.revivecoinreremake.event.ReviveCoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoinUtils{

    public static void add(Player user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.ADD, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            DatabaseUtils.setCoins(event.getUser().getUniqueId(), get(event.getUser()) + event.getCoins());
        }
    }

    public static void give(Player user, Player target, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.GIVE, user, target, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            DatabaseUtils.setCoins(event.getUser().getUniqueId(), event.getTarget().getUniqueId(), event.getCoins());
        }
    }

    public static void take(Player user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.REDUCE, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            DatabaseUtils.setCoins(event.getUser().getUniqueId(), get(event.getUser()) - event.getCoins());
        }
    }

    public static boolean has(Player user, int coins) {
        return coins <= get(user);
    }

    public static int get(Player user) {
        return DatabaseUtils.getCoins(user.getUniqueId());
    }
}
