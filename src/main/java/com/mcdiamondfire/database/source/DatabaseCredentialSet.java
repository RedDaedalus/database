package com.mcdiamondfire.database.source;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseCredentialSet {

    private final String database;
    private final String host;
    private final String username;
    private final String password;

    public DatabaseCredentialSet(String database, String host, String username, String password) {
        this.database = database;
        this.host = host;
        this.username = username;
        this.password = password;

    }

    public BasicDataSource constructSource() {
        BasicDataSource source = new BasicDataSource();

        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://" + host + "/" + database);
        source.setConnectionProperties("allowMultiQueries=true");
        source.setUsername(username);
        source.setPassword(password);
        source.setInitialSize(10);
        source.setMaxTotal(20);

        return source;
    }
}
