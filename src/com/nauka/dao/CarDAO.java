package com.nauka.dao;

import java.util.List;

public interface CarDAO {

    void addCar(Car car);

    List<Car> getAllCompanyCars(int companyId);

    Car getCarById(int id);

    Car getRentedCar(Integer id);

}
