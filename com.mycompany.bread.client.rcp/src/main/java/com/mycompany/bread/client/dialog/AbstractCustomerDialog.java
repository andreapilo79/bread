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
import java.util.GregorianCalendar;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.mycompany.bread.domain.customer.Customer;

public abstract class AbstractCustomerDialog extends TitleAreaDialog
{
    protected Text firstNameText;

    protected Text lastNameText;

    protected DateTime dateOfBirthControl;

    public AbstractCustomerDialog(Shell parentShell)
    {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout(2, false);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(layout);

        createFirstName(container);
        createLastName(container);
        createDateOfBirth(container);

        return area;
    }

    private void createFirstName(Composite container)
    {
        Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("First Name");

        GridData dataFirstName = new GridData();
        dataFirstName.grabExcessHorizontalSpace = true;
        dataFirstName.horizontalAlignment = GridData.FILL;

        firstNameText = new Text(container, SWT.BORDER);
        firstNameText.setLayoutData(dataFirstName);
    }

    private void createLastName(Composite container)
    {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Last Name");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        lastNameText = new Text(container, SWT.BORDER);
        lastNameText.setLayoutData(dataLastName);
    }

    private void createDateOfBirth(Composite container)
    {
        Label lbdtDateOfBirth = new Label(container, SWT.NONE);
        lbdtDateOfBirth.setText("Date of birth");

        GridData dataDateOfBirth = new GridData();
        dataDateOfBirth.grabExcessHorizontalSpace = true;
        dataDateOfBirth.horizontalAlignment = GridData.FILL;
        dateOfBirthControl = new DateTime(container, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        dateOfBirthControl.setLayoutData(dataDateOfBirth);
        dateOfBirthControl.setDate(1950, Calendar.JANUARY, 1);
    }

    @Override
    protected boolean isResizable()
    {
        return true;
    }

    protected Customer getCustomerFromFields()
    {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        GregorianCalendar dateOfBirth = new GregorianCalendar(
            dateOfBirthControl.getYear(),
            dateOfBirthControl.getMonth(),
            dateOfBirthControl.getDay());

        return new Customer(firstName, lastName, dateOfBirth);
    }
}