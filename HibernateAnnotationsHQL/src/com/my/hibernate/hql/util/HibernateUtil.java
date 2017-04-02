package com.my.hibernate.hql.util;




import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.hql.beans.Employee;



public class HibernateUtil {

	 Session session = null;
	 SessionFactory sessionFactory = null;



	public  Session getHibernateSession() {
		session=getHibernateSessionFactory().openSession();
		return session;
	}

	public  SessionFactory getHibernateSessionFactory() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	

}
