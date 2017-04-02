package com.my.hibernate.hql.beans;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="anotheremployee")
@Table(name="tbl_anotheremployee")
public class AnotherEmployee {
	
	private Long empId;
	private String empName;
	private String empAddr;
	private Double empSalary;
	
	public AnotherEmployee()
	{
		
	}
	
	public AnotherEmployee(String empName,String empAddr,Double empSalary)
	{
		this.empName=empName;
		this.empAddr=empAddr;
		this.empSalary=empSalary;
	}
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="emp_id")
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	
	@Column(name="emp_name")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name="emp_addr")
	public String getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}
	
	@Column(name="emp_salary",precision=6)
	public Double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}
	
	

}
