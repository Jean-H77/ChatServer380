package org.server.net.packet.decoders;

import org.server.Server;
import org.server.model.User;
import org.server.net.packet.InboundPacketHandler;
import org.server.net.packet.Packet;
import org.server.net.packet.encoders.GroupMessageEncoder;

import java.util.Set;

public final class GroupMessageDecoder implements InboundPacketHandler {

    @Override
    public void handleMessage(User user, Packet packet) {
        String message = packet.readString();
        String imageLink = user.getProfileImage();
        String username = user.getUsername();
        long uuid = user.getUuid();
        long groupId = user.getCurrentGroupChatId();

        Thread.startVirtualThread(() -> {
           // save message to database
        });

        Set<User> users = Server.getInstance().getUsersByGroupChatId(groupId);
        for(User u : users) {
            if(u == null) continue;
            u.getSession().send(new GroupMessageEncoder(message, username, groupId, imageLink, uuid));
        }
    }
}
