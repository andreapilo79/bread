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
package com.mycompany.bread.service.customer.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.Validate;

import com.mycompany.bread.domain.EntityManagerHelper;
import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.customer.CustomerService;

public class CustomerServiceImpl implements CustomerService
{

    public CustomerServiceImpl()
    {
    }

    @Override
    public void save(Customer customer)
    {
        Objects.requireNonNull(customer);
        Validate.isTrue(
            customer.getId() == null,
            MessageFormat.format("Saving an already persisting object: id {0}", customer.getId()));

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(customer);
        EntityManagerHelper.commit();
    }

    @Override
    public void update(Customer customer)
    {
        Objects.requireNonNull(customer);
        Validate.isTrue(customer.getId() != null, "Updating an not persisting object");

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(customer);
        EntityManagerHelper.commit();
    }

    @Override
    public void delete(Customer customer)
    {
        Objects.requireNonNull(customer);

        EntityManagerHelper.beginTransaction();
        Customer find = EntityManagerHelper.getEntityManager().find(Customer.class, customer.getId());
        EntityManagerHelper.getEntityManager().remove(find);
        EntityManagerHelper.commit();
    }

    @Override
    public List<Customer> getAll()
    {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        if (entityManager == null)
            return null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = entityManager.createQuery(all);

        return allQuery.getResultList();
    }

}
