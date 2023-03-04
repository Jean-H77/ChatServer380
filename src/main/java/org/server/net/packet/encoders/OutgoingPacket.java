package org.server.net.packet.encoders;

import org.server.model.Session;
import org.server.net.packet.PacketBuilder;

public abstract sealed class OutgoingPacket permits DirectMessageEncoder {

    protected final PacketBuilder builder;

    protected OutgoingPacket(int opcode) {
        this.builder = new PacketBuilder(opcode);
    }

    protected abstract void encode();

    public void send(Session session) {
        session.channel().write(builder.toPacket());
    }
}
