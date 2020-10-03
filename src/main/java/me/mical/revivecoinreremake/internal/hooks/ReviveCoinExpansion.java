package me.mical.revivecoinreremake.internal.hooks;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.util.Coin;
import org.serverct.parrot.parrotx.hooks.BaseExpansion;

public class ReviveCoinExpansion extends BaseExpansion {
    public ReviveCoinExpansion() {
        super(ReviveCoinReremake.getInst());

        addParam(PlaceholderParam.builder()
                .name("coins")
                .parse((user, args) -> String.valueOf(Coin.get(user))).build());
    }
}
