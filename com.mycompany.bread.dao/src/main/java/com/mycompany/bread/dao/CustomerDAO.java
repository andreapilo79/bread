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
package com.mycompany.bread.dao;

import java.util.List;

import com.mycompany.bread.domain.customer.Customer;

/**
 * Dao interface for {@link Customer} objects
 * @author ap
 */
public interface CustomerDAO
{
    /**
     * Save
     * @param customer to be saved
     * @throws IllegalArgumentException if the {@link Customer#getId()} is NOT null
     * @throws NullPointerException if the customer argument is null
     */
    void save(Customer customer);

    /**
     * Update
     * @param customer to be updated
     * @throws IllegalArgumentException if the {@link Customer#getId()} is null
     * @throws NullPointerException if the customer argument is null
     */
    void update(Customer customer);

    /**
     * Delete the {@link Customer} object.
     * @param customer to be deleted
     * @throws IllegalArgumentException if the {@link Customer#getId()} is null
     * @throws NullPointerException if the customer argument is null
     */
    void delete(Customer customer);

    /**
     * Save
     * @param customer to be saved
     */
    List<Customer> getAll();
}
