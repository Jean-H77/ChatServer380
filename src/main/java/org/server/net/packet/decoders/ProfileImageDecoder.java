package org.server.net.packet.decoders;

import org.server.model.ProfileImage;
import org.server.model.User;
import org.server.net.packet.InboundPacketHandler;
import org.server.net.packet.Packet;

public class ProfileImageDecoder implements InboundPacketHandler {
    @Override
    public void handleMessage(User user, Packet packet) {
        short length = packet.readShort();
        byte[] data = packet.readBytes(length);

        user.setProfileImage(new ProfileImage(data, length));
    }
}
