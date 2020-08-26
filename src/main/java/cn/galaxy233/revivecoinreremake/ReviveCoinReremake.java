package cn.galaxy233.revivecoinreremake;

import cn.galaxy233.revivecoinreremake.command.ReviveCoinCommand;
import cn.galaxy233.revivecoinreremake.hooks.Placeholders;
import cn.galaxy233.revivecoinreremake.listener.LoginListener;
import cn.galaxy233.revivecoinreremake.listener.RespawnListener;
import cn.galaxy233.revivecoinreremake.listener.ReviveCoinListener;
import cn.galaxy233.revivecoinreremake.utils.Configuration;
import cn.galaxy233.revivecoinreremake.utils.DatabaseUtils;
import cn.galaxy233.revivecoinreremake.utils.Storage;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;
import org.serverct.parrot.parrotx.utils.I18n;

public final class ReviveCoinReremake extends PPlugin {

    @Getter
    private static VaultUtil vaultUtil;

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
    }

    @Override
    protected void preload() {
        this.pConfig = new Configuration(this);
    }

    @Override
    protected void load() {
        getLogger().info("正在加载ReviveCoinReremake, 版本" + getDescription().getVersion());

        Placeholders.hook();
        vaultUtil = new VaultUtil(this, true);
        registerCommand(new ReviveCoinCommand(this, "revivecoin"));
        listen(pluginManager -> {
            pluginManager.registerEvents(new LoginListener(), this);
            pluginManager.registerEvents(new RespawnListener(), this);
            pluginManager.registerEvents(new ReviveCoinListener(), this);
        });

        Storage.get(this).init();

        if (!DatabaseUtils.createDatabases()) {
            lang.logError(I18n.INIT, "数据库", "初始化失败");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            DatabaseUtils.preInitializePlayerData(player, player.getUniqueId(), Configuration.NEW_PLAYER_COINS);
        }

        getLogger().info("插件已加载");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Placeholders.unhook();
        getLogger().info("插件已卸载");
    }
}
