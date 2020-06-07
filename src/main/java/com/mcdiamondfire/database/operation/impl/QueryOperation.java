package com.mcdiamondfire.database.operation.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.ExecutableOperation;
import com.mcdiamondfire.database.result.impl.QueryResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class QueryOperation extends ExecutableOperation {

    @Override
    protected void execute(QueryExecutor executor, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        QueryResult result = new QueryResult(executor, new ResultSet[] { resultSet });

        executor.completed(result);
    }

    @Override
    protected void executeBatch(QueryExecutor executor, List<PreparedStatement> statements) throws SQLException {
        // TODO: finish
    }
}
