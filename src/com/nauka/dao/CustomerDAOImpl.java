package com.nauka.dao;

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

    @Override
    public void rentACar(Customer customer, Car car) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "UPDATE customer " +
                    "SET rented_car_id=" + car.getId() + " " +
                    "WHERE id=" + customer.getId() + ";";
            statement.execute(sql);
            sql = "UPDATE car " +
                    "SET is_rented=TRUE " +
                    "WHERE id=" + car.getId() + ";";
            statement.execute(sql);
            System.out.println("You rented '" + car.getName() + "'" + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnCar(Customer customer) {
        int rentedCarId;
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT rented_car_id " +
                    "FROM customer " +
                    "WHERE id=" + customer.getId();
            statement.execute(sql);
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                rentedCarId = Integer.parseInt(result.getString("rented_car_id"));
            } else {
                System.out.println("You didn't rent a car!");
                return;
            }

            sql = "UPDATE customer " +
                    "SET rented_car_id=NULL " +
                    "WHERE id=" + customer.getId() + ";";
            statement.execute(sql);

            sql = "UPDATE car " +
                    "SET is_rented=FALSE " +
                    "WHERE id=" + rentedCarId + ";";
            statement.execute(sql);
            System.out.println("You've returned a rented car!" + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasRentedCar(Customer customer) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT rented_car_id FROM customer " +
                    "WHERE id=" + customer.getId() + ";";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            if (result.next()) {
                if (result.getString("rented_car_id") != null) {
                    System.out.println("You've already rented a car!" + "\n");
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
