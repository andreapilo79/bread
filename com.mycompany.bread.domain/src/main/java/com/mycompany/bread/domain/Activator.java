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
