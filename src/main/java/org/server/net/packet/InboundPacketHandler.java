package org.server.net.packet;

import org.server.model.User;

public interface InboundPacketHandler {
    void handleMessage(User user, Packet packet);
}
