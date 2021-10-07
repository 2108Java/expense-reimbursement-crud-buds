package com.revature.service;

import com.revature.models.Employee;

public interface Service {
	
	
	//Authenticate:
	
	//verify if the user exists
	public boolean verifyEmployeeExists(String username);
	
	//returns true if employee is a finance manger.
	public boolean verifyManager(Employee user);
	
	//returns the Employee object associated with the username
	public Employee getEmployee(Employee user);
	
	//authenticate the user
	public boolean authenticateEmployee(Employee user);
	
	
	//Employee Services:
	
	
	//public boolean submitReport(Employee emp);
	
	public Employee getEmployeeReports(Employee user);
	
	
}
