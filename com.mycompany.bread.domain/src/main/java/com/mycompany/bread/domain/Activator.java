
/*******************************************************************************
 * Copyright (c) 2010 Oracle.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at 
 *     http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *     mkeith - Gemini JPA sample 
 ******************************************************************************/
package com.mycompany.bread.domain;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator, ServiceTrackerCustomizer<EntityManagerFactory, EntityManagerFactory>
{

    private BundleContext ctx;

    @SuppressWarnings("rawtypes")
    private ServiceTracker emfTracker;

    @Override
    @SuppressWarnings(
    {"rawtypes", "unchecked"})
    public void start(BundleContext context) throws Exception
    {
        ctx = context;

        emfTracker = new ServiceTracker(ctx, EntityManagerFactory.class.getName(), this);
        emfTracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        emfTracker.close();
    }

    @Override
    public EntityManagerFactory addingService(ServiceReference<EntityManagerFactory> ref)
    {
        Bundle b = ref.getBundle();
        EntityManagerFactory service = b.getBundleContext().getService(ref);
        EntityManagerHelper.setFactory(service);
        String unitName = (String) ref.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);
        return service;
    }

    @Override
    public void modifiedService(ServiceReference<EntityManagerFactory> ref, EntityManagerFactory service)
    {
        // nothing
    }

    @Override
    public void removedService(ServiceReference<EntityManagerFactory> ref, EntityManagerFactory service)
    {
        // nothing
    }
}