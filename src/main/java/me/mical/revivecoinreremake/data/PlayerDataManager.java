package me.mical.revivecoinreremake.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import me.mical.revivecoinreremake.config.DataManager;
import org.serverct.parrot.parrotx.data.PData;
import org.serverct.parrot.parrotx.data.PID;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.io.File;
import java.io.IOException;

@ToString
public class PlayerDataManager extends PData {

    @Getter
    @Setter
    private String uuid;
    @Getter
    @Setter
    private int coins;

    public PlayerDataManager(File file, PID id) {
        super(file, id);
        load(file);
    }

    public PlayerDataManager(String uuid, int coins) {
        super(new File(DataManager.getInst().getFile(), uuid + ".yml"), DataManager.getInst().buildId(uuid));
        this.uuid = uuid;
        this.coins = coins;
        save();
    }

    @Override
    public String getTypename() {
        return "玩家数据/" + getFilename();
    }

    @Override
    public void save() {
        if (!this.file.exists()) {
            data.set("Player", uuid);
            data.set("Coins", coins);
            try {
                data.save(file);
            } catch (IOException exception) {
                this.plugin.getLang().log.error(I18n.SAVE, getTypename(), exception, null);
            }
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void saveDefault() {
    }

    @Override
    public void load(@NonNull File file) {
        try {
            this.uuid = getFilename();
            this.uuid = data.getString("Player");
            this.coins = data.getInt("Coins");
        } catch (Throwable e) {
            this.plugin.getLang().log.error(I18n.LOAD, getTypename(), e, null);
        }
    }
}
