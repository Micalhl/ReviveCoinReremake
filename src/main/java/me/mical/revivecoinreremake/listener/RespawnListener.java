package me.mical.revivecoinreremake.listener;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.utils.WarpUtils;
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

public class RespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        PPlugin plugin = ReviveCoinReremake.getInst();
        Player user = event.getPlayer();
        int current_coins = CoinUtils.get(user);
        user.setGameMode(GameMode.valueOf(ConfigManager.BEFORE_RESPAWN_GAME_MODE));
        if (current_coins == 0) {
            if (ReviveCoinReremake.getVaultUtil().getBalances(user) >= ConfigManager.NO_COINS_TAKE_MONEY) {
                I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.WARN, "Lang", "have-no-enough-coins").replace("{money}", String.valueOf(ConfigManager.NO_COINS_TAKE_MONEY)));
                new BukkitRunnable() {
                    int time = ConfigManager.RESPAWN_COUNT_TIME;
                    @Override
                    public void run() {
                            ReviveCoinReremake.getVaultUtil().take(user, ConfigManager.NO_COINS_TAKE_MONEY);
                            I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "prepare-for-respawn").replace("{time}", String.valueOf(time)));
                            time--;
                            if (time == 0) {
                                user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                                I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-respawn"));
                                cancel();
                            }
                    }
                }.runTaskTimer(plugin, 0L, 20L);
            } else {
                I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.WARN, "Lang", "prepare-warp-because-no-coins-or-money"));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        WarpUtils.teleportWarp(user, ConfigManager.WARP);
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-teleport"));
                        user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                        cancel();
                    }
                }.runTaskTimer(plugin, 0L, 20L);
            }
        } else {
            CoinUtils.take(user, 1);
            new BukkitRunnable() {
                int time = ConfigManager.RESPAWN_COUNT_TIME;
                @Override
                public void run() {
                    time--;
                    if (time == 0) {
                        user.setGameMode(GameMode.valueOf(ConfigManager.AFTER_RESPAWN_GAME_MODE));
                        I18n.send(user, plugin.getLang().data.get(plugin.localeKey , I18n.Type.INFO, "Lang", "successful-respawn"));
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }
}
