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
	public Employee getEmployee(Employee user) {
		return emDao.selectEmployeeByUsername(user);
	}
	
	@Override
	public boolean authenticateEmployee(Employee user){	
		
		boolean userExists = this.verifyEmployeeExists(user.getUsername());
		boolean authenticated = false;
		
		if(userExists) {
			//System.out.println("User Exists");
			Employee em = this.getEmployee(user);
			
			if(em.getPassword().equals(user.getPassword())) {
				authenticated = true;
				System.out.println("Username & Password Match");
			}else {
				System.out.println("Username & Password Dont Match");
			}
		}
		
		return authenticated;
	}

	@Override
	public boolean verifyManager(Employee user) {
		boolean isFinMan = false;
		
		if(user.isFinanceManager()) {
			isFinMan = true;
		}else {
			System.out.println("User is not a finance manager");
		}
		
		return isFinMan;
	}
	
	
}
