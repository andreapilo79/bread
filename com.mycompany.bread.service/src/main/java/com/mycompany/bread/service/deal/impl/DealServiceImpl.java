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
package com.mycompany.bread.service.deal.impl;

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

import com.mycompany.bread.domain.deal.Deal;
import com.mycompany.bread.service.deal.DealService;

public class DealServiceImpl implements DealService
{
    private EntityManagerFactory emf;

    private EntityManager em;

    public DealServiceImpl()
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
    public void save(Deal deal)
    {
        Objects.requireNonNull(deal);
        Validate.isTrue(
            deal.getId() == null,
            MessageFormat.format("Saving an already persisting object: id {0}", deal.getId()));

        em.getTransaction().begin();
        em.persist(deal);
        em.getTransaction().commit();
    }

    @Override
    public void update(Deal deal)
    {
        Objects.requireNonNull(deal);
        Validate.isTrue(deal.getId() != null, "Updating an not persisting object");

        em.getTransaction().begin();
        em.merge(deal);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Deal deal)
    {
        Objects.requireNonNull(deal);

        em.getTransaction().begin();
        Deal find = em.find(Deal.class, deal.getId());
        em.remove(find);
        em.getTransaction().commit();
    }

    @Override
    public List<Deal> getAll()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Deal> cq = cb.createQuery(Deal.class);
        Root<Deal> rootEntry = cq.from(Deal.class);
        CriteriaQuery<Deal> all = cq.select(rootEntry);
        TypedQuery<Deal> allQuery = em.createQuery(all);

        return allQuery.getResultList();
    }

}
