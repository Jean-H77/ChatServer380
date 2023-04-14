package org.server.net.packet.decoders;

import org.server.Server;
import org.server.model.User;
import org.server.net.packet.InboundPacketHandler;
import org.server.net.packet.Packet;
import org.server.net.packet.encoders.GroupMessageEncoder;
import org.server.utils.MessageUtils;

import java.util.List;

public final class GroupMessageDecoder implements InboundPacketHandler {

    @Override
    public void handleMessage(User user, Packet packet) {
        String message = packet.readString();
        long groupId = 5; // get users current groupId

        if(MessageUtils.containsProfanity(message)) {
            return;
        }

        Thread.startVirtualThread(() -> {
           // save message to database
        });

        List<User> users = Server.getInstance().getUserListByGroupChatId(groupId);
        for(User u : users) {
            u.getSession().send(new GroupMessageEncoder(message, user.getUsername(), groupId, user.getProfileImage()));
        }
    }
}
