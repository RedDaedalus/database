package com.mcdiamondfire.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionProvider {

    private final BasicDataSource source;
    private final Connection connection;

    private Connection createdConnection = null;

    private ConnectionProvider(BasicDataSource source, Connection connection) {
        this.source = source;
        this.connection = connection;
    }

    public ConnectionProvider(@NotNull BasicDataSource source) {
        this(source, null);
    }

    public ConnectionProvider(@NotNull Connection connection) {
        this(null, connection);
    }

    public Connection getConnection() throws SQLException {
        if (createdConnection != null) return createdConnection;

        Connection connection = this.connection == null ? source.getConnection() : this.connection;
        createdConnection = connection;

        return connection;
    }
}
