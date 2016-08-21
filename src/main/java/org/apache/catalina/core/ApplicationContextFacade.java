/*
 * $Header: /home/cvs/jakarta-tomcat-4.0/catalina/src/share/org/apache/catalina/core/ApplicationContextFacade.java,v 1.3 2001/07/22 20:25:08 pier Exp $
 * $Revision: 1.3 $
 * $Date: 2001/07/22 20:25:08 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */


package org.apache.catalina.core;


import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

/**
 * Facade object which masks the internal <code>ApplicationContext</code>
 * object from the web application.
 *
 * @author Remy Maucherat
 * @version $Revision: 1.3 $ $Date: 2001/07/22 20:25:08 $
 */

public final class ApplicationContextFacade
    implements ServletContext {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this class, associated with the specified
     * Context instance.
     *
     * @param context The associated Context instance
     */
    public ApplicationContextFacade(ApplicationContext context) {
        super();
        this.context = context;
    }


    // ----------------------------------------------------- Instance Variables


    /**
     * Wrapped application context.
     */
    private ApplicationContext context = null;


    // ------------------------------------------------- ServletContext Methods


    public ServletContext getContext(String uripath) {
        ServletContext theContext = context.getContext(uripath);
        if ((theContext != null) &&
            (theContext instanceof ApplicationContext))
            theContext = ((ApplicationContext) theContext).getFacade();
        return (theContext);
    }


    public int getMajorVersion() {
        return context.getMajorVersion();
    }


    public int getMinorVersion() {
        return context.getMinorVersion();
    }


    public String getMimeType(String file) {
        return context.getMimeType(file);
    }


    public Set getResourcePaths(String path) {
        return context.getResourcePaths(path);
    }


    public URL getResource(String path)
        throws MalformedURLException {
        return context.getResource(path);
    }


    public InputStream getResourceAsStream(String path) {
        return context.getResourceAsStream(path);
    }


    public RequestDispatcher getRequestDispatcher(String path) {
        return context.getRequestDispatcher(path);
    }


    public RequestDispatcher getNamedDispatcher(String name) {
        return context.getNamedDispatcher(name);
    }


    public Servlet getServlet(String name)
        throws ServletException {
        return context.getServlet(name);
    }


    public Enumeration getServlets() {
        return context.getServlets();
    }


    public Enumeration getServletNames() {
        return context.getServletNames();
    }


    public void log(String msg) {
        context.log(msg);
    }


    public void log(Exception exception, String msg) {
        context.log(exception, msg);
    }


    public void log(String message, Throwable throwable) {
        context.log(message, throwable);
    }


    public String getRealPath(String path) {
        return context.getRealPath(path);
    }


    public String getServerInfo() {
        return context.getServerInfo();
    }


    public String getInitParameter(String name) {
        return context.getInitParameter(name);
    }


    public Enumeration getInitParameterNames() {
        return context.getInitParameterNames();
    }


    public Object getAttribute(String name) {
        return context.getAttribute(name);
    }


    public Enumeration getAttributeNames() {
        return context.getAttributeNames();
    }


    public void setAttribute(String name, Object object) {
        context.setAttribute(name, object);
    }


    public void removeAttribute(String name) {
        context.removeAttribute(name);
    }


    public String getServletContextName() {
        return context.getServletContextName();
    }


	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getEffectiveMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getEffectiveMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean setInitParameter(String name, String value) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Dynamic addServlet(String servletName, String className) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Dynamic addServlet(String servletName, Servlet servlet) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServletRegistration getServletRegistration(String servletName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FilterRegistration getFilterRegistration(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public SessionCookieConfig getSessionCookieConfig() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addListener(String className) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T extends EventListener> void addListener(T t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addListener(Class<? extends EventListener> listenerClass) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void declareRoles(String... roleNames) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getVirtualServerName() {
		// TODO Auto-generated method stub
		return null;
	}


}
