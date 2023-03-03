package org.server.database;

import com.zaxxer.hikari.HikariDataSource;
import org.server.Server;

import java.sql.Connection;
import java.sql.SQLException;

public class Datasource {

    public static final HikariDataSource ds = new HikariDataSource();

    static  {
        ds.setJdbcUrl(Server.configuration.getJdbcUrl());
        ds.setUsername(Server.configuration.getUsername());
        ds.setPassword(Server.configuration.getPassword());
        ds.addDataSourceProperty("cachePrepStmts", "true");
        ds.addDataSourceProperty("prepStmtCacheSize", "250");
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
