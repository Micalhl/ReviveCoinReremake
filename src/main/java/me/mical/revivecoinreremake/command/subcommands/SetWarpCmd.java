package me.mical.revivecoinreremake.command.subcommands;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.config.ConfigManager;
import org.bukkit.Location;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.BasicUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class SetWarpCmd extends BaseCommand {
    public SetWarpCmd() {
        super(ReviveCoinReremake.getInst(), "setwarp", 0);
        mustPlayer(true);
        describe("设置脚下为监狱坐标点");
        perm("ReviveCoin.setWarp");
    }

    @Override
    protected void call(String[] strings) {
        Location location = user.getLocation();
        ConfigManager.setWarp(location);
        String replace = BasicUtil.formatLocation(location);
        I18n.send(user, plugin.getLang().data.get(plugin.localeKey, I18n.Type.INFO, "Lang", "successful-set-warp", replace));
    }
}
