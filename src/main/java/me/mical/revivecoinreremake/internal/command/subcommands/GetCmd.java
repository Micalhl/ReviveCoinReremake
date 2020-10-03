package me.mical.revivecoinreremake.internal.command.subcommands;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.internal.config.ConfigManager;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.i18n.I18n;
import org.serverct.parrot.parrotx.utils.JsonChatUtil;

import java.util.HashMap;
import java.util.Map;

public class GetCmd extends BaseCommand {

    public GetCmd() {
        super(ReviveCoinReremake.getInst(), "get", 1);
        describe("获取指定数量的复活粒");
        perm("ReviveCoin.admin.get");
        mustPlayer(true);
    }

    @Override
    protected void call(String[] args) {
        try {
            ItemStack itemStack = ConfigManager.REVIVE_COIN;
            itemStack.setAmount(Integer.parseInt(args[0]));

            String getMsg = info(plugin.getLang().data.get(plugin.localeKey, "Lang", "successful-get-reviveCoin"));
            TextComponent getMsgComponent = JsonChatUtil.getFromLegacy(getMsg);
            getMsgComponent.setHoverEvent(
                    new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            TextComponent.fromLegacyText(I18n.color("&7简介 ▶ &c右键复活粒可以获得一枚复活币,同时也会消耗一枚复活粒."))
                    )
            );
            user.spigot().sendMessage(getMsgComponent);

            HashMap<Integer, ItemStack> out = user.getInventory().addItem(itemStack);
            if (!out.isEmpty()) {
                String msg = plugin.getLang().data.get(plugin.localeKey, "Lang", "inventory-is-full");
                I18n.send(user, warn(msg));

                for (Map.Entry<Integer, ItemStack> entry : out.entrySet()) {
                    user.getLocation().getWorld().dropItem(user.getLocation(), entry.getValue());
                }
            }
        } catch (NumberFormatException exception) {
            String msg = plugin.getLang().data.get(plugin.localeKey, "Lang", "illgeal-argument");
            I18n.send(user, error(msg));
        }
    }
}
