/**
 * Copyright © 2018 A.P. (email@email.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.bread.client.view;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.mycompany.bread.dao.CustomerDAO;
import com.mycompany.bread.domain.customer.Customer;

/**
 * Content providers for structured viewers
 * @author ap
 */
public class CustomersContentProvider implements IStructuredContentProvider
{

    private CustomerDAO customerDAO;

    public CustomersContentProvider(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }

    @Override
    public Object[] getElements(Object arg0)
    {
        List<Customer> customers = customerDAO.getAll();
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

    public void setCustomerDAO(CustomerDAO customerDAO)
    {
        this.customerDAO = customerDAO;
    }
}
