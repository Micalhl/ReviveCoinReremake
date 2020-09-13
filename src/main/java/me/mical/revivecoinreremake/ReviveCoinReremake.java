package me.mical.revivecoinreremake;

import me.mical.revivecoinreremake.command.ReviveCoinCommand;
import me.mical.revivecoinreremake.hooks.PlaceholderHook;
import me.mical.revivecoinreremake.listener.LoginListener;
import me.mical.revivecoinreremake.listener.RespawnListener;
import me.mical.revivecoinreremake.listener.ReviveCoinListener;
import me.mical.revivecoinreremake.config.ConfigManager;
import me.mical.revivecoinreremake.utils.DatabaseUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.Arrays;

public class ReviveCoinReremake extends PPlugin {

    @Getter
    private static ReviveCoinReremake inst;
    @Getter
    private static VaultUtil vaultUtil;

    @Override
    public void onEnable() {
        inst = this;
        super.onEnable();
    }

    @Override
    protected void preload() {
        printLogo();

        lang.log.log("正在加载 ReviveCoin &f&lReremake &7, 版本 &c" + getDescription().getVersion());

        pConfig = new ConfigManager(this);
    }

    @Override
    protected void load() {
        new PlaceholderHook(this).hook();

        vaultUtil = new VaultUtil(this, true);
        registerCommand(new ReviveCoinCommand(this, "revivecoin"));
        listen(pluginManager -> {
            pluginManager.registerEvents(new LoginListener(), this);
            pluginManager.registerEvents(new RespawnListener(), this);
            pluginManager.registerEvents(new ReviveCoinListener(), this);
        });

        if (!DatabaseUtils.createDatabases()) {
            lang.log.error(I18n.INIT, "数据库", "初始化失败");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            DatabaseUtils.preInitializePlayerData(player, player.getUniqueId(), ConfigManager.NEW_PLAYER_COINS);
        }

        lang.log.info("加载插件成功...");
    }

    @Override
    public void onDisable() {
        super.onDisable();

        new PlaceholderHook(this).unhook();

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
