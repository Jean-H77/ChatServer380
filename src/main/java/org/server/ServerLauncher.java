package org.server;

import static org.server.ServerConstants.CONFIGURATION_PATH;

public final class ServerLauncher {

    public static final Configuration configuration = Configuration.load(CONFIGURATION_PATH);

    public final static Server server = new Server(configuration);

    public static void main(String[] args) throws InterruptedException {
        launch();
    }

    public static void launch() throws InterruptedException {
        server.run();
    }
}
