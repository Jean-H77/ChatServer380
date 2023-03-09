package org.server.net.packet.encoders;

import org.server.net.packet.OutboundPacket;

public final class DirectMessageEncoder extends OutboundPacket {

    private static final int OPCODE = 10;

    private final String message;

    public DirectMessageEncoder(String message) {
        super(OPCODE);
        this.message = message;
    }

    @Override
    public void encode() {
        builder.writeString(message);
    }
}
