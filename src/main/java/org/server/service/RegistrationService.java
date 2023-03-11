package org.server.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.server.model.request.RegisterRequest;

public class RegistrationService {
    private static final int RESPONSE_OPCODE = 10;
    private static final int SUCCESS = 1;
    private static final int ACCOUNT_ALREADY_EXISTS = 0;

    public void validateRegistration(RegisterRequest rr) {
        String email = rr.getRegistrationDetails().email();
        int response = SUCCESS;

        if(email.equalsIgnoreCase("alreadyexists@gmail.com")) {
            response = ACCOUNT_ALREADY_EXISTS;
        }

        completeAndSendResponse(rr, response);
    }

    private void completeAndSendResponse(RegisterRequest rr, int response) {
        if(response == SUCCESS) {

        }

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(RESPONSE_OPCODE);
        byteBuf.writeByte(response);
        rr.getChannel().write(byteBuf);
    }
}
