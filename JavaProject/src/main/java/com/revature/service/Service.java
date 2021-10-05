package com.revature.service;

import com.revature.models.Employee;

public interface Service {
	
	
	//Authenticate:
	
	//verify if the user exists
	public boolean verifyEmployeeExists(String username);
	
	//returns true if employee is a finance manger.
	public boolean verifyManager(String username);
	
	//returns the Employee object associated with the username
	public Employee getEmployeeByUsername(String username);
	
	//authenticate the user
	public boolean authenticateEmployee(String username, String password);
	
	
	//Employee Services:
	
	
	//public boolean submitReport(Employee emp);
	
	
}
