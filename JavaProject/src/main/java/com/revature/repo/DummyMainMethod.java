package com.revature.repo;

public class DummyMainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeDAO database = new EmployeeDAOImpl();
		
		if(database.selectEmployee("Harley", "starfish")) {
			System.out.println("Logged in");
		}else {
			System.out.println("Failed Log in");
		}
		
	}

}
