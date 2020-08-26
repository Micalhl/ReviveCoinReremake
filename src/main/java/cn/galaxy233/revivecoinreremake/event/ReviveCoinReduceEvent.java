package cn.galaxy233.revivecoinreremake.event;

import com.sun.istack.internal.NotNull;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class ReviveCoinReduceEvent extends PlayerEvent {

    private static final HandlerList handlerList = new HandlerList();

    public ReviveCoinReduceEvent(Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
