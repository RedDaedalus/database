package com.mcdiamondfire.database;

import com.mcdiamondfire.database.executor.QueryExecutor;
import com.mcdiamondfire.database.query.QueryBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * This class is a utility class for interfacing with the
 * SQL database. Shortcuts for certain queries can be put
 * here, and there is also a method to form a builder for
 * raw query execution.
 *
 * @see QueryBuilder
 * @see QueryExecutor
 */
public final class Database {

    /**
     * Creates a buildable query object used for forming and executing
     * queries in a clean way. When passing multiple queries, they can
     * all be executed as a batch.
     *
     * @param query The SQL operation to execute
     * @param other Additional optional queries to run
     *              alongside the query.
     * @return A {@code QueryBuilder} constructed with
     * the provided query strings.
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static QueryBuilder query(@NotNull String query, @NotNull String... other) {
        return new QueryBuilder(query, other);
    }

    private Database() {
    }
}
