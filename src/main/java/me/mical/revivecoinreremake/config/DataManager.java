package me.mical.revivecoinreremake.config;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.data.PlayerDataManager;
import org.serverct.parrot.parrotx.config.PFolder;
import org.serverct.parrot.parrotx.utils.BasicUtil;

import java.io.File;
import java.util.Objects;

public class DataManager extends PFolder<PlayerDataManager> {

    private static DataManager inst;

    public static DataManager getInst() {
        if (Objects.isNull(inst)) {
            inst = new DataManager();
        }
        return inst;
    }

    public DataManager() {
        super(ReviveCoinReremake.getInst(), "data", "数据文件夹", "RCRDATA");
    }

    @Override
    public PlayerDataManager loadData(File file) {
        return new PlayerDataManager(file, buildId(BasicUtil.getNoExFileName(file.getName())));
    }
}
