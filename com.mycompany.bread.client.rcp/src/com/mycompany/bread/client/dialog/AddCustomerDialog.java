package com.mycompany.bread.client.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

import com.mycompany.bread.client.service.ServiceLocator;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;

public class AddCustomerDialog extends AbstractCustomerDialog
{

    public AddCustomerDialog(Shell parentShell)
    {
        super(parentShell);
    }

    @Override
    public void create()
    {
        super.create();
        setTitle("Add customer");
        setMessage("Enter customer details", IMessageProvider.INFORMATION);
    }

    @Override
    protected void okPressed()
    {
        Customer customer = getCustomerFromFields();
        CustomerDBService customerService = ServiceLocator.getService(CustomerDBService.class);
        customerService.addCustomer(customer);
        super.okPressed();
    }
}
