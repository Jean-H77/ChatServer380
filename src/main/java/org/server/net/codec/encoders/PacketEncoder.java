package org.server.net.codec.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.net.packet.Packet;

@ChannelHandler.Sharable
public final class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        out.writeByte(packet.opcode());
        out.writeBytes(packet.payload());
    }
}
