package com.mcdiamondfire.database.result.impl;

import com.mcdiamondfire.database.executor.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.sql.Statement;

public final class GenericResult extends SQLResultImpl {

    public GenericResult(@NotNull QueryExecutor executor, @NotNull Statement statement) {
        super(executor, statement);
    }
}
