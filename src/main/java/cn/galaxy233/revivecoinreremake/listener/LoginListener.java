package cn.galaxy233.revivecoinreremake.listener;

import cn.galaxy233.revivecoinreremake.utils.Configuration;
import cn.galaxy233.revivecoinreremake.utils.DatabaseUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        DatabaseUtils.preInitializePlayerData(event.getPlayer(), event.getPlayer().getUniqueId(), Configuration.NEW_PLAYER_COINS);

    }
}
