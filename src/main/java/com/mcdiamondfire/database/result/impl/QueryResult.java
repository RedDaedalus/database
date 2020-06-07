package com.mcdiamondfire.database.result.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("unused")
public final class QueryResult extends SQLResultImpl {
    private final ResultSet[] resultSets;

    public QueryResult(@NotNull QueryExecutor executor, @NotNull ResultSet[] resultSets) throws SQLException {
        super(executor, resultSets[0].getStatement());

        this.resultSets = resultSets;
    }

    @Override
    public @NotNull ResultSet getResultSet(int index) {
        return resultSets[index];
    }

    @Override
    public void close() throws SQLException {
        Statement statement = getStatement();

        statement.getConnection().close();
        statement.close();

        for (ResultSet resultSet : resultSets) {
            resultSet.close();
        }
    }
}
