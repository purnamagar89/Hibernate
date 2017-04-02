package com.my.hibernate.hql.main;

import java.util.List;

import org.junit.Test;

import com.my.hibernate.hql.beans.Employee;
import com.my.hibernate.hql.util.EmployeeDAO;


public class MyTests {

	EmployeeDAO eDAO = new EmployeeDAO();
	
	@Test
	public void testloadEmployees_HQLNamedParameter_RealColumnNames(){
	//HQL select using a named-paramter.
		List<Employee> lstEmployee=eDAO.loadEmployees_HQLNamedParameter_RealColumnNames();
		for(Employee employee:lstEmployee)
		{
			System.out.println(employee.getEmpName());
		}
	}
	
	@Test
	public void testdeleteEmployee_HQLNamedParameter()
	{
		Long empIdToDelete=new Long(1);
		int delResult=eDAO.deleteEmployee_HQLNamedParameter(empIdToDelete);
		System.out.println("Delete : rows-deleted :"+delResult);
	}
	
	@Test
	public void testCopyEmployeeINAnotherEmployee()
	{
		/**
		 * Copy the whole table :-
		 * src table : tbl_employee
		 * dest table : tbl_anotheremployee
		 */
		int insResult=eDAO.copyEmployeeINAnotherEmployee();
		System.out.println("Insert : rows-inserted :"+insResult);
	}
	
	@Test
	public void testupdateEmployee_HQLNamedParameter()
	{
		int updResult=eDAO.updateEmployee_HQLNamedParameter("Praveen","Dallas");
		System.out.println("update : rows-updated :"+updResult);
	}

}
