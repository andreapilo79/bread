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
package com.mycompany.bread.service.deal;

import java.util.List;

import com.mycompany.bread.domain.deal.Deal;

/**
 * Dao interface for {@link Deal} objects
 * @author ap
 */
public interface DealService
{
    /**
     * Save
     * @param customer to be saved
     * @throws IllegalArgumentException if the {@link Deal#getId()} is NOT null
     * @throws NullPointerException if the customer argument is null
     */
    void save(Deal customer);

    /**
     * Update
     * @param customer to be updated
     * @throws IllegalArgumentException if the {@link Deal#getId()} is null
     * @throws NullPointerException if the customer argument is null
     */
    void update(Deal customer);

    /**
     * Delete the {@link Deal} object.
     * @param customer to be deleted
     * @throws IllegalArgumentException if the {@link Deal#getId()} is null
     * @throws NullPointerException if the customer argument is null
     */
    void delete(Deal customer);

    /**
     * Save
     * @param customer to be saved
     */
    List<Deal> getAll();
}
