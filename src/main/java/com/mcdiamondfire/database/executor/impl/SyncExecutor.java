package com.mcdiamondfire.database.executor.impl;

import com.mcdiamondfire.database.Callback;
import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.QueryRunner;
import com.mcdiamondfire.database.operation.SQLOperation;
import com.mcdiamondfire.database.query.RunnableQuery;
import com.mcdiamondfire.database.result.SQLResult;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Represents an executor intended to run queries on
 * the current thread. This can lead to freezing for
 * up to <em>10 seconds</em>, so be careful.
 */
public final class SyncExecutor extends QueryExecutor {

    private SQLResult result;

    public SyncExecutor(RunnableQuery query) {
        super(query);
    }

    @Override
    public void start() throws SQLException {
        RunnableQuery query = getQuery();
        SQLOperation operation = query.getOperation();

        QueryRunner.execute(operation.getExecutable(), this);
    }

    @Override
    public void completed(SQLResult result) {
        this.result = result;
    }

    @Override
    public void handleCallback(Callback done, Consumer<Throwable> failed) {
        try (SQLResult result = getResult()) {
            done.call(result);
        } catch(Throwable exception) {
            failed.accept(exception);
        }
    }

    /**
     * Gets the query result for this executor. This will
     * be null until the query has been completed.
     *
     * @return The result of the executed query
     */
    public SQLResult getResult() {
        return result;
    }
}
