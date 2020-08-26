package cn.galaxy233.revivecoinreremake.features;

import cn.galaxy233.revivecoinreremake.ReviveCoinReremake;
//import com.Zrips.CMI.CMI;
//import com.Zrips.CMI.Modules.Warps.CmiWarp;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;

public class WarpControl {

    public static void teleportWarp(Player user, String paramWarp) {
        PPlugin plugin = ReviveCoinReremake.getInstance();
        //CmiWarp warp = CMI.getInstance().getWarpManager().getWarp(paramWarp);
        //if (warp == null) {
        //    plugin.lang.logError("传送", "玩家" + user.getName() + "至地标" + paramWarp, "目标地标不存在");
        //} else {
            //Location location = warp.getLocIncRand().getBukkitLoc();
            //user.teleport(location);
        //    plugin.lang.logAction("传送", "玩家" + user.getName() + "至地标" + paramWarp);
        //}
    }
}
