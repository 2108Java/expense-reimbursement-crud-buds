package com.revature.repo;

import java.util.List;

import com.revature.models.Report;

public interface ReportDAO {
	
	
	//CREATE
	
	//Create new report object in reports table, returns true if successful. 
	public boolean insertReport(Report report);
	
	//READ
	
	//Returns a list of all report objects in DB. 
	public List<Report> selectAllReports();
	
	//Returns a list of all reports by type. Type is identified by a string.
	public List<Report> selectReportsByType(String reimbursementType);
	
	//Returns a list of all reports associated with specific employee ID. 
	public List<Report> selectEmployeeReports(int employeeId);
	
	
	//UPDATE
	
	//Used to update the status of a report from Pending to approved/denied and returns true if successful. Report argument contains report ID and new status. 
	public boolean updateReport(Report report);
	
	//DELETE

}
