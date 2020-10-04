package me.mical.revivecoinreremake.internal.nms;

import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class ActionBarManager {

    public static ActionBarManagerImpl initialize(PPlugin plugin) {
        String version;
        try {
            version = plugin.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        } catch (ArrayIndexOutOfBoundsException exception) {
            plugin.getLang().log.error(I18n.INIT, "ActionBarManager", exception, null);
            return null;
        }
        switch (version) {
            case "v1_13_R1":
                return new me.mical.revivecoinreremake.internal.nms.v1_13_R1.ActionBarManagerImpl();
            case "v1_13_R2":
                return new me.mical.revivecoinreremake.internal.nms.v1_13_R2.ActionBarManagerImpl();
            case "v1_14_R1":
                return new me.mical.revivecoinreremake.internal.nms.v1_14_R1.ActionBarManagerImpl();
            case "v1_15_R1":
                return new me.mical.revivecoinreremake.internal.nms.v1_15_R1.ActionBarManagerImpl();
            case "v1_16_R1":
                return new me.mical.revivecoinreremake.internal.nms.v1_16_R1.ActionBarManagerImpl();
            case "v1_16_R2":
                return new me.mical.revivecoinreremake.internal.nms.v1_16_R2.ActionBarManagerImpl();
            default:
                plugin.getLang().log.error(I18n.INIT, "ActionBarManager", "ActionBarManager 不支持您的版本 " + version);
                return null;
        }
    }
}
