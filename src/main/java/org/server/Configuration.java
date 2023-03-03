package org.server;

public class Configuration {

    // database variables
    private String jdbcUrl;
    private String username;
    private String password;

    // server variables
    private int port;
    private boolean SSL;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public boolean isSSL() {
        return SSL;
    }

    public static class Builder {

        private final Configuration configuration;

        public Builder(Configuration configuration) {
            this.configuration = configuration;
        }

        public Configuration load(String path) {
            return null;
        }

        public Configuration setSSL(boolean SSL) {
            configuration.SSL = SSL;
            return configuration;
        }

        public Configuration setPort(int port) {
            configuration.port = port;
            return configuration;
        }

        public Configuration setPassword(String password) {
            configuration.password = password;
            return configuration;
        }

        public Configuration setUsername(String username) {
            configuration.username = username;
            return configuration;
        }

        public Configuration setJdbcUrl(String jdbcUrl) {
            configuration.jdbcUrl = jdbcUrl;
            return configuration;
        }
    }
}
