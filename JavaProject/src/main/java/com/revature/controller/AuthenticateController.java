package com.revature.controller;

import java.io.IOException;

import com.revature.models.Employee;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import io.javalin.http.Context;

public class AuthenticateController {

	private EmployeeDAO emDao = new EmployeeDAOImpl();
	private ReportDAO reDao = new ReportDAOImpl();
	private Service service = new ServiceImpl(emDao,reDao);
	
	public AuthenticateController(Service service) {
		super();
		this.service = service;
	}
	
	
	public String authenticate(Context ctx) throws IOException {
		Employee em = new Employee();
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		em.setUsername(username);
		em.setPassword(password);
		
		System.out.println(ctx.formParam("username"));
		System.out.println(ctx.formParam("password"));
		String page = "";
	
		if(service.authenticateEmployee(em)){
			em = service.getEmployee(em);
			if(service.verifyManager(em)) {
				ctx.sessionAttribute("user", em);
				ctx.sessionAttribute("access","manager");
			
				ctx.res.sendRedirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com:8080/manager");
				
				
			}else{
				ctx.sessionAttribute("user", em);
			ctx.sessionAttribute("access","customer");
		
			ctx.res.sendRedirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com:8080/home");
			}

			
		
		}else {
			ctx.res.sendRedirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com:8080/");
			
		}
			
//		if(service.authenticate(ctx.queryParam(username))) What we would do in a full stack. 
		
		return page;
		
	}
}
