package me.mical.revivecoinreremake.internal.nms.v1_16_R2;

import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.ChatMessageType;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionBarManagerImpl implements me.mical.revivecoinreremake.internal.nms.ActionBarManagerImpl {
    private static final UUID ZERO = new UUID(0, 0);

    @Override
    public void sendActionBar(Player user, String message) {
        EntityPlayer user1 = ((CraftPlayer) user).getHandle();
        user1.playerConnection.sendPacket(new PacketPlayOutChat(new ChatComponentText(message), ChatMessageType.GAME_INFO, ZERO));
    }
}