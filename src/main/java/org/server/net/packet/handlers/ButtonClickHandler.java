package org.server.net.packet.handlers;

import org.server.net.packet.IncomingPacket;
import org.server.net.packet.Packet;

public class ButtonClickHandler implements IncomingPacket {

    @Override
    public void handleMessage(Packet packet) {
        byte buttonId = packet.readByte();

        switch (buttonId) {

        }
    }
}
