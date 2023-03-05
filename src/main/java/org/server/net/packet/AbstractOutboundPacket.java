package org.server.net.packet;

import org.server.model.Session;

public abstract class AbstractOutboundPacket {

    protected final PacketBuilder builder;

    protected AbstractOutboundPacket(int opcode) {
        this.builder = new PacketBuilder(opcode);
    }

    protected abstract void encode();

    public void send(Session session) {
        encode();
        session.channel().write(builder.toPacket());
    }
}
