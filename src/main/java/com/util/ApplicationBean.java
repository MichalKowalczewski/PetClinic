package com.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean(name = "applicationBean", eager = true)
@ApplicationScoped
public class ApplicationBean{
	public static Session session;
	private static final Logger logger = LoggerFactory.getLogger(ApplicationBean.class);
	private SessionFactory factory;
	
    public ApplicationBean() {
    	logger.info("ApplicationContainer constructed");
    }
	
	@PostConstruct
    public void init() {
		factory = HibernateUtil.getSessionFactory();
    	logger.info("Initialized");
		session = factory.openSession();
	}	
	
	public Session getSession() {
    	logger.info("Took session");
		return session;
	}

	public SessionFactory getSessionFactory() {
		return factory;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@PreDestroy
	public void finish() {
		session.close();
	}
	
	public void closeSession() {
		factory.close();
	}

}
