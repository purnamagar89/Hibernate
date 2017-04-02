package com.raj.hibernate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String [] args){
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create session factory object "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		ManageEmployee ME = new ManageEmployee();
		HashMap map = new HashMap();
		map.put("ComputerScience", new Certificate("MCA"));
		map.put("BusinessManagement", new Certificate("MBA"));
		map.put("ProjectManagement", new Certificate("PMP"));
		
		/* Add employee records in the database */
	      Integer empID = ME.addEmployee("Manoj", "Kumar", 4000, map);

	      /* List down all the employees */
	      ME.listEmployees();

	      /* Update employee's salary records */
	      ME.updateEmployee(empID, 5000);

	      /* List down all the employees */
	      ME.listEmployees();
	}
	
	/**
	 * @param fname
	 * @param lname
	 * @param salary
	 * @param cert
	 * @return
	 */
	public Integer addEmployee(String fname,String lname,int salary,HashMap cert){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try{
			tx = session.beginTransaction();
			Employee employee = new Employee(fname,lname,salary);
			employee.setCertificates(cert);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		}catch(HibernateException e){
			if(tx!= null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return employeeID;
	}
	
	public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM Employee").list(); 
	         for (Iterator iterator1 = 
	                           employees.iterator(); iterator1.hasNext();){
	            Employee employee = (Employee) iterator1.next(); 
	            System.out.print("First Name: " + employee.getFirstName()); 
	            System.out.print("  Last Name: " + employee.getLastName()); 
	            System.out.println("  Salary: " + employee.getSalary());
	            Map ec = employee.getCertificates();
	            System.out.println("Certificate: " + 
	              (((Certificate)ec.get("ComputerScience")).getName()));
	            System.out.println("Certificate: " + 
	              (((Certificate)ec.get("BusinessManagement")).getName()));
	            System.out.println("Certificate: " + 
	              (((Certificate)ec.get("ProjectManagement")).getName()));
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	public void updateEmployee(Integer employeeID,int salary){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, employeeID);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally {
	         session.close(); 
	      }
	}
	
	public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                   (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
}
