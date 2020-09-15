package me.mical.revivecoinreremake.utils;

import me.mical.revivecoinreremake.config.ConfigManager;
import me.mical.revivecoinreremake.config.DataManager;
import me.mical.revivecoinreremake.data.PlayerDataManager;
import me.mical.revivecoinreremake.event.ReviveCoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Objects;

public class CoinUtils {

    public static void add(OfflinePlayer user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.ADD, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (ConfigManager.ENABLE_MYSQL) {
                DatabaseUtils.setCoins(event.getUser().getUniqueId(), get(event.getUser()) + event.getCoins());
            } else {
                DataManager.getInst().get(Objects.requireNonNull(event.getUser().getPlayer()).getUniqueId().toString()).setCoins(get(event.getUser()) + event.getCoins());
            }
        }
    }

    public static void give(OfflinePlayer user, OfflinePlayer target, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.GIVE, user, target, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (ConfigManager.ENABLE_MYSQL) {
                DatabaseUtils.setCoins(event.getUser().getUniqueId(), event.getTarget().getUniqueId(), event.getCoins());
            } else {
                PlayerDataManager userData = DataManager.getInst().get(Objects.requireNonNull(event.getUser().getPlayer()).getUniqueId().toString());
                PlayerDataManager targetData = DataManager.getInst().get(Objects.requireNonNull(event.getTarget().getPlayer()).getUniqueId().toString());
                userData.setCoins(get(event.getUser()) - event.getCoins());
                targetData.setCoins(get(event.getTarget()) + event.getCoins());
            }
        }
    }

    public static void take(OfflinePlayer user, int coins) {
        ReviveCoinEvent event = new ReviveCoinEvent(ReviveCoinEvent.Type.REDUCE, user, coins);
        Bukkit.getPluginManager().callEvent(event);
        if (ConfigManager.ENABLE_MYSQL) {
            DatabaseUtils.setCoins(event.getUser().getUniqueId(), get(event.getUser()) - event.getCoins());
        } else {
            DataManager.getInst().get(Objects.requireNonNull(event.getUser().getPlayer()).getUniqueId().toString()).setCoins(get(event.getUser()) - event.getCoins());
        }
    }

    public static boolean has(OfflinePlayer user, int coins) {
        return coins <= get(user);
    }

    public static int get(OfflinePlayer user) {
        return DatabaseUtils.getCoins(user.getUniqueId());
    }
}
