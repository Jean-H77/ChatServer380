package org.server.net.codec.encoders;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.server.Server;
import org.server.model.User;
import org.server.net.codec.decoders.PacketDecoder;

public final class LoginRegistrationEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        ctx.writeAndFlush(msg);
    }
}
