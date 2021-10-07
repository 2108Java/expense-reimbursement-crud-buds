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
		
		Service service = new ServiceImpl(database,database2);
		
		String username = "Harley";
		String password = "starfish";
		
		Employee em = new Employee();
		em.setUsername(username);
		em.setPassword(password);
		
		Report report = new Report();
	
		
		if(service.authenticateEmployee(em)) {
			System.out.println("Logged in Harley");
		}else {
			System.out.println("Failed Log in");
		}
		
		em.setUsername("Brandon");
		em.setEmployeeID(3);
		em.setFinanceManager(true);
		
		if(service.verifyManager(em)) {
			System.out.println("Logged in Brandon");
		}else {
			System.out.println("Failed Log in");
		}
		
		report.setAmount(500);
		report.setDescription("Car rental for during last business trip");
		report.setReportType("OTHER");
		
		//if(database2.insertReimbursement(em, report)) {
		//	System.out.println("inserted into database");
		//}else {
		//	System.out.println("not inserted into database");
		//}
		
		em = service.getEmployeeReports(em);
		
		System.out.println(em.getUsername()+"'s Report List : " + em.getReportList().toString());
		
	}

}
