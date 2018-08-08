package com.mycompany.bread.service.dao;

import java.util.List;

import com.mycompany.bread.domain.customer.Customer;

public interface CustomerDBService
{
    void addCustomer(Customer customer);

    void modifyCustomer(Customer customer);

    void removePersion(Customer customer);

    List<Customer> getCustomers();

    void registerCustomerObserver(ICustomerDBChangeObserver observer);

    void unregisterCustomerObserver(ICustomerDBChangeObserver observer);
}
