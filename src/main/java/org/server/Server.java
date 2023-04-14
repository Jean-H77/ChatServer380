package org.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.server.model.User;
import org.server.net.ChannelInit;
import org.server.service.LoginService;
import org.server.service.RegistrationService;

import java.util.*;

public class Server {

    private static Server INSTANCE;

    public final Configuration configuration;
    private final Set<User> connectedUsers = new HashSet<>();

    public final LoginService loginService = new LoginService();
    public final RegistrationService registrationService = new RegistrationService();

    public Server(Configuration configuration) {
        this.configuration = configuration;
        registrationService.registerWorker();
        INSTANCE = this;
    }
    
    public void run() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInit())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_RCVBUF, Short.MAX_VALUE*2)
                    .childOption(ChannelOption.SO_SNDBUF, Short.MAX_VALUE*2);

            ChannelFuture future = bootstrap.bind(configuration.getPort()).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public Optional<User> getUserByName(String name) {
        return connectedUsers
                .stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addNewUser(User user) {
        connectedUsers.add(user);
    }

    public boolean removeUser(User user) {
        return connectedUsers.remove(user);
    }

    public List<User> getUserListByGroupChatId(long id) {
        return connectedUsers
                .stream()
                .filter(user -> user.getGroupChatIds().contains(id))
                .toList();
    }

    public Optional<User> getUserByUuid(long id) {
        return connectedUsers
                .stream()
                .filter(user -> user.getUuid() == id)
                .findFirst();
    }

    public static Server getInstance() {
        return INSTANCE;
    }
}