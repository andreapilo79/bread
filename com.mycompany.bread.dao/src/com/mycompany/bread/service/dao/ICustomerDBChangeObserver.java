package com.mycompany.bread.service.dao;

import com.mycompany.bread.domain.customer.Customer;

public interface ICustomerDBChangeObserver
{
    void changed(String eventID, Customer affectedCustomer);
}
