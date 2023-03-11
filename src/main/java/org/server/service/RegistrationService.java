package org.server.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.server.model.request.RegisterRequest;
import org.server.persistance.Database;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public final class RegistrationService {
    private static final int RESPONSE_OPCODE = 10;
    private static final int SUCCESS = 1;
    private static final int ACCOUNT_ALREADY_EXISTS = 0;

    private final ExecutorService registerExecutorService = Executors.newFixedThreadPool(1);
    private final BlockingQueue<RegisterRequest> requestBlockingQueue = new LinkedBlockingQueue<>();

    private final Database database = new Database();

    public void addRequest(RegisterRequest rr) {
        requestBlockingQueue.add(rr);
    }

    public void loadWorker() {
        registerExecutorService.submit(new loginWorker());
    }

    class loginWorker implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    RegisterRequest request = requestBlockingQueue.take();
                    String email = request.getRegistrationDetails().email();

                    int response = SUCCESS;

                    if(database.emailExists(email)) {
                        response = ACCOUNT_ALREADY_EXISTS;
                    }

                    // @todo add other verification (name length, password, etc.)

                    if(response == SUCCESS) {
                    }

                    ByteBuf byteBuf = Unpooled.buffer();
                    byteBuf.writeByte(RESPONSE_OPCODE);
                    byteBuf.writeByte(response);
                    request.getChannel().writeAndFlush(byteBuf);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
