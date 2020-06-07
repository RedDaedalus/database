package com.mcdiamondfire.database.result.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.result.SQLResult;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class SQLResultImpl implements SQLResult {

    private final QueryExecutor executor;
    private final Statement statement;

    protected SQLResultImpl(@NotNull QueryExecutor executor, @NotNull Statement statement) {
        this.executor = executor;
        this.statement = statement;
    }

    @NotNull
    @Override
    public final QueryExecutor getExecutor() {
        return executor;
    }

    @NotNull
    @Override
    public final Statement getStatement() {
        return statement;
    }

    @Override
    public @NotNull ResultSet getResultSet(int index) {
        throw new UnsupportedOperationException("This SQL result does not contain any result sets");
    }

    @Override
    public int getUpdateCount() {
        throw new UnsupportedOperationException("This SQL result does not contain an update count");
    }

    @Override
    public void close() throws SQLException {
        statement.close();
    }
}
