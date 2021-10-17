package com.revature.controller;

import com.revature.models.Employee;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import java.io.IOException;

public class AuthenticateController {

	private static final Logger loggy = Logger.getLogger(AuthenticateController.class);
	
	private EmployeeDAO emDao = new EmployeeDAOImpl();
	private ReportDAO reDao = new ReportDAOImpl();
	private Service service = new ServiceImpl(emDao,reDao);
	
	public AuthenticateController(Service service) {
		super();
		this.service = service;
	}
	
	//Takes user input and checks if the credentials match in DB. 
	//Once User has been authenticated, the method will assign user sessions attributes and grant the authorized access level. 
	//Managers will be redirected to manager home page and regular employees will be redirected to employee home page.
	//Failed logins will be redirected to login page. 
	public String authenticate(Context ctx) throws IOException {
		Employee em = new Employee();
		
		loggy.info("Getting Username and Password from user...");
		String username = ctx.formParam("username");
		loggy.info("User input for username: "+ username);
		
		String password = ctx.formParam("password");
		loggy.info("User input for password: "+ username);
		
		em.setUsername(username);
		em.setPassword(password);
	
		String page = "";
	if(!username.isEmpty() && !password.isEmpty()) {
		loggy.info("Attempting to Authenticate");
		
		if(service.authenticateEmployee(em)){
			em = service.getEmployee(em);
			if(service.verifyManager(em)) {
				
				loggy.info("Setting 'user' session attribute");
				ctx.sessionAttribute("user", em);
				
				loggy.info("Setting access level to 'manager'");
				ctx.sessionAttribute("access","manager");
				
				loggy.info("Redirecting...");
				ctx.res.sendRedirect("http://localhost:8000/manager");
				
			}else{
				loggy.info("Setting 'user' session attribute");
				ctx.sessionAttribute("user", em);
				
				loggy.info("Setting access level to 'manager'");
				ctx.sessionAttribute("access","customer");
				
				loggy.info("Redirecting...");
				ctx.res.sendRedirect("http://localhost:8000/home");
			}

			
		
		}else {
			loggy.info("Redirecting...");
			ctx.res.sendRedirect("http://localhost:8000/");
			
		}
	}else {
		loggy.info("Username or Password is empty");
		loggy.info("Redirecting...");
		ctx.res.sendRedirect("http://localhost:8000/");
	}

		return page;
		
	}
}
