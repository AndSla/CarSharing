package com.nauka;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final Connection dbConnection;

    public CustomerDAOImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "INSERT INTO customer (name) " +
                    "VALUES ('" + customer.getName() + "');";
            statement.execute(sql);
            System.out.println("The customer was added!" + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM customer;";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(result.getString("id")));
                customer.setName(result.getString("name"));

                Integer rentedCarId;
                if (result.getString("rented_car_id") != null) {
                    rentedCarId = Integer.parseInt(result.getString("rented_car_id"));
                } else {
                    rentedCarId = null;
                }

                customer.setRentedCarId(rentedCarId);
                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM customer WHERE id=" + id + ";";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(Integer.parseInt(result.getString("id")));
                customer.setName(result.getString("name"));

                Integer rentedCarId;
                if (result.getString("rented_car_id") != null) {
                    rentedCarId = Integer.parseInt(result.getString("rented_car_id"));
                } else {
                    rentedCarId = null;
                }

                customer.setRentedCarId(rentedCarId);
                return customer;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
