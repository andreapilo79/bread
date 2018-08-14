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
package com.mycompany.bread.dao.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.Validate;
import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.mycompany.bread.dao.CustomerDAO;
import com.mycompany.bread.domain.customer.Customer;

public class CustomerDAOImpl implements CustomerDAO
{
    private EntityManagerFactory emf;

    private EntityManager em;

    public CustomerDAOImpl()
    {
        activate();
    }

    @SuppressWarnings("unchecked")
    protected void activate()
    {
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        map.put(PersistenceUnitProperties.CLASSLOADER, getClass().getClassLoader());

        org.eclipse.persistence.jpa.PersistenceProvider persistenceProvider = new org.eclipse.persistence.jpa.PersistenceProvider();
        emf = persistenceProvider.createEntityManagerFactory("com.mycompany.bread", map);
        em = emf.createEntityManager();

    }

    protected void deactivate()
    {
        em.close();
        emf.close();
        em = null;
        emf = null;
    }

    @Override
    public void save(Customer customer)
    {
        Objects.requireNonNull(customer);
        Validate.isTrue(
            customer.getId() == 0,
            MessageFormat.format("Saving an already persisting object: id {0}", customer.getId()));

        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    @Override
    public void update(Customer customer)
    {
        Objects.requireNonNull(customer);
        Validate.isTrue(customer.getId() != 0, "Updating an not persisting object");

        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Customer customer)
    {
        Objects.requireNonNull(customer);

        em.getTransaction().begin();
        Customer find = em.find(Customer.class, customer.getId());
        em.remove(find);
        em.getTransaction().commit();
    }

    @Override
    public List<Customer> getAll()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);

        return allQuery.getResultList();
    }

}
