package org.server.net.packet;

import io.netty.buffer.ByteBuf;
import org.server.ServerConstants;

public final class PacketBuilder {

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

    /**
     * Converts the opcode and the payload to a packet for use
     * @return The created Packet
     */
    public Packet toPacket() {
        return new Packet(opcode, payload);
    }

    public PacketBuilder writeString(String string) {
        payload.writeBytes(string.getBytes()).writeByte(ServerConstants.STRING_TERMINATOR);
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

    public PacketBuilder writeBytes(byte[] bytes) {
        payload.writeBytes(bytes);
        return this;
    }
}
