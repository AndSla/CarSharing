package com.nauka.dao;

import java.util.List;

public interface CustomerDAO {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void rentACar(Customer customer, Car car);

    boolean hasRentedCar(Customer customer);

    void returnCar(Customer customer);

}
