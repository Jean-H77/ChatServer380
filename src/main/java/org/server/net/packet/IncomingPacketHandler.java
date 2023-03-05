package org.server.net.packet;

public interface IncomingPacketHandler {
    void handleMessage(Packet packet);
}
