package me.mical.revivecoinreremake.util;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.api.event.ReviveCoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Coin {

    public static void add(OfflinePlayer user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.ADD, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            ReviveCoinReremake.getDao().setCoins(event.getUser().getUniqueId(), get(event.getUser()) + event.getCoins());
        }
    }

    public static void give(OfflinePlayer user, OfflinePlayer target, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.GIVE, user, target, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            ReviveCoinReremake.getDao().setCoins(event.getUser().getUniqueId(), event.getTarget().getUniqueId(), event.getCoins());
        }
    }

    public static void take(OfflinePlayer user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.REDUCE, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        ReviveCoinReremake.getDao().setCoins(event.getUser().getUniqueId(), get(event.getUser()) - event.getCoins());
    }

    public static boolean has(OfflinePlayer user, int coins) {
        return coins <= get(user);
    }

    public static int get(OfflinePlayer user) {
        return ReviveCoinReremake.getDao().getCoins(user.getUniqueId());
    }
}
