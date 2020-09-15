package me.mical.revivecoinreremake.listener;

import me.mical.revivecoinreremake.config.ConfigManager;
import me.mical.revivecoinreremake.config.DataManager;
import me.mical.revivecoinreremake.data.PlayerDataManager;
import me.mical.revivecoinreremake.utils.DatabaseUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (ConfigManager.ENABLE_MYSQL) {
            DatabaseUtils.preInitializePlayerData(event.getPlayer(), event.getPlayer().getUniqueId(), ConfigManager.NEW_PLAYER_COINS);
        } else {
            DataManager.getInst().put(new PlayerDataManager(event.getPlayer().getUniqueId().toString(), ConfigManager.NEW_PLAYER_COINS));
        }
    }
}
