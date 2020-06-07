package com.mcdiamondfire.database;

import com.mcdiamondfire.database.result.SQLResult;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Callback {

    void call(@NotNull SQLResult result) throws Exception;
}
