package org.server.net.packet.decoders;

import org.server.net.packet.Packet;

public sealed interface IncomingPacket permits ButtonClickDecoder, DirectMessageDecoder, GroupMessageDecoder {
    void handleMessage(Packet packet);
}
