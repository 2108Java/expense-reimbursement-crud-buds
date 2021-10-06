package com.revature.repo;

import com.revature.models.Employee;

public interface EmployeeDAO {
	
	//This method will use the employees username to return the Employee object
	public Employee selectEmployeeByUsername(Employee user);
	
	//This method will use the employees username and return true if the user exists
	public boolean isEmployee(String username);
	
	//This method will use the employees username and determine if the user is a finance manager
	//public boolean isFinanceManager(String username);

}
