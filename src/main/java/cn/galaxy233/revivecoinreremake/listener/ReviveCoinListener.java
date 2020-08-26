package cn.galaxy233.revivecoinreremake.listener;

import cn.galaxy233.revivecoinreremake.ReviveCoinReremake;
import cn.galaxy233.revivecoinreremake.features.ReviveCoin;
import cn.galaxy233.revivecoinreremake.utils.Configuration;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.I18n;

public class ReviveCoinListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        /*
        ItemStack reviveCoin = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta itemMeta = reviveCoin.getItemMeta();
        itemMeta.setDisplayName(Configuration.REVIVE_COIN_DISPLAY_NAME);
        itemMeta.setLore(Configuration.LORE);
        reviveCoin.setItemMeta(itemMeta);
        PPlugin plugin = ReviveCoinReremake.getInstance();
        try {
            ItemStack itemStack = event.getItem();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (itemStack.isSimilar(reviveCoin)) {
                    if (itemStack.getAmount() > 1) {
                        I18n.send(event.getPlayer(), plugin.lang.build(plugin.localeKey, I18n.Type.WARN, "为防止BUG出现,物品栏里的复活粒数量必须为&c1&7."));
                    } else if (itemStack.getAmount() == 1) {
                        itemStack.setAmount(0);
                        ReviveCoin.getReviveCoin().add(event.getPlayer(), 1);
                        I18n.send(event.getPlayer(), plugin.lang.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-get-coin-by-reviveCoin"));
                    }
                }
            }
        } catch (NullPointerException ignore) {}

         */
    }
}

