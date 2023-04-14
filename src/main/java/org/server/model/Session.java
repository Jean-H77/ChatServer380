package org.server.model;

import io.netty.channel.Channel;
import org.server.net.packet.OutboundPacket;
import org.server.net.packet.Packet;
import org.server.net.packet.PacketConstants;

/**
 * @// TODO: 3/2/2023 documentation
 */
public class Session {

    private final Channel channel;
    private User user;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public void send(OutboundPacket packet) {
        packet.send(this);
    }

    public void read(Packet packet) {
        int opcode = packet.opcode();
        PacketConstants.PACKET_DECODERS.get(opcode).handleMessage(user, packet);
        packet.payload().release();
    }

    public Channel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
