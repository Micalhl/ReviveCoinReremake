package me.mical.revivecoinreremake;

import lombok.Getter;
import me.mical.revivecoinreremake.internal.command.ReviveCoinCommand;
import me.mical.revivecoinreremake.internal.config.ConfigManager;
import me.mical.revivecoinreremake.internal.hooks.ReviveCoinExpansion;
import me.mical.revivecoinreremake.internal.listener.LoginListener;
import me.mical.revivecoinreremake.internal.listener.RespawnListener;
import me.mical.revivecoinreremake.internal.listener.ReviveCoinListener;
import me.mical.revivecoinreremake.internal.nms.ActionBarManager;
import me.mical.revivecoinreremake.internal.nms.ActionBarManagerImpl;
import me.mical.revivecoinreremake.util.Dao;
import org.bukkit.Bukkit;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.Arrays;
import java.util.Objects;

public final class ReviveCoinReremake extends PPlugin {

    @Getter
    private static ActionBarManagerImpl actionBarManager;
    @Getter
    private static Dao dao;
    @Getter
    private static ReviveCoinReremake inst;
    @Getter
    private static VaultUtil vaultUtil;

    public ReviveCoinReremake() {
        inst = this;
        dao = new Dao(this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    protected void preload() {
        printLogo();

        lang.log.log("正在加载 ReviveCoin &f&lReremake &7, 版本 &c" + getDescription().getVersion());

        pConfig = new ConfigManager();
    }

    @Override
    protected void load() {
        registerExpansion(new ReviveCoinExpansion());

        vaultUtil = new VaultUtil(this, true);

        registerCommand(new ReviveCoinCommand());

        listen(pluginManager -> {
            pluginManager.registerEvents(new LoginListener(), this);
            pluginManager.registerEvents(new RespawnListener(this), this);
            pluginManager.registerEvents(new ReviveCoinListener(this), this);
        });

        actionBarManager = ActionBarManager.initialize(this);

        if (Objects.isNull(actionBarManager)) {
            lang.log.error(I18n.INIT, "ActionBarManager", "ActionBarManagerImpl 对象为 null");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if (!dao.createDatabases()) {
            lang.log.error(I18n.INIT, "数据库", "初始化失败");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getOnlinePlayers().forEach(player -> dao.preInitializePlayerData(player, player.getUniqueId(), ConfigManager.NEW_PLAYER_COINS));

        lang.log.info("加载插件成功...");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        lang.log.info("卸载插件成功...");
    }

    private void printLogo() {
        lang.log.log(Arrays.asList(
                "",
                "&f__________            .__             _________        .__        ",
                "&f\\______   \\ _______  _|__|__  __ ____ \\_   ___ \\  ____ |__| ____  ",
                "&f |       _// __ \\  \\/ /  \\  \\/ // __ \\/    \\  \\/ /  _ \\|  |/    \\ ",
                "&f |    |   \\  ___/\\   /|  |\\   /\\  ___/\\     \\___(  <_> )  |   |  \\",
                "&f |____|_  /\\___  >\\_/ |__| \\_/  \\___  >\\______  /\\____/|__|___|  /",
                "&f        \\/     \\/                   \\/        \\/               \\/ ",
                ""
        ));
    }

}
