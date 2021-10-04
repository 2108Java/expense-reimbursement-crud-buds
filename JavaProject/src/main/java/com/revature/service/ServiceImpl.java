package com.revature.service;

import com.revature.models.Employee;
import com.revature.repo.EmployeeDAO;

public class ServiceImpl implements Service{

	private EmployeeDAO empDao;
	
	@Override
	public boolean verify(String username) {
		return false;
	}
	
	@Override
	public boolean authenticate(String username, String password){
		return false;
	}

	@Override
	public boolean verifyManager(String username) {
	
		return false;
	}

	@Override
	public Employee getUserByUsername(String username) {
		return null;
	}
	
	
	
}
