package me.mical.revivecoinreremake.internal.listener;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.internal.config.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ReviveCoinReremake.getDao().preInitializePlayerData(event.getPlayer(), event.getPlayer().getUniqueId(), ConfigManager.NEW_PLAYER_COINS);
    }
}
