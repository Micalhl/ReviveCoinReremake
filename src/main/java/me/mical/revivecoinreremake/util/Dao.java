package me.mical.revivecoinreremake.util;

import lombok.Getter;
import me.mical.revivecoinreremake.internal.config.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.i18n.I18n;
import org.serverct.parrot.parrotx.utils.JsonChatUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Dao {

    private final PPlugin plugin;
    @Getter
    private final HikariDataSource dataSource;

    public Dao(PPlugin plugin) {
        this.plugin = plugin;
        this.dataSource = setupDatabase();
    }

    private HikariDataSource setupDatabase() {
        HikariDataSource dataSource = new HikariDataSource();
        String host = ConfigManager.HOST;
        int port = ConfigManager.PORT;
        String database = ConfigManager.DATABASE;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(ConfigManager.USERNAME);
        dataSource.setPassword(ConfigManager.PASSWORD);
        dataSource.addDataSourceProperty("autoReconnect", "true");
        dataSource.addDataSourceProperty("autoReconnectForPools", "true");
        dataSource.addDataSourceProperty("interactiveClient", "true");
        dataSource.addDataSourceProperty("characterEncoding", "UTF-8");
        if (!ConfigManager.SSL) {
            dataSource.addDataSourceProperty("useSSL", "false");
        }
        return dataSource;
    }

    public boolean createDatabases() {
        try (Connection connection = getDataSource().getConnection()) {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS revivecoin(`uuid` VARCHAR(255) UNIQUE NOT NULL,`coins` INTEGER DEFAULT NULL,PRIMARY KEY (`uuid`))").execute();
            return true;
        } catch (SQLException exception) {
            plugin.getLang().log.error(I18n.CREATE, "数据库", exception, null);
            return false;
        }
    }

    public void preInitializePlayerData(Player user, UUID uuid, int defaultCoins) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT `coins` FROM `revivecoin` WHERE `uuid` = ?")) {
                statement.setString(1, uuid.toString());
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    try (PreparedStatement insertS = connection.prepareStatement(
                            "INSERT INTO `revivecoin` (uuid, coins) VALUES (?, ?)"
                    )) {
                        insertS.setString(1, uuid.toString());
                        insertS.setInt(2, defaultCoins);
                        insertS.executeUpdate();
                        String message = plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "new-player-gived-coins").replace("{coin}", String.valueOf(defaultCoins));
                        TextComponent text = JsonChatUtil.getFromLegacy(message);
                        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(I18n.color(
                                "&7简介 ▶ &c复活币是一种可以让你死而复生的货币.\n" +
                                        "     ▶ &c如果没有复活币,你将无法复活,灵魂将受到惩罚."))));
                        user.spigot().sendMessage(text);
                    }
                }
            }
        } catch (SQLException exception) {
            plugin.getLang().log.error(I18n.INIT, "数据库", exception, null);
        }
    }

    public int getCoins(UUID uuid) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT `coins` FROM `revivecoin` WHERE `uuid` = ?")) {
                statement.setString(1, uuid.toString());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next())
                    return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            plugin.getLang().log.error(I18n.GET, "数据库", exception, null);
        }
        return 0;
    }

    public void setCoins(UUID uuid, int coins) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `revivecoin` SET `coins` = ? WHERE `uuid` = ?"
            )) {
                statement.setInt(1, coins);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            plugin.getLang().log.error(I18n.SET, "数据库", exception, null);
        }
    }

    public void setCoins(UUID user, UUID target, int coins) {
        setCoins(user, getCoins(user) - coins);
        setCoins(target, getCoins(target) + coins);
    }
}
