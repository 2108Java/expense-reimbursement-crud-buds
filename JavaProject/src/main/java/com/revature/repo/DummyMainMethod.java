package com.revature.repo;

import com.revature.models.Employee;

public class DummyMainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeDAO database = new EmployeeDAOImpl();
		
		Employee em = new Employee();
		em.setUsername("Harley");
		em.setPassword("starfish");
		
		if(database.selectEmployee(em)) {
			System.out.println("Logged in");
		}else {
			System.out.println("Failed Log in");
		}
		
	}

}
