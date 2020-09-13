package me.mical.revivecoinreremake.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviveCoinGiveOtherEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private Player user1;
    private Player user2;
    private int coins;

    public ReviveCoinGiveOtherEvent(Player user1, Player user2, int coins) {
        this.user1 = user1;
        this.user2 = user2;
        this.coins = coins;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
