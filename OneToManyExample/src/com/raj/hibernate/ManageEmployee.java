package com.raj.hibernate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Unable to create SessionFactory "+e);
			throw new ExceptionInInitializerError(e);
		}
		ManageEmployee ME = new ManageEmployee();
		HashSet set1 = new HashSet();
		set1.add(new OneToManyCertificate("B.Tech"));
		set1.add(new OneToManyCertificate("MS"));
		set1.add(new OneToManyCertificate("P.hd"));
		
		Integer empID1 = ME.addEmployee("Raji Reddy", "Chintala", 10000, set1);
		
		HashSet set2 = new HashSet();
		set2.add(new OneToManyCertificate("B.Com"));
		set2.add(new OneToManyCertificate("M.Com"));
		
		Integer empID2 = ME.addEmployee("Vijay", "Chintala", 15000, set2);
		System.out.println("Employee List: ");
		ME.listEmployees();		
		ME.updateEmployee(empID1, 12000);
		System.out.println("Employee List: ");
		ME.listEmployees();		
		ME.deleteEmployee(empID2);
		System.out.println("Employee List: ");
		ME.listEmployees();
	}
	
	private Integer addEmployee(String fname,String lname,int salary,Set cert){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try{
			tx = session.beginTransaction();
			OneToManyEmployee employee = new OneToManyEmployee(fname, lname, salary);
			employee.setCertificates(cert);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.commit();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		return employeeID;
	}
	
	private void listEmployees(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List employees = session.createQuery("from OneToManyEmployee").list();
			for(Iterator iterator = employees.iterator();iterator.hasNext();){
				OneToManyEmployee employee = (OneToManyEmployee) iterator.next();
				System.out.println("First Name "+employee.getFirstName());
				System.out.println("Last Name "+employee.getLastName());
				System.out.println("Salary "+employee.getSalary());
				Set certificates = employee.getCertificates();
				for(Iterator itrt = certificates.iterator();itrt.hasNext();){
					OneToManyCertificate cert = (OneToManyCertificate) itrt.next();
					System.out.println("Certificate Name "+cert.getName());
				}
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null){
				tx.commit();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/* Method to update salary for an employee */
	   public void updateEmployee(Integer EmployeeID, int salary ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         OneToManyEmployee employee = 
	                    (OneToManyEmployee)session.get(OneToManyEmployee.class, EmployeeID); 
	         employee.setSalary( salary );
	         session.update(employee);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to delete an employee from the records */
	   public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         OneToManyEmployee employee = 
	                   (OneToManyEmployee)session.get(OneToManyEmployee.class, EmployeeID); 
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
