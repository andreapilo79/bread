package com.mycompany.bread.client.toolitem;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.mycompany.bread.client.view.CustomerPart;

public class Refresh
{
    @Execute
    public void execute(EPartService partService)
    {
        MPart part = partService.findPart(CustomerPart.ID);
        CustomerPart dataPart = (CustomerPart) part.getObject();
        dataPart.refresh();
    }
}