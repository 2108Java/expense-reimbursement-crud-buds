package com.revature.service;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Report;

public interface Service {
	
	
	//Authenticate:
	
	//verify if the user exists
	public boolean verifyEmployeeExists(String username);
	
	//returns true if employee is a finance manger.
	public boolean verifyManager(Employee user);
	
	//returns the Employee object associated with the username
	public Employee getEmployee(Employee user);
	
	//authenticate the user
	public boolean authenticateEmployee(Employee user);
	
	
	//Employee Services:
	
	//get all reports associated with user. Report List is added to the employee object
	public Employee getEmployeeReports(Employee user);
	
	//New report is added to the DB and will return true if successful
	public boolean createEmployeeReport(Report newReport);
	
	
	//Manager Services:
	
	//Returns a list of all reports from DB
	public List<Report> getAllReports();
	
	//Returns a list of all reports with the given type from DB
	public List<Report> getReportsByType(String reimbursementType);
	
	//Updates the report status from pending to "Approved" or "Denied" in DB. Returns true if successful
	public boolean updateReportStatus(Report report);
	
}
