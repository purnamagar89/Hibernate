package com.raj.hibernate;

import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String[] args) {		
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable e){
			System.err.println("Failed to create sessionFactory object." + e);
	         throw new ExceptionInInitializerError(e); 
		}
		ManageEmployee ME = new ManageEmployee();
		
		ME.addEmployee("Raj", "Chintala", 3000);
		ME.addEmployee("Vijay", "Chintala", 4000);
		ME.addEmployee("Sandeep", "Dandoli", 2000);
		
		System.out.println("Employee List*****************");
		ME.listEmployees();
		
		ME.employeeCount();
		
		ME.totalSalary();

	}
	
	public Integer addEmployee(String fname, String lname, int salary){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try{
	         tx = session.beginTransaction();
	         CriteriaEmployee employee = new CriteriaEmployee(fname, lname, salary);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	
	public void listEmployees(){
		Session session  = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(CriteriaEmployee.class);
			cr.add(Restrictions.gt("salary", 2000));
			List employees = cr.list();
			for(Iterator itr = employees.iterator();itr.hasNext();){
				CriteriaEmployee employee = (CriteriaEmployee) itr.next();
				System.out.println("FirstName "+employee.getFirstName());
				System.out.println("LastName "+employee.getLastName());
				System.out.println("Salary "+employee.getSalary());
			}
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public void employeeCount(){
		Session session = factory.openSession();
		Transaction tx= null;
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(CriteriaEmployee.class);
			cr.setProjection(Projections.rowCount());
			List rowCount = cr.list();
			System.out.println("Emp Count "+rowCount.get(0));
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
	
	public void totalSalary(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(CriteriaEmployee.class);
			cr.setProjection(Projections.sum("salary"));
			List salary = cr.list();
			System.out.println("Total Salary: "+salary.get(0));
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}

}
