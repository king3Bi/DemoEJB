package com.ejb.demo.hibernate;

import java.util.Properties;

import com.ejb.demo.model.Role;
import com.ejb.demo.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	private static final SessionFactory FACTORY;

	static {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		conf.addAnnotatedClass(Role.class);
		conf.addAnnotatedClass(User.class);
		
		ServiceRegistry registry =  new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		FACTORY = conf.buildSessionFactory(registry);
	}
	
	public static SessionFactory getFactory() {
		return FACTORY;
	}
	
}
