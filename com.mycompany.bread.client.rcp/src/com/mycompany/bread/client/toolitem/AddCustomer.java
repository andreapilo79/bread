package com.mycompany.bread.client.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.mycompany.bread.client.dialog.AddCustomerDialog;

public class AddCustomer
{
    @Execute
    public void execute()
    {
        Dialog dialog = new AddCustomerDialog(Display.getDefault().getActiveShell());
        dialog.open();
    }
}
