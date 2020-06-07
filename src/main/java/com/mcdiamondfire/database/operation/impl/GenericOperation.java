package com.mcdiamondfire.database.operation.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.ExecutableOperation;
import com.mcdiamondfire.database.result.impl.GenericResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class GenericOperation extends ExecutableOperation {

    @Override
    protected void execute(QueryExecutor executor, PreparedStatement statement) throws SQLException {
        statement.execute();

        executor.completed(new GenericResult(executor, statement));
    }
}
