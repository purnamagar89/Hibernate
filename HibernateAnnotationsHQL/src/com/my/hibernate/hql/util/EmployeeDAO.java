package com.my.hibernate.hql.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.my.hibernate.hql.beans.Employee;

public class EmployeeDAO {
	/**
	 * HQL with a named-paramter.
	 * emp_id is the real column name. we can use both the real colum
	 * - name(i.e. emp_id) and the mapped column name (i.e. empId).
	 */
	public List<Employee> loadEmployees_HQLNamedParameter_RealColumnNames()
	{
		HibernateUtil hUtil=new HibernateUtil();
		Session session=hUtil.getHibernateSession();
		Query query = session.createQuery("from employee where empId = :myEmpId ");// select * from employee (sql) --> select e.name,e.age from employee e (hql) OR from employee (hql)
		query.setParameter("myEmpId", new Long(1));
		List<Employee> lstEmployees = query.list();
		return lstEmployees;
	}
	
	/**
	 * HQL-select with a named-paramter.
	 * empId is the mapped column name. we can use both the real colum
	 * - name(i.e. emp_id) and the mapped column name (i.e. empId).
	 */
	public List<Employee> loadEmployees_HQLNamedParameter_MappedColumnNames()
	{
		HibernateUtil hUtil=new HibernateUtil();
		Session session=hUtil.getHibernateSession();
		Query query = session.createQuery("from employee where empId = :myEmpId ");
		query.setParameter("myEmpId", new Long(1));
		List<Employee> lstEmployees = query.list();
		return lstEmployees;
	}
	
	/**
	 * 
	 *	HQL-delete with a named-paramter.
	 * empId is the mapped column name. we can use both the real colum
	 * - name(i.e. emp_id) and the mapped column name (i.e. empId).
	 */
	public int deleteEmployee_HQLNamedParameter(Long empId)
	{
		int result=0;
		Transaction tx=null;
		try{
		HibernateUtil hUtil=new HibernateUtil();
		Session session=hUtil.getHibernateSession();
		tx=session.beginTransaction();
		Query query = session.createQuery("delete employee where empId = :myEmpId ");
		query.setParameter("myEmpId", empId);
		result=query.executeUpdate();
		tx.commit();
		 
		}
		catch(RuntimeException e)
		{
			try{
				tx.rollback();
			}
			catch(RuntimeException re)
			{
				System.out.println("Could not rollback transaction :"+re);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * HQL only supports insert from another table. 
	 * Therefore use the syntax below to copy one table into another :-
	 * insert into Object (id, name) select oo.id, oo.name from OtherObject oo. 
	 */
	public int copyEmployeeINAnotherEmployee()
	{
		int result=0;
		Transaction tx=null;
		try{
		HibernateUtil hUtil=new HibernateUtil();
		Session session=hUtil.getHibernateSession();
		
		tx=session.beginTransaction();
		Query query = session.createQuery("insert into anotheremployee(empId,empName,empAddr,empSalary)  select emp.empId,emp.empName,emp.empAddr,emp.empSalary from employee emp");
		result=query.executeUpdate();
		tx.commit();
		}
		catch(RuntimeException re1)
		{
			try{
				re1.printStackTrace();
				tx.rollback();
			}
			catch(RuntimeException re)
			{
				System.out.println("Could not rollback transaction :"+re);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * HQL-update with a named-paramter.
	 * empId is the mapped column name. we can use both the real colum
	 * - name(i.e. emp_id) and the mapped column name (i.e. empId).
	 */
	public int updateEmployee_HQLNamedParameter(String empName,String empAddr)
	{
		int result=0;
		Transaction tx=null;
		try{
		HibernateUtil hUtil=new HibernateUtil();
		Session session=hUtil.getHibernateSession();
		tx=session.beginTransaction();
		Query query = session.createQuery("update employee set empAddr = :myEmpAddr where empName=:myEmpName)");
		query.setParameter("myEmpAddr", empAddr);
		query.setParameter("myEmpName", empName);
		result=query.executeUpdate();
		tx.commit();
		}
		catch(RuntimeException re1)
		{
			try{
				re1.printStackTrace();
				tx.rollback();
			}
			catch(RuntimeException re)
			{
				System.out.println("Could not rollback transaction :"+re);
			}
		}
		return result;
	}
}
