package com.mcdiamondfire.database.parameter;

public class QueryParameter {

    private final int index;
    private final Object value;

    protected QueryParameter(int index, Object value) {
        this.index = index;
        this.value = value;
    }

    protected int getIndex() {
        return index;
    }

    protected Object getValue() {
        return value;
    }
}
