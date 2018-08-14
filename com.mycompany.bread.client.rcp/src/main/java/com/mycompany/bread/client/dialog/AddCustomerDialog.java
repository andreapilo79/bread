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

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Shell;

import com.mycompany.bread.domain.customer.Customer;

public class AddCustomerDialog extends AbstractCustomerDialog
{

    private Customer currentCustomer;

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
        currentCustomer = getCustomerFromFields();
        super.okPressed();
    }

    public Customer getCurrentCustomer()
    {
        return currentCustomer;
    }
}
