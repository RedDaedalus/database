package com.mcdiamondfire.database.result.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;

public final class UpdateResult extends SQLResultImpl {

    private final int updateCount;

    public UpdateResult(@NotNull QueryExecutor executor, Statement statement) throws SQLException {
        super(executor, statement);

        this.updateCount = statement.getUpdateCount();
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }
}
