package org.server.net.packet.decoders;

import org.server.Server;
import org.server.ServerLauncher;
import org.server.model.User;
import org.server.net.packet.InboundPacketHandler;
import org.server.net.packet.Packet;
import org.server.net.packet.encoders.GroupMessageEncoder;
import org.server.utils.MessageUtils;

import java.util.List;

public final class GroupMessageDecoder implements InboundPacketHandler {

    private static final Server server = ServerLauncher.server;

    @Override
    public void handleMessage(Packet packet) {
        long groupId = packet.readLong();
        String message = packet.readString();

        if(MessageUtils.containsProfanity(message)) {
            return;
        }

        Thread.startVirtualThread(() -> {
           // save message to database
        });

        // send message to all users with groupId
        List<User> users = server.getUserListByGroupChatId(groupId);
        for(User user : users) {
            user.getSession().send(new GroupMessageEncoder(message, groupId));
        }
    }
}
