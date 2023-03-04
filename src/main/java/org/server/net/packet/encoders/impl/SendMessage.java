package org.server.net.packet.encoders.impl;

import org.server.net.packet.encoders.OutgoingPacket;

public class SendMessage extends OutgoingPacket {

    private static final int OPCODE = 10;

    private final String message;

    public SendMessage(String message) {
        super(OPCODE);
        this.message = message;
    }

    @Override
    public void encode() {
        builder.writeString(message);
    }
}
