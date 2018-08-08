package com.mycompany.bread.client.toolitem;

import java.text.MessageFormat;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.mycompany.bread.client.service.ServiceLocator;
import com.mycompany.bread.client.view.CustomerPart;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;

public class RemoveCustomer
{
    @Inject
    private ESelectionService selectionService;

    @Execute
    public void execute()
    {
        Customer selectedCustomer = (Customer) selectionService.getSelection(CustomerPart.ID);
        if (selectedCustomer != null)
        {
            String msg = MessageFormat.format(
                "Do you want to delete: ''{0}, {1}''",
                selectedCustomer.getFirstname(),
                selectedCustomer.getLastname());
            boolean confirmed = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "Confirm delete", msg);
            if (confirmed)
            {
                ServiceLocator.getService(CustomerDBService.class).removePersion(selectedCustomer);
            }
        }
    }

}