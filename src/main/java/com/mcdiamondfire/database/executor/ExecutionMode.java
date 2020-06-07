package com.mcdiamondfire.database.executor;


import com.mcdiamondfire.database.Callback;
import com.mcdiamondfire.database.executor.impl.AsyncExecutor;
import com.mcdiamondfire.database.executor.impl.SyncExecutor;
import com.mcdiamondfire.database.query.RunnableQuery;

import java.util.function.Consumer;

/**
 * Represents an execution mode for a query. This enum can be used
 * to determine how a query runs at runtime while maintaining clean
 * code.
 */
// TODO: Figure out a better way to do this without reflection
@SuppressWarnings("unused")
public enum ExecutionMode {

    /**
     * Synchronous execution mode.
     */
    SYNC(SyncExecutor.class),

    /**
     * Asynchronous execution mode.
     */
    ASYNC(AsyncExecutor.class);

    private final Class<? extends QueryExecutor> executorClass;

    ExecutionMode(Class<? extends QueryExecutor> executorClass) {
        this.executorClass = executorClass;
    }

    /**
     * Runs this execution mode by constructing a new instance of the
     * provided {@code QueryExecutor} class.
     *
     * @param query The query to be executed
     * @param callback The callback for completion
     * @param failed The callback for errors
     */
    public void run(RunnableQuery query, Callback callback, Consumer<Throwable> failed) {
        try {
            QueryExecutor executor = executorClass.getConstructor(RunnableQuery.class).newInstance(query);

            executor.start();
            executor.handleCallback(callback, failed);
        } catch(Throwable exception) {
            failed.accept(exception);
        }
    }
}
