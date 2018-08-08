package com.mycompany.bread.client.view;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.mycompany.bread.client.service.ServiceLocator;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;

public class CustomersContentProvider implements IStructuredContentProvider
{
    @Override
    public Object[] getElements(Object arg0)
    {
        CustomerDBService customerService = ServiceLocator.getService(CustomerDBService.class);
        List<Customer> customers = customerService.getCustomers();

        Customer[] customerArray = new Customer[customers.size()];
        customerArray = customers.toArray(customerArray);
        return customerArray;

    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2)
    {
        // do nothing
    }

    @Override
    public void dispose()
    {
        // do nothing
    }
}
