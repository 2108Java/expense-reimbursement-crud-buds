package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Authentication.User;

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
	List<Report> ReportList = new ArrayList<>();
	public Employee initalizeList() {
		
		
		
		ReportList.add(0, new Report(1, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(1, new Report(2, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(2, new Report(3, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(3, new Report(4, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(4, new Report(5, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(5, new Report(6, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(6, new Report(7, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		ReportList.add(7, new Report(8, 700,  "TRAVEL", "Plane Ticket", "2021-10-06 18:59:56", "Pending"));
		em.setUsername("Harley");
		em.setPassword("starfish");
		em.setReportList(ReportList);
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
		
		System.out.println(rep.toString());
		System.out.println(rep.getEmployeeName());
		
		if (service.insertEmployeeReport(rep)) {
			ctx.res.setStatus(200);
		}else {
			ctx.res.setStatus(400);
		}
		

		return em;
	}

}
