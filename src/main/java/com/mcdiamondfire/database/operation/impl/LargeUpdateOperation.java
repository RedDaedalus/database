package com.mcdiamondfire.database.operation.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.ExecutableOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LargeUpdateOperation extends ExecutableOperation {

    @Override
    protected void execute(QueryExecutor executor, PreparedStatement statement) throws SQLException {

    }

    @Override
    protected void executeBatch(QueryExecutor executor, List<PreparedStatement> statements) throws SQLException {

    }
}
