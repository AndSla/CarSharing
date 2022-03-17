package com.nauka;

import java.util.List;

public interface CarDAO {

    void addCar(Car car);

    List<Car> getAllCompanyCars(int companyId);

}
