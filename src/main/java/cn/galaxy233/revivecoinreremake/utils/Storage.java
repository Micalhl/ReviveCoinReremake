package cn.galaxy233.revivecoinreremake.utils;

import lombok.NonNull;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PFolder;

public class Storage extends PFolder {
    private static Storage storage;

    public static Storage get(PPlugin plugin) {
        if (storage == null)
            storage = new Storage(plugin);
        return storage;
    }

    public Storage(@NonNull PPlugin plugin) {
        super(plugin, "dataFolder", "玩家数据文件夹", null);
    }
}
