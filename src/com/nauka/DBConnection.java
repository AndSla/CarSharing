package com.nauka;

import java.sql.*;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./db/";
    private static final String USER = "u";
    private static final String PASS = "p";
    private Connection connection;

    public DBConnection(String[] args) {
        String dbName = getDbNameFromCmdLine(args);

        if (dbName.equals("error")) {
            System.out.println("Wrong arguments! Use -databaseFileName [db_file_name]");
        } else {

            String fullUrl = DB_URL + dbName;
            String companyTableName = "company";
            String carTableName = "car";

            try {
                Class.forName(JDBC_DRIVER);
                try {
                    Connection connection = DriverManager.getConnection(fullUrl);
                    Statement statement = connection.createStatement();
                    connection.setAutoCommit(true);

                    if (tableNotExist(connection, companyTableName)) {
                        String sql = "CREATE TABLE " + companyTableName +
                                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                                "name VARCHAR(255) UNIQUE NOT NULL, " +
                                "PRIMARY KEY (id))";
                        statement.executeUpdate(sql);
                    }

                    if (tableNotExist(connection, carTableName)) {
                        String sql = "CREATE TABLE " + carTableName +
                                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                                "name VARCHAR(255) UNIQUE NOT NULL, " +
                                "company_id INTEGER NOT NULL, " +
                                "PRIMARY KEY (id), " +
                                "CONSTRAINT fk_company FOREIGN KEY (company_id) " +
                                "REFERENCES company(id))";
                        statement.executeUpdate(sql);
                    }

                    this.connection = connection;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean tableNotExist(Connection connection, String tableName) throws SQLException {
        tableName = tableName.toUpperCase(); //implementation of getTables method uses uppercase names
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, new String[]{"TABLE"});

        return !resultSet.next();

    }

    private String getDbNameFromCmdLine(String[] args) {
        String cmdLineArgs;
        String arg1Name = "-databaseFileName";

        if (args.length == 2) {
            cmdLineArgs = args[0] + " " + args[1];
            if (cmdLineArgs.matches(arg1Name + "\\s+\\w+\\s*")) {
                return args[1];
            }
        }

        return "error";

    }

    public Connection getConnection() {
        return connection;
    }

}
