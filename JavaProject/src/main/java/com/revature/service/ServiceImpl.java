package com.revature.service;

import com.revature.models.Employee;
import com.revature.repo.EmployeeDAO;

public class ServiceImpl implements Service{

	private EmployeeDAO emDao;
	
	public ServiceImpl(EmployeeDAO emDao) {
		this.emDao = emDao;
	}
	
	@Override
	public boolean verifyEmployeeExists(String username) {			
		return emDao.isEmployee(username); 
	}
	
	@Override
	public Employee getEmployeeByUsername(String username) {
		return emDao.selectEmployeeByUsername(username);
	}
	
	@Override
	public boolean authenticateEmployee(String username, String password){	
		
		boolean userExists = this.verifyEmployeeExists(username);
		boolean authenticated = false;
		
		if(userExists) {
			//System.out.println("User Exists");
			Employee em = this.getEmployeeByUsername(username);
			//System.out.println(un +" "+ pw);
			
			if(em.getPassword().equals(password)) {
				authenticated = true;
				System.out.println("Username & Password Match");
			}else {
				System.out.println("Username & Password Dont Match");
			}
		}
		
		return authenticated;
	}

	@Override
	public boolean verifyManager(String username) {
		return emDao.isFinanceManager(username);
	}
	
	
}
