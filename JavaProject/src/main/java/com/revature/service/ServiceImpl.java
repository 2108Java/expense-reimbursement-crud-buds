package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.ReportDAO;

public class ServiceImpl implements Service{

	private EmployeeDAO emDao;
	private ReportDAO reDao;
	
	private static final Logger loggy = Logger.getLogger(Service.class);
	
	public ServiceImpl(EmployeeDAO emDao, ReportDAO reDao) {
		this.emDao = emDao;
		this.reDao = reDao;
	}
	
	//Authenticate:
	@Override
	public boolean verifyEmployeeExists(String username) {			
		boolean userExists = false;
		loggy.info("Verifying that user exists : "+ username);
		
		if(emDao.isEmployee(username)) {
			loggy.info("User: "+ username+" exists");
			userExists = true;
		}else {
			loggy.info("User: "+ username+" does not exists");
		}
		return userExists; 
	}
	
	@Override
	public Employee getEmployee(Employee user) {
		Employee employee = new Employee();
		loggy.info("Retrieving Employee information for : "+ user.getUsername());
		
		employee = emDao.selectEmployeeByUsername(user);
		return employee;
	}
	
	@Override
	public boolean authenticateEmployee(Employee user){	
		
		boolean userExists = this.verifyEmployeeExists(user.getUsername());
		boolean authenticated = false;
		
		if(userExists) {
			Employee em = this.getEmployee(user);
			
			if(em.getPassword().equals(user.getPassword())) {
				authenticated = true;
				loggy.info("Authenticated User: "+ user.getUsername());
			}else {
				loggy.info("User Failed To Authenicate: "+ user.getUsername());
			}
		}
		
		return authenticated;
	}

	@Override
	public boolean verifyManager(Employee user) {
		
		boolean isFinMan = false;
		loggy.info("Checking if "+user.getUsername()+" is a Finance Manager");
		
		if(user.isFinanceManager()) {
			isFinMan = true;
			loggy.info("Authorized user as Manager: "+ user.getUsername());
		}else {
			loggy.info("Failed to authorize as Manager: "+ user.getUsername());
		}
		
		return isFinMan;
	}

	//Employee Services:
	@Override
	public Employee getEmployeeReports(Employee user) {
		loggy.info("Retrieving list of Reports for : "+ user.getUsername());
		List<Report> reportList = new ArrayList<>(reDao.selectEmployeeReports(user.getEmployeeID()));
		
		loggy.info("Appending report list to Employee object");
		user.setReportList(reportList);
		return user;
	}

	@Override
	public boolean createEmployeeReport(Report newReport) {
		loggy.info("Creating new expense report for current user in database");
		loggy.info(newReport.toString());
		boolean success = false;
		if(reDao.insertReport(newReport)) {
			success = true;
		}
		return success;
	}


	//Manager Services:
	@Override
	public List<Report> getAllReports() {
		loggy.info("Retrieving list of all reports for finance manager view");
		return reDao.selectAllReports();
	}

	@Override
	public List<Report> getReportsByType(String reimbursementType) {
		loggy.info("Retrieving list of all "+reimbursementType+ " reports for finance manager view");
		return reDao.selectReportsByType(reimbursementType);
	}

	@Override
	public boolean updateReportStatus(Report report) {
		loggy.info("Updating status of Report ID: "+report.getReportId()+" from 'PENDING' to '"+report.getApprovalStatus()+"'");
		return reDao.updateReport(report);
	}
	
}
