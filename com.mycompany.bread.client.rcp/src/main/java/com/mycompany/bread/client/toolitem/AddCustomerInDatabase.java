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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.customer.CustomerService;

/**
 * Action: add random customer in databse
 * @author ap
 */
public class AddCustomerInDatabase
{

    private static final String[] firstnames =
    {"Giuseppe", "Gioachino"};

    private static final String[] lastnames =
    {"Verdi", "Rossini"};

    private Random random = new Random();

    @Inject
    private CustomerService customerService;

    @Execute
    public void execute(EPartService partService)
    {
        Customer randomCustomer = getRandomCustomer();
        customerService.save(randomCustomer);
    }

    private Customer getRandomCustomer()
    {
        String firstName = getName(firstnames);
        String lastName = getName(lastnames);

        int year = getRandomBetween(1900, 2000);
        int month = random.nextInt(Calendar.DECEMBER);
        int day = getRandomDay(year, month);

        GregorianCalendar dateOfBirth = new GregorianCalendar(year, month, day);

        return new Customer(firstName, lastName, dateOfBirth);
    }

    private String getName(String[] nameArray)
    {
        int idx = random.nextInt(nameArray.length);
        return nameArray[idx];
    }

    private int getRandomBetween(int min, int max)
    {
        return random.nextInt(max - min + 1) + min;
    }

    private int getRandomDay(int year, int month)
    {
        GregorianCalendar firstDayInMonth = new GregorianCalendar(year, month, 1);
        int actualMaximum = firstDayInMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        return random.nextInt(actualMaximum);
    }
}