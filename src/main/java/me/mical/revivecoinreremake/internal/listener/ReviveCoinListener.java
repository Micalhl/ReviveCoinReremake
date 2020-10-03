package me.mical.revivecoinreremake.internal.listener;

import me.mical.revivecoinreremake.internal.config.ConfigManager;
import me.mical.revivecoinreremake.util.Coin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class ReviveCoinListener implements Listener {

    private final PPlugin plugin;

    public ReviveCoinListener(PPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack != null) {
            if (event.getItem().isSimilar(ConfigManager.REVIVE_COIN)) {
                if (itemStack.getAmount() > 1) {
                    I18n.send(event.getPlayer(), plugin.getLang().data.build(plugin.localeKey, I18n.Type.WARN, "为防止BUG出现,物品栏里的复活粒数量必须为&c1&7."));
                } else if (itemStack.getAmount() == 1) {
                    itemStack.setAmount(0);
                    Coin.add(event.getPlayer(), 1);
                    I18n.send(event.getPlayer(), plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-get-coin-by-reviveCoin"));
                }
            }
        }
    }
}

