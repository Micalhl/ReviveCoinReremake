package cn.galaxy233.revivecoinreremake.hooks;

import cn.galaxy233.revivecoinreremake.features.ReviveCoin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.PPlugin;

public class PlaceholderHook extends PlaceholderExpansion {

    protected PPlugin plugin;

    public PlaceholderHook(PPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "revivecoin";
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
            return String.valueOf(ReviveCoin.getReviveCoin().get(player));
        return null;
    }
}
