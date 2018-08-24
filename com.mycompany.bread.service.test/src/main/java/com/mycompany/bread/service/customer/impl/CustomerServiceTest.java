package com.mycompany.bread.service.customer.impl;

import java.util.GregorianCalendar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.customer.CustomerService;

@Test public class CustomerServiceTest
{

    private CustomerService customerService;

    @BeforeTest
    public void setup()
    {
        customerService = new CustomerServiceImpl();
    }

    @AfterTest
    public void endSetup()
    {
    }

    public void test()
    {
        Assert.assertNotNull(customerService);
        Assert.assertNotNull(customerService.getAll());
        int size = customerService.getAll().size();
        Assert.assertEquals(size, 0);
        customerService.save(new Customer("a", "b", new GregorianCalendar(2018, 01, 22, 00, 00)));
        Assert.assertEquals(customerService.getAll().size(), 1);

    }
}
