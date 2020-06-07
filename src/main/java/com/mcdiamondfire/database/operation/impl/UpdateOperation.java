package com.mcdiamondfire.database.operation.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.ExecutableOperation;
import com.mcdiamondfire.database.result.impl.UpdateResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateOperation extends ExecutableOperation {

    @Override
    protected void execute(QueryExecutor executor, PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        UpdateResult result = new UpdateResult(executor, statement);

        executor.completed(result);
    }

    @Override
    protected void executeBatch(QueryExecutor executor, List<PreparedStatement> statements) throws SQLException {
//        int updateCount = 0;
//
//        for (PreparedStatement statement : statements) {
//            statement.executeUpdate();
//            updateCount += statement.getUpdateCount();
//        }
//
//        QueryResult result = new QueryResult(executor, updateCount);
//        executor.completed(result);
//
//        statements.get(0).getConnection().commit();
    }
}
