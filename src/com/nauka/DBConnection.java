package com.nauka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./db/";
    private static final String USER = "u";
    private static final String PASS = "";

    public DBConnection(String[] args) {
        String dbName = getDbNameFromCmdLine(args);
        String fullUrl = DB_URL + dbName;

        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(fullUrl, USER, PASS);
                 Statement statement = connection.createStatement()) {
                connection.setAutoCommit(true);
                String sql = "CREATE TABLE COMPANY " +
                        "(ID INTEGER not NULL, " +
                        "NAME VARCHAR(255), " +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
