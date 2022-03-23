package com.nauka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private final Connection dbConnection;

    public CarDAOImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addCar(Car car) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "INSERT INTO car (name, company_id) " +
                    "VALUES ('" + car.getName() + "', " + car.getCompanyId() + ");";
            statement.execute(sql);
            System.out.println("The car was added!" + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getAllCompanyCars(int companyId) {
        List<Car> cars = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM car WHERE company_id=" + companyId + ";";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                Car car = new Car();
                car.setId(Integer.parseInt(result.getString("id")));
                car.setName(result.getString("name"));
                car.setCompanyId(Integer.parseInt(result.getString("company_id")));
                car.setRented(Boolean.parseBoolean(result.getString("is_rented")));
                cars.add(car);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public Car getCarById(int id) {
        try (Statement statement = dbConnection.createStatement()) {
            String sql = "SELECT * FROM car WHERE id=" + id + ";";
            statement.execute(sql);
            ResultSet result = statement.getResultSet();

            if (result.next()) {
                Car car = new Car();
                car.setId(Integer.parseInt(result.getString("id")));
                car.setName(result.getString("name"));
                car.setCompanyId(Integer.parseInt(result.getString("company_id")));
                car.setRented(Boolean.parseBoolean(result.getString("is_rented")));
                System.out.println("You rented '" + car.getName() + "'\n");
                return car;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
