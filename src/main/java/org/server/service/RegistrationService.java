package org.server.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.server.model.request.RegisterRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RegistrationService {
    private static final int INVALID_OPCODE = 10;
    private static final int INVALID_EMAIL_CODE = 1;
    private static final int INVALID_USERNAME_LENGTH_CODE = 2;
    private static final int INVALID_PASSWORD_CODE = 3;
    private static final int INVALID_DATE_CODE = 4;
    private static final int INVALID_PROFILE_IMAGE_CODE = 5;

    private static final Pattern upperCasePatten = Pattern.compile("[A-Z ]");
    private static final Pattern digitCasePatten = Pattern.compile("[0-9 ]");
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public void validateRegistration(RegisterRequest rr) {
        String email = rr.getRegistrationDetails().email();
        String password = rr.getRegistrationDetails().password();
        String username = rr.getRegistrationDetails().username();
        String DOB = rr.getRegistrationDetails().dateOfBirth();
        Date date = null;

        try {
            date = dateFormatter.parse(DOB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> invalidCodes = new ArrayList<>();

        // check valid email
        if(!EmailValidator.getInstance().isValid(email)) {
            invalidCodes.add(INVALID_EMAIL_CODE);
        }

        /**
         * @// TODO: 3/8/2023
         * if(email exists in database)
         */

        // check valid username
        if(!(username.length() > 5 && username.length() < 15)) {
            invalidCodes.add(INVALID_USERNAME_LENGTH_CODE);
        }

        // check valid password
        if(!(upperCasePatten.matcher(password).find() && digitCasePatten.matcher(password).find() && password.length() >= 8)) {
            invalidCodes.add(INVALID_PASSWORD_CODE);
        }

        if(date == null) {
            invalidCodes.add(INVALID_DATE_CODE);
        } else {
            // check valid date >= 12 years olds
            LocalDate now = LocalDate.now();
            LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (!inputDate.isBefore(now.minusYears(12))) {
                invalidCodes.add(INVALID_DATE_CODE);
            }
        }

        // send invalid responses to client
        if(invalidCodes.size() > 0) {
            ByteBuffer buffer = ByteBuffer.allocate(invalidCodes.size() + 1); //+1 for opcode

            buffer.put((byte) INVALID_OPCODE); // 10 for the packet opcode
            for(Integer code : invalidCodes) {
                buffer.put(code.byteValue());
            }

            rr.getChannel().writeAndFlush(buffer.array());
            return;
        }

        // code comes here if validation is a success
        completeRegistration(rr);
    }

    private void completeRegistration(RegisterRequest rr) {

    }
}
