package org.server.net.packet.decoders;

import org.server.net.packet.Packet;

public final class ButtonClickDecoder implements IncomingPacketAdapter {

    @Override
    public void handleMessage(Packet packet) {
        byte buttonId = packet.readByte();

        switch (buttonId) {

        }
    }
}
