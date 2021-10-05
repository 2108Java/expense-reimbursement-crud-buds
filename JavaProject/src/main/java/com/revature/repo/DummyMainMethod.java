package com.revature.repo;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

public class DummyMainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeDAO database = new EmployeeDAOImpl();
		
		ReportDAO database2 = new ReportDAOImpl();
		
		Service service = new ServiceImpl(database);
		
		String username = "Harley";
		String password = "starfish";
		Employee em = service.getEmployeeByUsername(username);
		
		Report report = new Report();
	
		
		if(service.authenticateEmployee(username,password)) {
			System.out.println("Logged in Harley");
		}else {
			System.out.println("Failed Log in");
		}
		
		em.setUsername("Brandon");
		
		if(service.verifyManager(em.getUsername())) {
			System.out.println("Logged in Brandon");
		}else {
			System.out.println("Failed Log in");
		}
		
		report.setAmount(7000);
		report.setDescription("Plane ticket for round trip to conference in Japan");
		report.setReportType("TRAVEL");
		
		if(database2.insertReimbursement(em, report)) {
			System.out.println("inserted into database");
		}else {
			System.out.println("not inserted into database");
		}
		
	}

}
