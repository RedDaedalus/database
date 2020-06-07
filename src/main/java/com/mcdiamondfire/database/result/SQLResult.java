package com.mcdiamondfire.database.result;

import com.mcdiamondfire.database.executor.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.Statement;

public interface SQLResult extends AutoCloseable {

    @NotNull
    QueryExecutor getExecutor();

    @NotNull
    Statement getStatement();

    @NotNull
    ResultSet getResultSet(int index);

    @NotNull
    default ResultSet getResultSet() {
        return getResultSet(0);
    }

    int getUpdateCount();
}
