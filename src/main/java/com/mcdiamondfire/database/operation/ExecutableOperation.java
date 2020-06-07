package com.mcdiamondfire.database.operation;

import com.mcdiamondfire.database.executor.QueryExecutor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class ExecutableOperation {

    protected abstract void execute(QueryExecutor executor, PreparedStatement statement) throws SQLException;

    protected void executeBatch(QueryExecutor executor, List<PreparedStatement> statements) throws SQLException {
        throw new UnsupportedOperationException("This operation does not support batch queries.");
    }
}
