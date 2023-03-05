package org.server.net.packet.decoders;

import org.server.net.packet.Packet;

public interface IncomingPacketAdapter {
    void handleMessage(Packet packet);
}
