package me.mical.revivecoinreremake.config;

import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PConfig;

import java.io.File;

public class ConfigManager extends PConfig {
    public static double NO_COINS_TAKE_MONEY;
    public static double BUY_COINS_TAKE_MONEY;
    public static int RESPAWN_COUNT_TIME;
    public static int NEW_PLAYER_COINS;
    public static String BEFORE_RESPAWN_GAME_MODE;
    public static String AFTER_RESPAWN_GAME_MODE;
    public static String WARP;

    public static String HOST;
    public static int PORT;
    public static String DATABASE;
    public static boolean SSL;
    public static String USERNAME;
    public static String PASSWORD;

    public static ItemStack REVIVE_COIN;

    public ConfigManager(PPlugin plugin) {
        super(plugin, "config", "主配置文件");
    }

    @Override
    public void load(File file) {
        NO_COINS_TAKE_MONEY = getConfig().getDouble("Play.No-Coins-Take-Money");
        BUY_COINS_TAKE_MONEY = getConfig().getDouble("Play.Buy-Coins-Take-Money");
        RESPAWN_COUNT_TIME = getConfig().getInt("Play.Respawn-Count-Time");
        NEW_PLAYER_COINS = getConfig().getInt("Play.New-Player-Coins");
        BEFORE_RESPAWN_GAME_MODE = getConfig().getString("Play.Before-Respawn-Game-Mode");
        AFTER_RESPAWN_GAME_MODE = getConfig().getString("Play.After-Respawn-Game-Mode");
        WARP = getConfig().getString("Play.Warp");

        HOST = getConfig().getString("Mysql.Host");
        PORT = getConfig().getInt("Mysql.Port");
        DATABASE = getConfig().getString("Mysql.Database");
        USERNAME = getConfig().getString("Mysql.Username");
        PASSWORD = getConfig().getString("Mysql.Password");
        SSL = getConfig().getBoolean("Mysql.UseSSL");

        REVIVE_COIN = getConfig().getItemStack("ReviveCoin");
    }
}
