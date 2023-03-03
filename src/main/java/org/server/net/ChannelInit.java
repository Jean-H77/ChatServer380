package org.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.server.Server;
import org.server.net.codec.decoders.PacketDecoder;
import org.server.net.codec.encoders.PacketEncoder;
import org.server.net.codec.handlers.ServerChannelHandler;

public class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();

        if(Server.configuration.useSSL()) {
            //@todo ssl configuration
        }

        p.addLast(new PacketEncoder());
        p.addLast(new PacketDecoder());
        p.addLast(new ServerChannelHandler());
    }
}
