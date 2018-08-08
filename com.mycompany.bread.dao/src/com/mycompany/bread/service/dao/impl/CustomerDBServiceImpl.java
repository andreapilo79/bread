package com.mycompany.bread.service.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.mycompany.bread.domain.customer.Customer;
import com.mycompany.bread.service.dao.CustomerDBService;
import com.mycompany.bread.service.dao.CustomerEvent;
import com.mycompany.bread.service.dao.ICustomerDBChangeObserver;

public class CustomerDBServiceImpl implements CustomerDBService
{
    private EntityManagerFactory emf;

    private EntityManager em;

    private List<ICustomerDBChangeObserver> observers;

    @SuppressWarnings("unchecked")
    protected void activate()
    {
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        map.put(PersistenceUnitProperties.CLASSLOADER, getClass().getClassLoader());

        org.eclipse.persistence.jpa.PersistenceProvider persistenceProvider = new org.eclipse.persistence.jpa.PersistenceProvider();
        emf = persistenceProvider.createEntityManagerFactory("com.mycompany.bread", map);
        em = emf.createEntityManager();

        observers = new LinkedList<>();
    }

    protected void deactivate()
    {
        observers = null;
        em.close();
        emf.close();
        em = null;
        emf = null;
    }

    @Override
    public void addCustomer(Customer customer)
    {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        sendEvent(CustomerEvent.ADDED, customer);
    }

    @Override
    public void modifyCustomer(Customer customer)
    {
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        sendEvent(CustomerEvent.CHANGED, customer);
    }

    @Override
    public void removePersion(Customer customer)
    {
        em.getTransaction().begin();
        Customer find = em.find(Customer.class, customer.getId());
        em.remove(find);
        em.getTransaction().commit();
        sendEvent(CustomerEvent.REMOVED, customer);
    }

    @Override
    public List<Customer> getCustomers()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);

        List<Customer> resultList = allQuery.getResultList();
        return resultList;
    }

    private void sendEvent(String eventID, Customer affectedCustomer)
    {
        for (ICustomerDBChangeObserver observer : observers)
            observer.changed(eventID, affectedCustomer);
    }

    @Override
    public void registerCustomerObserver(ICustomerDBChangeObserver observer)
    {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void unregisterCustomerObserver(ICustomerDBChangeObserver observer)
    {
        if (observers.contains(observer))
            observers.remove(observer);
    }
}
