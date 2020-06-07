package com.mcdiamondfire.database.query;

import com.mcdiamondfire.database.ConnectionProvider;
import com.mcdiamondfire.database.executor.impl.AsyncExecutor;
import com.mcdiamondfire.database.operation.SQLOperation;
import com.mcdiamondfire.database.parameter.ParameterSet;
import com.mcdiamondfire.database.result.SQLResult;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")
public final class QueryBuilder {

    private final String[] queryStrings;
    private final ParameterSet parameterSet = new ParameterSet();

    private SQLOperation operation = SQLOperation.QUERY;

    private ConnectionProvider provider = new ConnectionProvider(new BasicDataSource());

    public QueryBuilder(String queryString, String... other) {
        int length = other.length + 1;

        this.queryStrings = new String[length];
        this.queryStrings[0] = queryString;

        System.arraycopy(other, 0, this.queryStrings, 1, other.length);
    }

    @Contract("_, _ -> this")
    public QueryBuilder with(@Nullable Object parameter, Object... other) {
        parameterSet.add(parameter);
        for (Object param : other) {
            parameterSet.add(param);
        }

        return this;
    }

    @Contract("_ -> this")
    public QueryBuilder operation(@NotNull("Null value passed for QueryBuilder operation") SQLOperation operation) {
        this.operation = operation;
        return this;
    }

    @Contract("_ -> this")
    public QueryBuilder source(@NotNull BasicDataSource source) {
        provider = new ConnectionProvider(source);
        return this;
    }

    @Contract("_ -> this")
    public QueryBuilder connection(@NotNull Connection connection) {
        provider = new ConnectionProvider(connection);
        return this;
    }

    // Overwritten in transaction builders
    ConnectionProvider provider() {
        return provider;
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public RunnableQuery build() {
        return new RunnableQuery(queryStrings, parameterSet, operation, provider());
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public AsyncExecutor execute() {
        return build().execute();
    }

    public SQLResult executeSync() throws SQLException {
        return build().executeSync();
    }
}
