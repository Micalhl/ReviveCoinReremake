package me.mical.revivecoinreremake.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviveCoinAddEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private Player player;
    private int coins;

    public ReviveCoinAddEvent(Player player, int coins) {
        this.player = player;
        this.coins = coins;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
