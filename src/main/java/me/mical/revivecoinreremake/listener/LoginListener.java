package me.mical.revivecoinreremake.listener;

import me.mical.revivecoinreremake.config.ConfigManager;
import me.mical.revivecoinreremake.utils.DatabaseUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        DatabaseUtils.preInitializePlayerData(event.getPlayer(), event.getPlayer().getUniqueId(), ConfigManager.NEW_PLAYER_COINS);

    }
}
