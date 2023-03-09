package org.server.net.codec.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.server.Server;
import org.server.model.Session;
import org.server.model.request.LoginRequest;
import org.server.model.request.RegisterRequest;
import org.server.net.packet.Packet;

import java.io.IOException;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Session session = (Session)ctx.channel().attr(AttributeKey.valueOf("session.key")).get();

        if(session == null) {
            return;
        }

        if(msg instanceof Packet) {
            session.read((Packet) msg);
        } else if(msg instanceof LoginRequest loginRequest) {
            loginRequest.setChannel(ctx.channel());
            Server.getInstance().loginService.addLoginRequest(loginRequest);
        } else if(msg instanceof RegisterRequest registerRequest) {
            registerRequest.setChannel(ctx.channel());
            Server.getInstance().registrationService.validateRegistration(registerRequest);
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

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().close();
        super.channelUnregistered(ctx);
    }
}
