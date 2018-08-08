package com.mycompany.bread.client.view;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import com.mycompany.bread.domain.customer.Customer;

public class CustomerViewerComparator extends ViewerComparator
{
    private static final int DESCENDING = 1;

    private static final int ASCENDING = 0;

    private CustomerViewerSortableColumns propertyIndex;

    private int direction;

    public CustomerViewerComparator()
    {
        this.propertyIndex = CustomerViewerSortableColumns.id;
        direction = ASCENDING;
    }

    public int getDirection()
    {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(CustomerViewerSortableColumns sortableColumn)
    {
        if (sortableColumn == this.propertyIndex)
            direction = 1 - direction;
        else
        {
            this.propertyIndex = sortableColumn;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2)
    {
        Customer p1 = (Customer) e1;
        Customer p2 = (Customer) e2;
        int rc = 0;
        switch (propertyIndex)
        {
            case id:
                rc = Integer.compare(p1.getId(), p2.getId());
                break;
            case firstname:
                rc = p1.getFirstname().compareTo(p2.getFirstname());
                break;
            case lastname:
                rc = p1.getLastname().compareTo(p2.getLastname());
                break;
            case dateOfBirth:
                rc = p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
                break;
            default:
                rc = 0;
                break;
        }

        if (direction == DESCENDING)
        {
            rc = -rc;
        }
        return rc;
    }

}
