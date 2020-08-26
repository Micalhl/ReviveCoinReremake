package cn.galaxy233.revivecoinreremake.hooks;

import cn.galaxy233.revivecoinreremake.features.ReviveCoin;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

@Deprecated
public class Placeholders extends PlaceholderHook {

    public static String hook_name = "ReviveCoin";

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(hook_name, new Placeholders());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(hook_name);
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {

        if (params.equals("coins"))
            return String.valueOf(ReviveCoin.getReviveCoin().get(p));
        return null;

    }
}
