package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import io.javalin.http.Context;

public class UserController {
	
	private static final Logger loggy = Logger.getLogger(UserController.class);
	
	private EmployeeDAO emDao = new EmployeeDAOImpl();
	private ReportDAO reDao = new ReportDAOImpl();
	private Service service = new ServiceImpl(emDao,reDao);
	
	public UserController(Service service) {
		super();
		this.service = service;
	}
	
	Employee em = new Employee();
	List<Report> reportList = new ArrayList<>();

	//Get all reports for current user from DB. Reports will be displayed on current users home page.
	public Employee getAllReports(Context ctx) {
		ctx.sessionAttribute("user");
		
		em = ctx.cachedSessionAttribute("user");
		em = service.getEmployeeReports(em);
		
		loggy.info("Gathered current employee reports");
		ctx.res.setStatus(200);
		return em;
	}
	
	//Converts front-end input to a Report object. New report object is then stored in the DB. 
	public Employee addReport(Context ctx) {
    Report rep = new Report();
		em = ctx.cachedSessionAttribute("user");
		loggy.info("Initializing new report for user: "+em.getUsername());
		
		if (!ctx.formParam("amount").isEmpty()) {
			Float amount = Float.parseFloat(ctx.formParam("amount"));
			loggy.info("Report amount set to: "+amount);
			
			String description = ctx.formParam("description");
			loggy.info("Report description set to: "+description);
			
			String reportType = ctx.formParam("type");
			loggy.info("Report type set to: "+reportType);
		
			if(amount != null && !description.isEmpty() && !reportType.isEmpty()) {
				rep.setAmount(amount);
				rep.setDescription(description);
				rep.setReportType(reportType);
				rep.setEmployeeName(em.getUsername());
				
				if (service.createEmployeeReport(rep)) {
					ctx.res.setStatus(200);
					loggy.info("Report successfully added");
				}else {
					ctx.res.setStatus(400);
					loggy.info("Error adding report to DB");
					}
				}
			}
	
		return em;
	}

	//Get a list all reports from DB for manger view. 
	public List<Report> viewAllReports(Context ctx) {
		reportList = service.getAllReports();
		loggy.info("Returning report list to manager");
		return reportList;
	}

	//Changes the report type view based on manager input
	public void viewSelect(Context ctx) {
		String selector = ctx.formParam("flexRadioDefault");
		loggy.info("Manager changed view type to:"+selector);
		
		reportList = service.getReportsByType(selector);
	}

	//Uses manager input to change a single report status to 'Approved' and updates info in DB
	public void updateStatusApproved(Context ctx) {
		Report re = null;
		ObjectMapper om = new ObjectMapper();
		String reportJSONText = ctx.body();
		
		try {
			re = om.readValue(reportJSONText, Report.class);
			re.setApprovalStatus("Approved");
			
			boolean success = service.updateReportStatus(re);
		if(success) {
			loggy.info("Report status had been updated to 'Approved'");
			ctx.res.setStatus(200);
			
		}else {
			loggy.info("Report status update failed");
			ctx.res.setStatus(402);
		}
		
		} catch (JsonMappingException e) {
			loggy.info("updateStatusApproved Json Mapping Exception: "+e.getMessage());	
			ctx.res.setStatus(401);
		} catch (JsonProcessingException e) {
			loggy.info("updateStatusApproved Json Proccessing Exception: "+e.getMessage());	
			ctx.res.setStatus(500);
		}	
	}
	
	//Uses manager input to change a single report status to 'Rejected' and updates info in DB
	public void updateStatusRejected(Context ctx) {
		Report re = null;
		ObjectMapper om = new ObjectMapper();
		String reportJSONText = ctx.body();
		
		try {
			re = om.readValue(reportJSONText, Report.class);
			re.setApprovalStatus("Rejected");
			
			boolean success = service.updateReportStatus(re);
		if(success) {
			loggy.info("Report status had been updated to 'Rejected'");
			ctx.res.setStatus(200);
		}else {
			loggy.info("Report status update failed");
			ctx.res.setStatus(402);
		}
		
		} catch (JsonMappingException e) {
			loggy.info("updateStatusRejected Json Mapping Exception: "+e.getMessage());	
			ctx.res.setStatus(401);
		} catch (JsonProcessingException e) {
			loggy.info("updateStatusRejected Json Proccessing Exception: "+e.getMessage());	
			ctx.res.setStatus(500);
		}	
	}

}
