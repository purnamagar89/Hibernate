package com.raj.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String[] args) {
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable e){
			System.err.println("Unable to create Session Factory "+e);
			throw new ExceptionInInitializerError(e);
		}
		ManageEmployee ME = new ManageEmployee();
		
		ME.addEmployees();
	}
	
	public void addEmployees(){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try{
			System.out.println("Begin addEmployees()");
			tx = session.beginTransaction();
			for(int i=0;i<6;i++){
				String fname = "First Name "+i;
				String lname = "Last Name "+i;
				int salary = i;
				BatchEmployee employee = new BatchEmployee(fname, lname, salary);
				employeeID = (Integer) session.save(employee);
				System.out.println("Employee ID: "+employeeID);
				if( i % 50 == 0 ){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			System.out.println("End addEmployees()");
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	    }finally {
	         session.close(); 
	      }
	    return ;
	}

}
