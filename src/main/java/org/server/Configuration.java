package org.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Objects;

public class Configuration {

    // database variables
    private String jdbcUrl;
    private String username;
    private String password;

    // server variables
    private int port;
    private boolean ssl;

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

    public boolean getSsl() {
        return ssl;
    }

    public static Configuration defaultValues() {
        Configuration configuration = new Configuration();
        configuration.ssl = false;
        configuration.port = 85;
        return configuration;
    }

    public static Configuration load(String path) {
        return builder().load(path);
    }

    static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Configuration configuration;

        public Builder(Configuration configuration) {
            this.configuration = configuration;
        }

        public Builder() {

        }

        public Configuration load(String path) {
            File file = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResource(path)).getFile());

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Configuration config = Configuration.defaultValues();

            try {
                config = mapper.readValue(file, Configuration.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return config;
        }

        public Configuration setSSL(boolean SSL) {
            configuration.ssl = SSL;
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
