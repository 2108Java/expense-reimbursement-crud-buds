package com.revature.repo;

import com.revature.models.Employee;

public interface EmployeeDAO {
	
	public boolean selectEmployee(Employee em);
	
	public boolean selectFinanceManager(Employee em);

}
