package com.mcdiamondfire.database.operation;

import com.mcdiamondfire.database.operation.impl.GenericOperation;
import com.mcdiamondfire.database.operation.impl.LargeUpdateOperation;
import com.mcdiamondfire.database.operation.impl.QueryOperation;
import com.mcdiamondfire.database.operation.impl.UpdateOperation;
import com.mcdiamondfire.database.query.QueryBuilder;
import com.mcdiamondfire.database.result.SQLResult;

/**
 * Denotes an operation that can be passed to an instance of a
 * {@link QueryBuilder}. This
 * enum is not responsible for execution, just for storage.
 *
 * @see SQLResult
 * @see ExecutableOperation
 */
@SuppressWarnings("unused")
public enum SQLOperation {

    /**
     *
     */
    // TODO redo docs
    GENERIC(new GenericOperation()),

    /**
     * Queries a set of values from the database. The fetched results
     * will be provided with the generated {@code QueryResult}. Given
     * results are closed by the query result itself.
     */
    QUERY(new QueryOperation()),

    /**
     * Performs an update to the database. The number of updated
     * rows is passed to the created {@code QueryResult}.
     */
    UPDATE(new UpdateOperation()),

    /**
     * Performs a large scale update to the database. The number
     * of generated rows is passed to the generated result data.
     */
    UPDATE_LARGE(new LargeUpdateOperation());

    private final ExecutableOperation operation;

    SQLOperation(ExecutableOperation operation) {
        this.operation = operation;
    }

    public ExecutableOperation getExecutable() {
        return operation;
    }
}
