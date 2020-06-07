package com.mcdiamondfire.database.query;

import com.mcdiamondfire.database.Callback;
import com.mcdiamondfire.database.ConnectionProvider;
import com.mcdiamondfire.database.executor.ExecutionMode;
import com.mcdiamondfire.database.executor.impl.AsyncExecutor;
import com.mcdiamondfire.database.executor.impl.SyncExecutor;
import com.mcdiamondfire.database.operation.SQLOperation;
import com.mcdiamondfire.database.parameter.ParameterSet;
import com.mcdiamondfire.database.result.SQLResult;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class RunnableQuery {

    private final String[] queryStrings;
    private final ParameterSet parameterSet;
    private final SQLOperation operation;
    private final ConnectionProvider provider;

    // Called in the QueryBuilder
    protected RunnableQuery(@NotNull String[] queryStrings, @NotNull ParameterSet parameterSet,
                            @NotNull SQLOperation operation, @NotNull ConnectionProvider provider) {
        this.queryStrings = queryStrings;
        this.parameterSet = parameterSet;
        this.operation = operation;
        this.provider = provider;
    }

    @NotNull
    public String[] getQueryStrings() {
        return queryStrings;
    }

    @NotNull
    public ParameterSet getParameterSet() {
        return parameterSet;
    }

    @NotNull
    public SQLOperation getOperation() {
        return operation;
    }

    @NotNull
    public ConnectionProvider getProvider() {
        return provider;
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public AsyncExecutor execute() {
        AsyncExecutor executor = new AsyncExecutor(this);
        executor.start();

        return executor;
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public SQLResult executeSync() throws SQLException {
        SyncExecutor executor = new SyncExecutor(this);
        executor.start();

        return executor.getResult();
    }

    public void execute(@NotNull ExecutionMode mode, @NotNull Callback callback,
                        @NotNull Consumer<Throwable> failed) {
        mode.run(this, callback, failed);
    }
}
