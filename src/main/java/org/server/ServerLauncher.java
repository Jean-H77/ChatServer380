package org.server;

public class ServerLauncher {

    public static final String CONFIGURATION_PATH = "configuration.yaml";

    public static final Configuration configuration = Configuration.load(CONFIGURATION_PATH);

    public static void main(String[] args) throws InterruptedException {
        launch();
    }

    public static void launch() throws InterruptedException {
        new Server(configuration).run();
    }
}
