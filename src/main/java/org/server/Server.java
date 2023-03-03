package org.server;

public class Server {

    public static void main(String[] args) {
        Configuration configuration = Configuration.builder().load("configuration.yaml");
    }
}