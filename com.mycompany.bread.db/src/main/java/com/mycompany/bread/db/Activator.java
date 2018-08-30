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
 *     mkeith - Gemini DBAccess examples 
 ******************************************************************************/
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
