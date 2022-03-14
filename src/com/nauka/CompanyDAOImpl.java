package com.nauka;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    private final Connection connection;

    public CompanyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public List<Company> getAllCompanies() {

        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM company;";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
