package com.revature.repo;

import java.util.List;

import com.revature.models.Report;

public interface ReportDAO {
	
	
	//CREATE
	
	public boolean insertReport(Report report);
	
	//READ
	
	public List<Report> selectAllReports();
	
	public List<Report> selectReportsByType(String reimbursementType);
	
	public List<Report> selectEmployeeReports(int employeeId);
	
	
	//UPDATE
	
	public boolean updateReport(Report report);
	
	//DELETE

}
