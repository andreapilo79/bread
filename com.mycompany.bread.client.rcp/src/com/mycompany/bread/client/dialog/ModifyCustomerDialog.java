package com.mycompany.bread.client.dialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.mycompany.bread.client.service.ServiceLocator;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;

public class ModifyCustomerDialog extends AbstractCustomerDialog
{
    private Customer customerToModify;

    public ModifyCustomerDialog(Shell parentShell, Customer customerToModify)
    {
        super(parentShell);
        this.customerToModify = customerToModify;
    }

    @Override
    public void create()
    {
        super.create();
        setTitle("Modify customer");
        setMessage("Enter customer details", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        Control createDialogArea = super.createDialogArea(parent);

        firstNameText.setText(customerToModify.getFirstname());
        lastNameText.setText(customerToModify.getLastname());
        GregorianCalendar dateOfBirth = customerToModify.getDateOfBirth();
        dateOfBirthControl.setDate(
            dateOfBirth.get(Calendar.YEAR),
            dateOfBirth.get(Calendar.MONTH),
            dateOfBirth.get(Calendar.DAY_OF_MONTH));

        return createDialogArea;
    }

    @Override
    protected void okPressed()
    {
        Customer customer = getCustomerFromFields();
        customer.setId(customerToModify.getId());
        CustomerDBService customerService = ServiceLocator.getService(CustomerDBService.class);
        customerService.modifyCustomer(customer);
        super.okPressed();
    }
}
