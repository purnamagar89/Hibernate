package com.raj.hibernate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department {

	@Id
	@GeneratedValue
	@Column(name="DEPARTMENT_ID")
	private Long departmentId;
	
	@Column(name="DEPT_NAME")
	private String departmentName;
	
	//@OneToMany annotation defines a many-valued association with one-to-many multiplicity.
	//If the collection is defined using generics to specify the element type, the associated target entity type need not be specified; 
	//otherwise the target entity class must be specified.
	
	//To declare a side as not responsible for the relationship, the attribute mappedBy is used. 
	//mappedBy refers to the property name of the association on the owner side. 
	
	//We must not declare the join column since it has already been declared on the owners side.
	@OneToMany(mappedBy="department")
	private Set<Employee> employees;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
