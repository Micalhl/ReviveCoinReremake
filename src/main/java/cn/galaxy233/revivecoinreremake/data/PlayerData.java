package cn.galaxy233.revivecoinreremake.data;

import lombok.NonNull;
import org.serverct.parrot.parrotx.data.PData;
import org.serverct.parrot.parrotx.data.PID;

import java.io.File;

public class PlayerData extends PData {
    public PlayerData(File file, PID id) {
        super(file, id);
    }

    @Override
    public String getTypeName() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void saveDefault() {

    }

    @Override
    public void load(@NonNull File file) {

    }

    @Override
    public void save() {

    }
}
