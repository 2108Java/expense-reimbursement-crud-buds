package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.ReportDAO;

public class ServiceImpl implements Service{

	private EmployeeDAO emDao;
	private ReportDAO reDao;
	
	public ServiceImpl(EmployeeDAO emDao, ReportDAO reDao) {
		this.emDao = emDao;
		this.reDao = reDao;
	}
	
	//Authenticate:
	@Override
	public boolean verifyEmployeeExists(String username) {			
		return emDao.isEmployee(username); 
	}
	
	@Override
	public Employee getEmployee(Employee user) {
		return emDao.selectEmployeeByUsername(user);
	}
	
	@Override
	public boolean authenticateEmployee(Employee user){	
		
		boolean userExists = this.verifyEmployeeExists(user.getUsername());
		boolean authenticated = false;
		
		if(userExists) {
			//System.out.println("User Exists");
			Employee em = this.getEmployee(user);
			
			if(em.getPassword().equals(user.getPassword())) {
				authenticated = true;
				System.out.println("Username & Password Match");
			}else {
				System.out.println("Username & Password Dont Match");
			}
		}
		
		return authenticated;
	}

	@Override
	public boolean verifyManager(Employee user) {
		
		boolean isFinMan = false;
		
		if(user.isFinanceManager()) {
			isFinMan = true;
		}else {
			System.out.println("User is not a finance manager");
		}
		
		return isFinMan;
	}

	//Employee Services:
	@Override
	public Employee getEmployeeReports(Employee user) {
		
		List<Report> reportList = new ArrayList<>(reDao.selectEmployeeReports(user.getEmployeeID()));
		
		user.setReportList(reportList);
		
		return user;
	}

	@Override
	public boolean createEmployeeReport(Report newReport) {
		return reDao.insertReport(newReport);
	}


	//Manager Services:
	@Override
	public List<Report> getAllReports() {
		return reDao.selectAllReports();
	}

	@Override
	public List<Report> getReportsByType(String reimbursementType) {
		return reDao.selectReportsByType(reimbursementType);
	}

	@Override
	public boolean updateReportStatus(Report report) {
		return reDao.updateReport(report);
	}
	
}
