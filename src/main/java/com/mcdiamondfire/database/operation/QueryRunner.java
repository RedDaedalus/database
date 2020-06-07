package com.mcdiamondfire.database.operation;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.query.RunnableQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class QueryRunner {

    public static void execute(ExecutableOperation operation, QueryExecutor executor) throws SQLException {
        RunnableQuery query = executor.getQuery();
        String queryString = query.getQueryStrings()[0];

        System.out.println(Arrays.toString(query.getQueryStrings()));

        Connection conn = query.getProvider().getConnection();
        PreparedStatement statement = conn.prepareStatement(queryString);

        query.getParameterSet().applyTo(statement);

        operation.execute(executor, statement);
    }

    public static void executeBatch(ExecutableOperation operation, QueryExecutor executor) throws SQLException {
        // TODO: Finish
    }
}
