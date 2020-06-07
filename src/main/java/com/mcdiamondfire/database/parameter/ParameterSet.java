package com.mcdiamondfire.database.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParameterSet {

    private final List<QueryParameter> parameters = new ArrayList<>();
    private int index = 0;

    public void add(Object value) {
        parameters.add(new QueryParameter(++index, value));
    }

    public void applyTo(PreparedStatement statement) throws SQLException {
        for (QueryParameter parameter : parameters) {
            statement.setObject(parameter.getIndex(), parameter.getValue());
        }
    }
}
