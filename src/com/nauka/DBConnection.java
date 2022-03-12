package com.nauka;

import java.sql.*;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./db/";
    private static final String USER = "u";
    private static final String PASS = "p";

    public DBConnection(String[] args) {
        String dbName = getDbNameFromCmdLine(args);
        String fullUrl = DB_URL + dbName;
        String tableName = "company";

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(fullUrl);
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(true);

                if (!tableExists(connection, tableName)) {
                    String sql = "CREATE TABLE " + tableName +
                            "(id INTEGER NOT NULL, " +
                            "name VARCHAR(255), " +
                            "PRIMARY KEY (id))";
                    statement.executeUpdate(sql);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        tableName = tableName.toUpperCase();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"})) {
            while (resultSet.next()) {
                if (resultSet.getString("TABLE_NAME").equals(tableName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    private String getDbNameFromCmdLine(String[] args) {
        String cmdLineArgs = args.length == 2 ? args[0] + " " + args[1] : "error";
        String arg1Name = "-databaseFileName";

        if (cmdLineArgs.matches(arg1Name + "\\s+\\w+\\s*")) {
            return args[1];
        }

        return "-1";

    }

}
