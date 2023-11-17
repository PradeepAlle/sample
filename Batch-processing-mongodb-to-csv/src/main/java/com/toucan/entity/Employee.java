package com.toucan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Employee {
	@Id
	private String EmployeeID;
	private String EmployeeName;
	private String Address;
	private String email;
	private String Department;
	private float Salary;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public String getEmployeeID() {
		return EmployeeID;
	}



	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}



	public String getEmployeeName() {
		return EmployeeName;
	}



	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}



	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDepartment() {
		return Department;
	}



	public void setDepartment(String department) {
		Department = department;
	}



	public float getSalary() {
		return Salary;
	}



	public void setSalary(float salary) {
		Salary = salary;
	}



	public Employee(String employeeID, String employeeName, String address, String email, String department,
			float salary) {
		super();
		EmployeeID = employeeID;
		EmployeeName = employeeName;
		Address = address;
		this.email = email;
		Department = department;
		Salary = salary;
	}



	
	
	
	
}

	