package com.mcdiamondfire.database.executor.impl;

import com.mcdiamondfire.database.Callback;
import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.operation.QueryRunner;
import com.mcdiamondfire.database.operation.SQLOperation;
import com.mcdiamondfire.database.query.RunnableQuery;
import com.mcdiamondfire.database.result.SQLResult;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Responsible for the asynchronous execution of {@code RunnableQueries}.
 * They are executed on a cached thread pool, which will then pass result
 * data back to this executor.
 *
 * @see AsyncExecutor#then(com.mcdiamondfire.database.Callback)
 * @see AsyncExecutor#caught(Consumer)
 */
public final class AsyncExecutor extends QueryExecutor {

    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    private Callback success = res -> {};
    private Consumer<Throwable> fail = Throwable::printStackTrace;

    public AsyncExecutor(RunnableQuery query) {
        super(query);
    }

    @Override
    public void start() {
        THREAD_POOL.submit(() -> {
            SQLOperation operation = getQuery().getOperation();

            try {
                QueryRunner.execute(operation.getExecutable(), this);
            } catch(SQLException exception) {
                fail.accept(exception);
            }
        });
    }

    @Override
    public void completed(SQLResult result) {
        try {
            success.call(result);
            result.close();
        } catch (Throwable exception) {
            fail.accept(exception);
        }
    }

    @Override
    public void handleCallback(Callback callback, Consumer<Throwable> failed) {
        then(callback).caught(failed);
    }

    /**
     * Handles the result of the executed query. The given
     * consumer is called when the operation has completed.
     *
     * @param then The consumer to call when queries are
     *             done
     * @return This executor
     */
    @Contract("_ -> this")
    public AsyncExecutor then(@NotNull Callback then) {
        success = then;
        return this;
    }

    /**
     * Handles any exceptions thrown, either in the
     * completion consumer, or in the execution of
     * the query itself.
     *
     * @param caught The consumer to call when an
     *               error is thrown.
     * @return This executor
     */
    @Contract("_ -> this")
    @SuppressWarnings("UnusedReturnValue")
    public AsyncExecutor caught(@NotNull Consumer<Throwable> caught) {
        fail = caught;
        return this;
    }
}
