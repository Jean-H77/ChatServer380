package org.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Configuration {

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

    public static Configuration load(String path) {
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
}
