package me.mical.revivecoinreremake.hooks;

import me.mical.revivecoinreremake.ReviveCoinReremake;
import me.mical.revivecoinreremake.utils.CoinUtils;
import org.serverct.parrot.parrotx.hooks.BaseExpansion;

public class ReviveCoinExpansion extends BaseExpansion {
    public ReviveCoinExpansion() {
        super(ReviveCoinReremake.getInst());

        addParam(PlaceholderParam.builder()
                .name("coins")
                .parse((user, args) -> String.valueOf(CoinUtils.get(user))).build());
    }
}
