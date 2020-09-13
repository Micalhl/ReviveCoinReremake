package me.mical.revivecoinreremake.hooks;

import me.mical.revivecoinreremake.utils.CoinUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.PPlugin;

import java.util.Objects;

public class PlaceholderHook extends PlaceholderExpansion {

    protected PPlugin plugin;

    public PlaceholderHook(PPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "revivecoin";
    }

    public void hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (Objects.nonNull(plugin)) {
            register();
        }
    }

    public void unhook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (Objects.nonNull(plugin)) {
            if (isRegistered()) {
                unregister();
            }
        }
    }

    @Override
    public @NotNull String getAuthor() {
        return "Mical";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equals("coins"))
            return String.valueOf(CoinUtils.get(player));
        return "";
    }
}
