package com.raj.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Falied to create SessionFactory object. "+e);
			throw new ExceptionInInitializerError(e);
		}
		ManageEmployee me = new ManageEmployee();
		Integer empID1 = me.addEmployee("Raj", "Chintala", 10000);
		Integer empID2 = me.addEmployee("Ravi", "Chintala", 15000);
		Integer empID3 = me.addEmployee("Vijay", "Chintala", 16000);
		
		me.updateEmployee(empID1, 20000);
		me.deleteEmployee(empID2);
		
		me.listEmployees();
	}
	
	public Integer addEmployee(String fname,String lname,int salary){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try{
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employeeID = (Integer)session.save(employee);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return employeeID;
	}
	
	public void listEmployees(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List employees = session.createQuery("From Employee").list();
			for(Iterator iterator = employees.iterator();iterator.hasNext();){
				Employee employee = (Employee)iterator.next();
				System.out.println("First Name "+employee.getFirstName());
				System.out.println("Last Name "+employee.getLastName());
				System.out.println("Salary "+employee.getSalary());
			}
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	
	public void updateEmployee(Integer employeeID,int salary){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, employeeID);
			//System.out.println("Employee Object: "+employee);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}
	
	public void deleteEmployee(Integer employeeID){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, employeeID);
			session.delete(employee);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}

}
