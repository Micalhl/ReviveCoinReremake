package cn.galaxy233.revivecoinreremake.listener;

import cn.galaxy233.revivecoinreremake.ReviveCoinReremake;
import cn.galaxy233.revivecoinreremake.event.ReviveCoinReduceEvent;
import cn.galaxy233.revivecoinreremake.features.WarpControl;
import cn.galaxy233.revivecoinreremake.utils.Configuration;
import cn.galaxy233.revivecoinreremake.features.ReviveCoin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.I18n;

public class RespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        PPlugin plugin = ReviveCoinReremake.getInstance();

        Player user = event.getPlayer();
        int current_coins = ReviveCoin.getReviveCoin().get(user);
        user.setGameMode(GameMode.valueOf(Configuration.BEFORE_RESPAWN_GAME_MODE));

        if (current_coins == 0) {

            if (ReviveCoinReremake.getVaultUtil().getBalances(user) >= Configuration.NO_COINS_TAKE_MONEY) {

                I18n.send(user, plugin.lang.get(plugin.localeKey, I18n.Type.WARN, "Lang", "have-no-enough-coins").replace("{money}", String.valueOf(Configuration.NO_COINS_TAKE_MONEY)));

                new BukkitRunnable() {

                    int time = Configuration.RESPAWN_COUNT_TIME;

                    @Override
                    public void run() {

                            ReviveCoinReremake.getVaultUtil().take(user, Configuration.NO_COINS_TAKE_MONEY);
                            I18n.send(user, plugin.lang.get(plugin.localeKey, I18n.Type.INFO, "Lang", "prepare-for-respawn").replace("{time}", String.valueOf(time)));
                            time--;

                            if (time == 0) {

                                user.setGameMode(GameMode.valueOf(Configuration.AFTER_RESPAWN_GAME_MODE));
                                I18n.send(user, plugin.lang.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-respawn"));
                                cancel();

                            }

                    }

                }.runTaskTimer(plugin, 0L, 20L);


            } else {

                I18n.send(user, plugin.lang.get(plugin.localeKey, I18n.Type.WARN, "Lang", "prepare-warp-because-no-coins-or-money"));

                new BukkitRunnable() {

                    @Override
                    public void run() {


                            WarpControl.teleportWarp(user, Configuration.WARP);

                        I18n.send(user, plugin.lang.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-teleport"));

                        user.setGameMode(GameMode.valueOf(Configuration.AFTER_RESPAWN_GAME_MODE));

                        cancel();

                    }
                }.runTaskTimer(plugin, 0L, 20L);

            }


        } else {

            ReviveCoin.getReviveCoin().take(user, 1);
            ReviveCoinReduceEvent reviveCoinReduceEvent = new ReviveCoinReduceEvent(event.getPlayer());
            Bukkit.getPluginManager().callEvent(reviveCoinReduceEvent);

            new BukkitRunnable() {

                int time = Configuration.RESPAWN_COUNT_TIME;

                @Override
                public void run() {

                    time--;

                    if (time == 0) {



                            user.setGameMode(GameMode.valueOf(Configuration.AFTER_RESPAWN_GAME_MODE));



                        I18n.send(user, plugin.lang.get(plugin.localeKey , I18n.Type.INFO, "Lang", "successful-respawn"));
                        cancel();

                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);

        }
    }
}
