package org.server.net.packet.decoders;

import org.server.model.User;
import org.server.net.packet.InboundPacketHandler;
import org.server.net.packet.Packet;

public final class ButtonClickDecoder implements InboundPacketHandler {

    @Override
    public void handleMessage(User user, Packet packet) {
        byte buttonId = packet.readByte();

        switch (buttonId) {

        }
    }
}
