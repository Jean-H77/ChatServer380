package org.server.net.codec.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.server.model.Session;
import org.server.net.packet.Packet;

import java.io.IOException;

public class ServerChannelHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Session session = (Session)ctx.channel().attr(AttributeKey.valueOf("session.key")).get();

        if(session == null) {
            return;
        }

        if(msg instanceof Packet) {
            session.read((Packet) msg);
        }

        ReferenceCountUtil.release(msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
        if (!(t instanceof IOException)) {
            t.printStackTrace();
        }
        try {
            ctx.channel().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
