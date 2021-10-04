package com.revature.repo;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Report;

public interface ReportDAO {
	
	
	//CREATE
	
	public boolean insertReimbursement(Employee em, Report report);
	
	
	
	
	
	//READ
	
	public List<Report> selectAllReimbursements();
	
	
	public List<Report> selectApprovedReimbursements();
	
	public List<Report> selectRejectedReimbursements();
	
	public List<Report> selectPendingReimbursements();
	
	public List<Report> selectEmployeeReimbursements();
	
	
	//UPDATE
	
	public boolean updateReimbursement(Report report);
	
	
	
	
	//DELETE

}
