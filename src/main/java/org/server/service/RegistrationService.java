package org.server.service;

import com.sanctionco.jmail.JMail;
import io.netty.buffer.Unpooled;
import org.server.model.RegistrationDetails;
import org.server.model.request.RegisterRequest;
import org.server.persistance.Database;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public final class RegistrationService {

    private static final int RESPONSE_OPCODE = 10;
    private static final int INVALID_EMAIL_BIT = 1;
    private static final int INVALID_USERNAME_BIT = 2;
    private static final int INVALID_DOB_BIT = 3;
    private static final int INVALID_PASSWORD_BIT = 3;

    private static final int REQUIRED_AGE = 12;
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 15;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private final ExecutorService registerExecutorService = Executors.newFixedThreadPool(1);
    private final BlockingQueue<RegisterRequest> requestBlockingQueue = new LinkedBlockingQueue<>();
    private final Database database = new Database();

    public void addRequest(RegisterRequest rr) {
        requestBlockingQueue.add(rr);
    }

    public void loadWorker() {
        registerExecutorService.submit(new RegistrationWorker());
    }

    class RegistrationWorker implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    RegisterRequest request = requestBlockingQueue.take();
                    RegistrationDetails details = request.getRegistrationDetails();

                    String email = details.email();
                    String username = details.username();
                    String password = details.password();
                    String dob = details.dateOfBirth();

                    byte response = 0;

                    if(JMail.isInvalid(email) || database.emailExists(email)) {
                        response |= 1 << INVALID_EMAIL_BIT;
                    }

                    if(!(username.length() >= MIN_LENGTH && username.length() <= MAX_LENGTH)) {
                        response |= 1 << INVALID_USERNAME_BIT;
                    }
                    
                    if(!LocalDate.parse(dob, formatter).isBefore(ChronoLocalDate.from(ZonedDateTime.now().minusYears(REQUIRED_AGE)))) {
                        response |= 1 << INVALID_DOB_BIT;
                    }

                    if(!(password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH)) { // add password regex pattern (required 1 uppercase, 1 number, 1 special character
                        response |= 1 << INVALID_PASSWORD_BIT;
                    }

                    if(response == 0) {
                        // @todo successful registration work
                    }

                    request.getChannel().writeAndFlush(Unpooled.buffer().setByte(0, RESPONSE_OPCODE).setByte(1, response));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
