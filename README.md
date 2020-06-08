# database
The DiamondFire SQL database module. 

## Introduction
This repository i not meant to be used as a library, more as code that can just be dropped into an existing project. It does not by any means provide full coverage of the Java SQL driver, and misses some pretty significant features such as transactions. It is simply the internal module used in DiamondFire, so any missing features do not exist because they are not needed.

## Usage
Query methods are accessible through the ``Database`` class. They can be executed synchronously or asynchronously depending on what is best suited to the context.

### Query Construction
This is the basic construction for every runnable query. Note that this is the construction of the ``QueryBuilder``, which contains "shortcut" methods for execution which build the ``RunnableQuery`` instance.

```java
Database.query("UPDATE accounts SET ip = ? WHERE name LIKE ?;").
	with("127.0.0.1", "RedDaedalus").      // Specifies the 1st parameter as an IP, and the second as "RedDaedalus"
	operation(SQLOperation.UPDATE).        // States that this is an update operation
	connection(source.getConnection());    // Provides a connection from "source"
```

### Async Execution
Executes the query on its own thread.
```java
// Success callback
queryBuilder.execute().then(result -> {
	if (result.getUpdateCount() == 0) System.err.println("Update failed without an error.");
	else System.out.println("IP address updated.");
// Failure callback
}).caught(Throwable::printStackTrace);
```

### Sync Execution
Executes the query on the current thread. It is imperative that ``SQLResult#close`` is called if the connection is to be terminated.
```java
try (SQLResult result = queryBuilder.executeSync()) {
	if (result.getUpdateCount() == 0) System.err.println("Update failed without an error.");
	else System.out.println("IP address updated.");
} catch(SQLException exception) {
	exception.printStackTrace();
}
```

## Runtime Mode Specification
If required, it is possible to specify which form of execution is desired easily at runtime using the execute method.
```java
queryBuilder.build().execute(ExecutionMode.SYNC, result -> {
	if (result.getUpdateCount() == 0) System.err.println("Update failed without an error.");
	else System.out.println("IP address updated.");
}, Throwable::printStackTrace);
