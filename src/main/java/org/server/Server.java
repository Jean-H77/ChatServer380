package org.server;

public class Server {

    public static Configuration configuration;

    public static void main(String[] args) {
        configuration = Configuration.builder().load("configuration.yaml");
    }
}