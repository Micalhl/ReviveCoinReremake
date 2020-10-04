package me.mical.revivecoinreremake.api;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.util.Coin;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

@SuppressWarnings("unused")
public class CoinAPI {

    private final PPlugin plugin;
    private final JavaPlugin hookPlugin;

    public CoinAPI(JavaPlugin hookPlugin) {
        this.plugin = ReviveCoinReremake.getInst();
        this.hookPlugin = hookPlugin;
        plugin.getLang().log.info("已连接 &c" + hookPlugin.getDescription().getName());
    }

    /**
     * 为指定玩家账户内添加指定数量复活币
     * @param user 指定玩家
     * @param coins 复活币数量
     */
    public void add(OfflinePlayer user, int coins) {
        Coin.add(user, coins);
        String action = I18n.color("&7为玩家 &c" + user.getName() + " &7添加 &c" + coins + " &7枚复活币.");
        plugin.getLang().log.warn(hookPlugin.getDescription().getName() + " &c尝试 &7" + action);
    }

    /**
     * 从指定玩家的账户内赠与另一为玩家复活币
     * @param user 指定玩家
     * @param target 赠送对象
     * @param coins 复活币数量
     */
    public void give(OfflinePlayer user, OfflinePlayer target, int coins) {
        Coin.give(user, target, coins);
        String action = I18n.color("&7让玩家 &c" + user.getName() + "为 &c" + target.getName() + " &7添加 &c" + coins + " &7枚复活币.");
        plugin.getLang().log.warn(hookPlugin.getDescription().getName() + " &c尝试 &7" + action);
    }

    /**
     * 为指定玩家账户内扣除指定数量复活币
     * @param user 指定玩家
     * @param coins 复活币数量
     */
    public void take(OfflinePlayer user, int coins) {
        Coin.take(user, coins);
        String action = I18n.color("&7为玩家 &c" + user.getName() + " &7扣除 &c" + coins + " &7枚复活币.");
        plugin.getLang().log.warn(hookPlugin.getDescription().getName() + " &c尝试 &7" + action);
    }

    /**
     * 检索指定玩家账户内有没有指定数量的复活币
     * @param user 指定玩家
     * @param coins 复活币数量
     * @return true 为有 / false 为无
     */
    public boolean has(OfflinePlayer user, int coins) {
        String action = I18n.color("&7检索玩家 &c" + user.getName() + " &7账户内有没有 &c" + coins + " &7枚复活币.");
        plugin.getLang().log.warn(hookPlugin.getDescription().getName() + " &c尝试 &7" + action);
        return Coin.has(user, coins);
    }

    /**
     * 检索指定玩家账户内复活币数量
     * @param user 指定玩家
     * @return 复活币数量
     */
    public int get(OfflinePlayer user) {
        String action = I18n.color("&7检索玩家 &c" + user.getName() + " &7账户内复活币数量.");
        plugin.getLang().log.warn(hookPlugin.getDescription().getName() + " &c尝试 &7" + action);
        return Coin.get(user);
    }
}
