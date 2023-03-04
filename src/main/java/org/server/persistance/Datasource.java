package org.server.persistance;

import com.zaxxer.hikari.HikariDataSource;
import org.server.ServerLauncher;

import java.sql.Connection;
import java.sql.SQLException;

public class Datasource {

    public static final HikariDataSource ds = new HikariDataSource();

    static  {
        assert ServerLauncher.configuration != null;
        ds.setJdbcUrl(ServerLauncher.configuration.getJdbcUrl());
        ds.setUsername(ServerLauncher.configuration.getUsername());
        ds.setPassword(ServerLauncher.configuration.getPassword());
        ds.addDataSourceProperty("cachePrepStmts", "true");
        ds.addDataSourceProperty("prepStmtCacheSize", "250");
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
