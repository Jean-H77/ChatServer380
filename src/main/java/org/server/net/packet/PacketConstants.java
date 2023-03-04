package org.server.net.packet;

import org.server.net.packet.decoders.ButtonClickDecoder;
import org.server.net.packet.decoders.DirectMessageDecoder;
import org.server.net.packet.decoders.GroupMessageDecoder;
import org.server.net.packet.decoders.IncomingPacket;

import java.util.HashMap;

public final class PacketConstants {

    public static final HashMap<Integer, IncomingPacket> INCOMING_PACKET_DECODERS = new HashMap<>();

    private static final int BUTTON_CLICK_OPCODE = 1;
    private static final int DIRECT_MESSAGE_OPCODE = 2;
    private static final int GROUP_MESSAGE_OPCODE = 3;

    static {
        INCOMING_PACKET_DECODERS.put(BUTTON_CLICK_OPCODE, new ButtonClickDecoder());
        INCOMING_PACKET_DECODERS.put(DIRECT_MESSAGE_OPCODE, new DirectMessageDecoder());
        INCOMING_PACKET_DECODERS.put(GROUP_MESSAGE_OPCODE, new GroupMessageDecoder());
    }

    private PacketConstants() {

    }
}
