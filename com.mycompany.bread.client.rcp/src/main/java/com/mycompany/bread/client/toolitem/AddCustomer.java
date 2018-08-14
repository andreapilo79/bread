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
package com.mycompany.bread.client.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.mycompany.bread.client.dialog.AddCustomerDialog;
import com.mycompany.bread.dao.CustomerDAO;

/**
 * Action: Open a dialog to add a customer in database
 * @author ap
 */
public class AddCustomer
{
    @Inject
    private CustomerDAO customerDAO;

    @Execute
    public void execute()
    {
        AddCustomerDialog dialog = new AddCustomerDialog(Display.getDefault().getActiveShell());
        if (dialog.open() == Window.OK)
        {
            customerDAO.save(dialog.getCurrentCustomer());
        }
        dialog.open();
    }
}
