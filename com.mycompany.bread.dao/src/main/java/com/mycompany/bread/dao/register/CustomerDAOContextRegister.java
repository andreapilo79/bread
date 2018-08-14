package com.mycompany.bread.dao.register;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

import com.mycompany.bread.dao.CustomerDAO;
import com.mycompany.bread.dao.impl.CustomerDAOImpl;

/**
 * Add Customer DAO in the Application Context
 * @author ap
 *
 */
@Component(name = "com.mycompany.bread.dao.CustomerDAO", service = IContextFunction.class, property = "service.context.key=com.mycompany.bread.dao.CustomerDAO") public class CustomerDAOContextRegister
        extends ContextFunction
{
    @Override
    public Object compute(IEclipseContext context, String contextKey)
    {
        CustomerDAOImpl todoService = ContextInjectionFactory.make(CustomerDAOImpl.class, context);

        MApplication app = context.get(MApplication.class);
        IEclipseContext appCtx = app.getContext();
        appCtx.set("com.mycompany.bread.dao.CustomerDAO", todoService);

        return todoService;
    }

    public static <T> T getService(Class<T> clazz)
    {
        BundleContext bundleContext = FrameworkUtil.getBundle(CustomerDAO.class).getBundleContext();
        ServiceReference<T> reference = bundleContext.getServiceReference(clazz);
        return bundleContext.getService(reference);
    }
}