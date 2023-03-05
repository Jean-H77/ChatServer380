package org.server.net.packet;

public interface InboundPacketHandler {
    void handleMessage(Packet packet);
}
