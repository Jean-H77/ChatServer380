package org.server.net.packet;

import org.server.model.Session;

public abstract class OutboundPacket {

    protected final PacketBuilder builder;

    protected OutboundPacket(int opcode) {
        this.builder = new PacketBuilder(opcode);
    }

    protected abstract void encode();

    public void send(Session session) {
        encode();
        session.getChannel().write(builder.toPacket());
        builder.getPayload().release();
    }
}
