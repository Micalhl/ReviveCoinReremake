package me.mical.revivecoinreremake.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviveCoinEvent extends Event implements Cancellable {

    private Type type;
    private Player user;
    private Player target;
    private int coins;

    public ReviveCoinEvent(Type type, Player user, int coins) {
        this.type = type;
        this.user = user;
        this.coins = coins;
    }

    public ReviveCoinEvent(Type type, Player user, Player target, int coins) {
        this.type = type;
        this.user = user;
        this.target = target;
        this.coins = coins;
    }

    private static HandlerList handlerList = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    private boolean isCancelled = false;

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    public enum Type {
        ADD, REDUCE, GIVE
    }
}
