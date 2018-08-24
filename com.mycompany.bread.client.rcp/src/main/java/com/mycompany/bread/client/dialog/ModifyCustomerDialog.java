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
package com.mycompany.bread.client.dialog;

import java.util.Calendar;

import javax.inject.Inject;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.customer.CustomerService;

public class ModifyCustomerDialog extends AbstractCustomerDialog
{
    private Customer customerToModify;

    @Inject
    private CustomerService customerService;

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
        Calendar dateOfBirth = customerToModify.getDateOfBirth();
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
        customerService.update(customer);
        super.okPressed();
    }
}
