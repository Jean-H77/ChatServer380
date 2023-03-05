package org.server.net.codec.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.net.packet.Packet;

public final class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        int size = 1 + packet.payload().readableBytes();
        ByteBuf buffer = Unpooled.buffer(size);
        buffer.writeByte(packet.opcode());
        buffer.writeBytes(packet.payload());
        ctx.writeAndFlush(buffer);
    }
}
