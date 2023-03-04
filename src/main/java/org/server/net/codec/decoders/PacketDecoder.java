package org.server.net.codec.decoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.server.net.packet.Packet;

import java.util.List;

@ChannelHandler.Sharable
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int opcode = in.readUnsignedByte();
        ByteBuf payload = in.readBytes(in.readableBytes());
        out.add(new Packet(opcode, payload));
    }

}
