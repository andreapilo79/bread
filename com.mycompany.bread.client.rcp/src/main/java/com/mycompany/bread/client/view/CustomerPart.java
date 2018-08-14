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
package com.mycompany.bread.client.view;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.mycompany.bread.dao.CustomerDAO;
import com.mycompany.bread.domain.customer.Customer;

/**
 * Customer Part
 * @author ap
 *
 */
public class CustomerPart
{
    public static final String ID = CustomerPart.class.getCanonicalName();

    private TableViewer viewer;

    private CustomerViewerComparator viewerComparator;

    @Inject
    private ESelectionService selectionService;

    @Inject
    private CustomerDAO customerDAO;

    @PostConstruct
    public void createControls(Composite parent)
    {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

        createColumns();
        addSelectionListener();

        viewer.setContentProvider(new CustomersContentProvider(customerDAO));
        viewer.setInput(new Object());
        viewerComparator = new CustomerViewerComparator();
        viewer.setComparator(viewerComparator);

        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }

    private void createColumns()
    {
        createIDColumn();
        createFirstNameColumn();
        createLastNameColumn();
        createDateOfBirthColumn();
    }

    private void createIDColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setWidth(100);
        column.setText("ID");
        column.setAlignment(SWT.RIGHT);
        column.addSelectionListener(getSelectionAdapter(CustomerViewerSortableColumns.id));
        viewerColumn.setLabelProvider(new ColumnLabelProvider()
        {
            @Override
            public String getText(Object element)
            {
                Customer p = (Customer) element;
                return Long.toString(p.getId());
            }
        });
    }

    private void createFirstNameColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setWidth(100);
        column.setText("First name");
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(getSelectionAdapter(CustomerViewerSortableColumns.firstname));

        viewerColumn.setLabelProvider(new ColumnLabelProvider()
        {
            @Override
            public String getText(Object element)
            {
                Customer p = (Customer) element;
                return p.getFirstname();
            }
        });
    }

    private void createLastNameColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setWidth(100);
        column.setText("Last name");
        column.setAlignment(SWT.LEFT);
        column.addSelectionListener(getSelectionAdapter(CustomerViewerSortableColumns.lastname));

        viewerColumn.setLabelProvider(new ColumnLabelProvider()
        {
            @Override
            public String getText(Object element)
            {
                Customer p = (Customer) element;
                return p.getLastname();
            }
        });
    }

    private void createDateOfBirthColumn()
    {
        TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = viewerColumn.getColumn();
        column.setWidth(100);
        column.setText("Date of birth");
        column.setAlignment(SWT.RIGHT);
        column.addSelectionListener(getSelectionAdapter(CustomerViewerSortableColumns.dateOfBirth));

        viewerColumn.setLabelProvider(new ColumnLabelProvider()
        {
            @Override
            public String getText(Object element)
            {
                Customer p = (Customer) element;
                GregorianCalendar dateOfBirth = p.getDateOfBirth();
                DateFormat df = DateFormat.getDateInstance();
                return df.format(dateOfBirth.getTime());
            }
        });
    }

    private SelectionAdapter getSelectionAdapter(final CustomerViewerSortableColumns sortableColumn)
    {
        return new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                viewerComparator.setColumn(sortableColumn);

                int dir = viewerComparator.getDirection();
                viewer.getTable().setSortDirection(dir);
                viewer.refresh();
            }
        };
    }

    private void addSelectionListener()
    {
        viewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            @Override
            public void selectionChanged(SelectionChangedEvent event)
            {
                IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
                selectionService.setSelection(selection.getFirstElement());
            }
        });
    }

    @Inject
    private void selectionChanged(@Optional
    @Named(IServiceConstants.ACTIVE_SELECTION)
    Customer customer)
    {
        // nothing here
    }

    @Focus
    public void onFocus()
    {
        viewer.getControl().setFocus();
    }

    public void refresh()
    {
        viewer.setInput(new Object());
    }
}