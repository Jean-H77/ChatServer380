package org.server.net.packet;

import io.netty.buffer.ByteBuf;

public class PacketBuilder {

    private static final int STRING_TERMINATOR = 0;

    /**
     * Opcode of the packet
     */
    private final int opcode;

    /**
     * The packets payload
     */
    private ByteBuf payload;

    public PacketBuilder(int opcode) {
        this.opcode = opcode;
    }

    public Packet toPacket() {
        return new Packet(opcode, payload);
    }

    public PacketBuilder writeString(String string) {
        payload.writeBytes(string.getBytes()).writeByte(STRING_TERMINATOR);
        return this;
    }

    public PacketBuilder writeInt(int value) {
        payload.writeInt(value);
        return this;
    }

    public PacketBuilder writeByte(int value) {
        payload.writeByte(value);
        return this;
    }

    public PacketBuilder writeLong(long value) {
        payload.writeLong(value);
        return this;
    }

    public PacketBuilder writeShort(int value) {
        payload.writeShort(value);
        return this;
    }
}
