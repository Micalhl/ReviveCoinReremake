package me.mical.revivecoinreremake.listener;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.config.ConfigManager;
import me.mical.revivecoinreremake.utils.CoinUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.Objects;

public class RespawnListener implements Listener {

    private final PPlugin plugin;

    public RespawnListener(PPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player user = event.getPlayer();
                int time = ConfigManager.RESPAWN_COUNT_TIME;
                user.setGameMode(GameMode.valueOf(ConfigManager.BEFORE_RESPAWN_GAME_MODE));
                if (CoinUtils.has(user, 1)) {
                    CoinUtils.take(user, 1);
                    time--;
                    if (Objects.equals(time, 0)) {
                        user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey , I18n.Type.INFO, "Lang", "successful-respawn"));
                        cancel();
                    }
                } else {
                    if (ReviveCoinReremake.getVaultUtil().getBalances(user) >= ConfigManager.NO_COINS_TAKE_MONEY) {
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.WARN, "Lang", "have-no-enough-coins").replace("{money}", String.valueOf(ConfigManager.NO_COINS_TAKE_MONEY)));
                        time--;
                        if (Objects.equals(time, 0)) {
                            user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                            I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-respawn"));
                            cancel();
                        }
                    } else {
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.WARN, "Lang", "prepare-warp-because-no-coins-or-money"));
                        user.teleport(ConfigManager.WARP);
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-teleport"));
                        user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                        cancel();
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
