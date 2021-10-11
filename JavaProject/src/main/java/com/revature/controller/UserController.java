package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Authentication.User;

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
	
	private EmployeeDAO emDao = new EmployeeDAOImpl();
	private ReportDAO reDao = new ReportDAOImpl();
	private Service service = new ServiceImpl(emDao,reDao);
	
	public UserController(Service service) {
		super();
		this.service = service;
	}
	
	Employee em = new Employee();
	List<Report> reportList = new ArrayList<>();
	public Employee initalizeList() {
		
		
		
		reportList.add(0, new Report(1, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(1, new Report(2, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(2, new Report(3, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(3, new Report(4, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(4, new Report(5, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(5, new Report(6, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(6, new Report(7, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		reportList.add(7, new Report(8, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		em.setUsername("Harley");
		em.setPassword("starfish");
		em.setReportList(reportList);
		return em;
	}

	public Employee getAllReports(Context ctx) {
		ctx.sessionAttribute("user");
		
		em = ctx.cachedSessionAttribute("user");
		em = service.getEmployeeReports(em);
		
		//em = (Employee) JSON.parse(sess);
		ctx.res.setStatus(200);
		return em;
	}
	
	public Employee addReport(Context ctx) {
		em = ctx.cachedSessionAttribute("user");
		Report rep = new Report();
		
		//System.out.println(ctx.formParam("amount"));
		//System.out.println(ctx.formParam("description"));
		//System.out.println(ctx.formParam("type"));
		//ReportList.add(rep);
		
		rep.setAmount(Float.parseFloat(ctx.formParam("amount")));
		rep.setDescription(ctx.formParam("description"));
		rep.setReportType(ctx.formParam("type"));
		rep.setEmployeeName(em.getUsername());
		
		//System.out.println(rep.toString());
		//System.out.println(rep.getEmployeeName());
		
		if (service.createEmployeeReport(rep)) {
			ctx.res.setStatus(200);
		}else {
			ctx.res.setStatus(400);
		}
		

		return em;
	}
	
	public List<Report> viewAllReports(Context ctx) {
		
		
		reportList = service.getAllReports();
		
		return reportList;
	}

	public void viewSelect(Context ctx) {
		
		String selector = ctx.formParam("flexRadioDefault");
		
		System.out.println(selector);
		
		
		reportList = service.getReportsByType(selector);
		
		System.out.println(reportList);
		
		
	}

	public void updateStatusApproved(Context ctx) {
		// TODO Auto-generated method stub
		Report re = null;
		
		ObjectMapper om = new ObjectMapper();
		
		String reportJSONText = ctx.body();
		
		try {
			re = om.readValue(reportJSONText, Report.class);
			re.setApprovalStatus("Approved");
			System.out.println(re);
			boolean success = service.updateReportStatus(re);
		if(success) {
			ctx.res.setStatus(200);
			
		}else {
			ctx.res.setStatus(402);
		}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ctx.res.setStatus(401);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			ctx.res.setStatus(500);
			e.printStackTrace();
		}	
	}
	
	public void updateStatusRejected(Context ctx) {
		// TODO Auto-generated method stub
		Report re = null;
		
		ObjectMapper om = new ObjectMapper();
		
		String reportJSONText = ctx.body();
		
		System.out.println(om);
		System.out.println(reportJSONText);
		
		try {
			re = om.readValue(reportJSONText, Report.class);
			System.out.println(re);
//			re.setApprovalStatus("Rejected");
			
//			System.out.println(re);
			
//			boolean success = service.updateReportStatus(re);
		if(true) {
			ctx.res.setStatus(200);
			
//		}else {
//			ctx.res.setStatus(402);
		}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ctx.res.setStatus(401);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			ctx.res.setStatus(500);
			e.printStackTrace();
		}	
	}

}
