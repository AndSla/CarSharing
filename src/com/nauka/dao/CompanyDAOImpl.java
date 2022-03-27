package com.nauka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    private final Connection dbConnection;

    public CompanyDAOImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addCompany(Company company) {

        try (Statement statement = dbConnection.createStatement()) {
            String sql = "INSERT INTO company (name) VALUES ('" + company.getName() + "');";
            statement.execute(sql);
            System.out.println("The company was created!" + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Company> getAllCompanies() {

        List<Company> companies = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM company;";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                Company company = new Company();
                company.setId(Integer.parseInt(result.getString("id")));
                company.setName(result.getString("name"));
                companies.add(company);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return companies;

    }

    @Override
    public Company getCompanyById(int id) {

        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM company WHERE id=" + id + ";";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            if (result.next()) {
                Company company = new Company();
                company.setId(Integer.parseInt(result.getString("id")));
                company.setName(result.getString("name"));
                return company;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void showCompanyOfRentedCar(int id) {
        Company company = getCompanyById(id);
        System.out.println("Company:");
        System.out.println(company.getName() + "\n");
    }
}
