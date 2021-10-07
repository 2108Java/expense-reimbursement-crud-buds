package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Authentication.User;

import com.revature.models.Employee;
import com.revature.models.Report;

import io.javalin.http.Context;

public class UserController {
	
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
		
		
		ctx.res.setStatus(200);
		return em;
	}
	
	public Employee addReport(Context ctx) {
		
		System.out.println(ctx.formParam("amount"));
		System.out.println(ctx.formParam("description"));
		System.out.println(ctx.formParam("type"));
		
		return em;
	}

}
