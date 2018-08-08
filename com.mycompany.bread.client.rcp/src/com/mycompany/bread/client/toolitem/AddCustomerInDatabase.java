package com.mycompany.bread.client.toolitem;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.mycompany.bread.client.service.ServiceLocator;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;

public class AddCustomerInDatabase
{

    private static final String[] firstnames =
    {"Giuseppe", "Gioachino"};

    private static final String[] lastnames =
    {"Verdi", "Rossini"};

    private Random random = new Random();

    @Execute
    public void execute(EPartService partService)
    {
        Customer randomCustomer = getRandomCustomer();
        CustomerDBService customerService = ServiceLocator.getService(CustomerDBService.class);
        customerService.addCustomer(randomCustomer);
    }

    private Customer getRandomCustomer()
    {
        String firstName = getName(firstnames);
        String lastName = getName(lastnames);

        int year = getRandomBetween(1900, 2000);
        int month = random.nextInt(Calendar.DECEMBER);
        int day = getRandomDay(year, month);

        GregorianCalendar dateOfBirth = new GregorianCalendar(year, month, day);

        Customer customer = new Customer(firstName, lastName, dateOfBirth);
        return customer;
    }

    private String getName(String[] nameArray)
    {
        int idx = random.nextInt(nameArray.length);
        return nameArray[idx];
    }

    private int getRandomBetween(int min, int max)
    {
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    private int getRandomDay(int year, int month)
    {
        GregorianCalendar firstDayInMonth = new GregorianCalendar(year, month, 1);
        int actualMaximum = firstDayInMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        return random.nextInt(actualMaximum);
    }
}