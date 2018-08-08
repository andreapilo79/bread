package com.mycompany.bread.client.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ServiceLocator
{
    private ServiceLocator()
    {
        // hiding
    }

    public static <T> T getService(Class<T> clazz)
    {
        BundleContext bundleContext = FrameworkUtil.getBundle(ServiceLocator.class).getBundleContext();
        ServiceReference<T> reference = bundleContext.getServiceReference(clazz);
        return bundleContext.getService(reference);
    }
}
