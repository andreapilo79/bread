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
package com.mycompany.bread.db;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jdbc.DataSourceFactory;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Example of how to access a DataSource from a client program
 * 
 * @author mkeith
 */
public class Activator implements BundleActivator, ServiceTrackerCustomizer
{

    public static final String EMBEDDED_DERBY_DRIVER_NAME = "org.h2.Driver";

    public static final String JDBC_4_VERSION = "1.3.175 (2014-01-18)";

    private ServiceTracker dsfTracker;

    private BundleContext ctx;

    /* === Activator methods === */

    @SuppressWarnings(
    {"rawtypes", "unchecked"})
    @Override
    public void start(BundleContext context) throws Exception
    {
        ctx = context;
        dsfTracker = new ServiceTracker(ctx, DataSourceFactory.class.getName(), this);
        dsfTracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        dsfTracker.close();
    }

    /* === ServiceTracker methods === */

    @SuppressWarnings(
    {"unchecked", "rawtypes"})
    @Override
    public Object addingService(ServiceReference ref)
    {
        DataSourceFactory service = ctx.getService(ref);
        return service;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void modifiedService(ServiceReference ref, Object service)
    {
        /* Do nothing */ }

    @SuppressWarnings("rawtypes")
    @Override
    public void removedService(ServiceReference ref, Object service)
    {
        ctx.ungetService(ref);
    }
}
