package cn.galaxy233.revivecoinreremake.api;

import org.bukkit.entity.Player;

public interface CoinAPI {

    void add(Player user, int coins);

    void give(Player user1, Player user2, int coins);

    void take(Player user, int coins);

    boolean has(Player user, int coins);

    int get(Player user);
}
