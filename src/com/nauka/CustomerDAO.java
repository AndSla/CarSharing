package com.nauka;

import java.util.List;

public interface CustomerDAO {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

}
