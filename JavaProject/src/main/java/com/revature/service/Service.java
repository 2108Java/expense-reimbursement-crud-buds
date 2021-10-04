package com.revature.service;

import com.revature.models.Employee;

public interface Service {
	
	
	//Authenticate
	public boolean verify(String username);
	
	public boolean verifyManager(String username);
	
	public boolean authenticate(String username, String password);
	
	
	//Employee Services
	public Employee getUserByUsername(String username);
	
	
	//public boolean submitReport(Employee emp);
	
	
}
