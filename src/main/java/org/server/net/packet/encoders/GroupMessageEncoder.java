package org.server.net.packet.encoders;

import org.server.model.ProfileImage;
import org.server.net.packet.AbstractOutboundPacket;

public class GroupMessageEncoder extends AbstractOutboundPacket {

    private final String message;
    private final String senderName;
    private final long groupId;
    private final ProfileImage profileImage;

    public GroupMessageEncoder(String message, String senderName, long groupId, ProfileImage profileImage) {
        super(11);
        this.message = message;
        this.senderName = senderName;
        this.groupId = groupId;
        this.profileImage = profileImage;
    }

    @Override
    protected void encode() {
        builder.writeString(message);
        builder.writeString(senderName);
        builder.writeLong(groupId);
        builder.writeShort(profileImage.size());
        builder.writeBytes(profileImage.data());
    }
}
