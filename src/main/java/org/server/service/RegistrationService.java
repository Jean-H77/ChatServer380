package org.server.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.server.model.ProfileImage;
import org.server.model.request.RegisterRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RegistrationService {

    private static final int INVALID_EMAIL_CODE = 1;
    private static final int INVALID_USERNAME_LENGTH_CODE = 2;
    private static final int INVALID_PASSWORD_CODE = 3;
    private static final int INVALID_DATE_CODE = 4;
    private static final int INVALID_PROFILE_IMAGE_CODE = 5;

    private static final Pattern upperCasePatten = Pattern.compile("[A-Z ]");
    private static final Pattern digitCasePatten = Pattern.compile("[0-9 ]");

    public void validateRegistration(RegisterRequest rr) {
        String email = rr.getRegistrationDetails().email();
        String password = rr.getRegistrationDetails().password();
        String username = rr.getRegistrationDetails().username();
        ProfileImage profileImage = rr.getRegistrationDetails().profileImage();
        String DOB = rr.getRegistrationDetails().dateOfBirth();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(rr.getRegistrationDetails().dateOfBirth());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(date != null) {
            List<Integer> invalid = new ArrayList<>();

            // check valid email
            if(!EmailValidator.getInstance().isValid(email)) {
                invalid.add(INVALID_EMAIL_CODE);
                // then check database if username exists
            }

            // check valid username
            if(!(username.length() > 5 && username.length() < 15)) {
                invalid.add(INVALID_USERNAME_LENGTH_CODE);
            }

            // check valid password
            if(!(upperCasePatten.matcher(password).find() && digitCasePatten.matcher(password).find() && password.length() >= 8)) {
                invalid.add(INVALID_PASSWORD_CODE);
            }

            // check valid date >= 12 years olds
            LocalDate now = LocalDate.now();
            LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(!inputDate.isBefore(now.minusYears(12))) {
                invalid.add(INVALID_DATE_CODE);
            }

            // check valid profile picture
            byte[] imageData = profileImage.data();
            try {
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));

                // check if image has a max of 128x128 dimensions
                if((bufferedImage.getHeight() > 128 || bufferedImage.getWidth() > 128)) {
                    invalid.add(INVALID_PROFILE_IMAGE_CODE);
                }

            } catch (Exception e) {
                invalid.add(INVALID_PROFILE_IMAGE_CODE);
            }

            if(invalid.size() > 0) {
                // send invalid response codes to client
            }
        }
    }


    private void completeRegistration(RegisterRequest rr) {

    }
}
