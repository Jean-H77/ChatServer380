package org.server.net.packet.encoders;

import org.server.net.packet.AbstractOutboundPacket;

public final class DirectMessageEncoder extends AbstractOutboundPacket {

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
