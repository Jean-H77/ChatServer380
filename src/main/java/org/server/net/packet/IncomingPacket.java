package org.server.net.packet;

public interface IncomingPacket {
    void handleMessage(Packet packet);
}
