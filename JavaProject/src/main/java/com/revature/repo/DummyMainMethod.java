package com.revature.repo;

import com.revature.models.Employee;
import com.revature.models.Report;

public class DummyMainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeDAO database = new EmployeeDAOImpl();
		
		ReportDAO database2 = new ReportDAOImpl();
		
		Employee em = new Employee();
		Report report = new Report();
		em.setUsername("Harley");
		em.setPassword("starfish");
		
		if(database.selectEmployee(em)) {
			System.out.println("Logged in");
		}else {
			System.out.println("Failed Log in");
		}
		
		em.setUsername("Brandon");
		
		if(database.selectFinanceManager(em)) {
			System.out.println("Logged in");
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
