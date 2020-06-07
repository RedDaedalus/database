package com.mcdiamondfire.database.executor;

import com.mcdiamondfire.database.Callback;
import com.mcdiamondfire.database.query.RunnableQuery;
import com.mcdiamondfire.database.result.SQLResult;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * This class is inherited to form different methods
 * for execution for queries. Used to abstract query
 * and operation management.
 */
public abstract class QueryExecutor {

    private final RunnableQuery query;

    /**
     * Create a new query executor with the given {@code RunnableQuery}
     * to be executed. The query will not be automatically executed, so
     * the {@code start} method must be employed.
     *
     * @param query The query to eventually execute
     */
    public QueryExecutor(RunnableQuery query) {
        this.query = query;
    }

    /**
     * Gets the query object for this executor.
     *
     * @return The un-cloned query instance
     */
    public final RunnableQuery getQuery() {
        return query;
    }
    
    /**
     * Starts this query's execution. Should only be called once
     * to avoid any issues with value reassignment in queries or
     * other execution related issues.
     *
     * @throws SQLException When the execution fails due to an
     * SQL related issue.
     */
    public abstract void start() throws SQLException;

    /**
     * Handles the completion of the query's execution. After
     * this method is called, the given query result should be
     * closed at some point to prevent issues within MySQL.
     *
     * @param result The constructed query result
     */
    public abstract void completed(SQLResult result);

    public abstract void handleCallback(Callback callback, Consumer<Throwable> failed);
}
