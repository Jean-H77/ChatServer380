package org.server.net.packet;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public record Packet(int opcode, ByteBuf payload) {

    private static final int STRING_TERMINATOR = 0;

    public short readUByte() {
        return payload.readUnsignedByte();
    }

    public byte readByte() {
        return payload.readByte();
    }

    public String readString() {
        byte[] bytes = new byte[payload.readableBytes()];
        byte b;
        int i = 0;
        while((b = payload.readByte()) != STRING_TERMINATOR) {
            bytes[i] = b;
            i++;
        }
        return new String(Arrays.copyOfRange(bytes, 0, i), StandardCharsets.UTF_8);
    }

    public short readShort() {
        return payload.readShort();
    }

    public int readUShort() {
        return payload.readUnsignedShort();
    }

    public long readLong() {
        return payload.readLong();
    }

    public double readDouble() {
        return payload.readDouble();
    }

    public float readFloat() {
        return payload.readFloat();
    }

    public int readInt() {
        return payload.readInt();
    }

    public long readUInt() {
        return payload.readUnsignedInt();
    }
}
