package com.mycompany.bread.client.toolitem;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.mycompany.bread.client.dialog.ModifyCustomerDialog;
import com.mycompany.bread.client.view.CustomerPart;
import com.mycompany.bread.domain.customer.Customer;

public class ModifyCustomer
{
    @Inject
    private ESelectionService selectionService;

    @Execute
    public void execute()
    {
        Customer selectedCustomer = (Customer) selectionService.getSelection(CustomerPart.ID);
        if (selectedCustomer != null)
        {
            Dialog dialog = new ModifyCustomerDialog(Display.getDefault().getActiveShell(), selectedCustomer);
            dialog.open();
        }
    }

}
