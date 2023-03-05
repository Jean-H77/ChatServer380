package org.server.net.packet;

import org.server.model.Session;

public abstract class AbstractOutgoingPacket {

    protected final PacketBuilder builder;

    protected AbstractOutgoingPacket(int opcode) {
        this.builder = new PacketBuilder(opcode);
    }

    protected abstract void encode();

    public void send(Session session) {
        encode();
        session.channel().write(builder.toPacket());
    }
}
