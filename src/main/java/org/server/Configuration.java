package org.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    // database variables
    private String jdbcUrl;
    private String username;
    private String password;

    // server variables
    private int port;

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

    public static Configuration defaultValues() {
        Configuration configuration = new Configuration();
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

            try {
                return mapper.readValue(file, Configuration.class);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Unable to load configuration file with path of " + path, e);
            }

            return null;
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
