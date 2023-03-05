package org.server.net.packet;

import org.server.Server;
import org.server.ServerLauncher;
import org.server.model.User;

public interface InboundPacketHandler {
    Server server = ServerLauncher.server;
    void handleMessage(User user, Packet packet);
}
