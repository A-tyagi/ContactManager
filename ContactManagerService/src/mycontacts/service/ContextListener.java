package mycontacts.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mycontacts.dao.ContactManagerSQL;
import mycontacts.dao.ContactsDAOInterface;


public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        System.out.println("###############################################  Starting up! #############################################");
        System.out.println("Initializing DB");
        ServletContext servletContext  = servletContextEvent.getServletContext();
        try {
        	ContactsDAOInterface contactsDAO = new ContactManagerSQL();
	        servletContext.setAttribute("ContactsDAO", contactsDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	System.out.println("###############################################  Shutting down! #############################################");
    }
}
