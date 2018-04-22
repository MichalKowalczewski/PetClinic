package com.util;

import org.hibernate.cfg.Configuration;

import com.petclinic.model.Owner;

import org.hibernate.SessionFactory;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		try {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Owner.class);
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		System.out.println("Hibernate successfully configured");
		return sessionFactory;
		}
		catch(Throwable throwable) {
			throw new ExceptionInInitializerError(throwable);
		}
	}
	
	public static SessionFactory getSessionFactory() {return sessionFactory;}

}
