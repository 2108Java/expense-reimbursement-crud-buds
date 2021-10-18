package com.revature.controller;

import org.apache.log4j.Logger;

import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class RequestHandler {
	
	private static final Logger loggy = Logger.getLogger(RequestHandler.class);
	
	//Checks user session attribute and returns true if user has successfully logged in and is in an active session.
	public static boolean checkSession(Context ctx) {
		boolean success = false;
		loggy.info("Checking user session...");
		if(ctx.sessionAttribute("access") != null) {
			loggy.info("Session attribute exists...");
			if(ctx.sessionAttribute("access").equals("customer") || ctx.sessionAttribute("access").equals("manager")){
				loggy.info("Passed checkSession");
				success = true;
			}
		
		}else {
			loggy.info("Session attribute does not exist");
			return success;
		}
		return success;
	}
	
	//Checks current session for manager access
	public static boolean checkManagerSession(Context ctx) {
		boolean success = false;
		loggy.info("Checking manager session...");
		if(ctx.sessionAttribute("access") != null) {
			loggy.info("Session attribute exists...");
			if(ctx.sessionAttribute("access").equals("manager")){
				loggy.info("Passed checkManagerSession");
				success = true;
			}
		}else {
			loggy.info("Session attribute does not exist");
			return success;
		}
		return success;
	}
	
	//Setup End points for expense report application  
	public static void setupEndPoints(Javalin app) {
		EmployeeDAO emDao = new EmployeeDAOImpl();
		ReportDAO reDao = new ReportDAOImpl();
		Service service = new ServiceImpl(emDao,reDao);
		
		AuthenticateController ac = new AuthenticateController(service);
		UserController uc = new UserController(service);
		
	
		//The first thing the user is going to see is the login page
		app.get("/", ctx -> {
			
			loggy.info("Forwarding to /loginPage");
			ctx.req.getRequestDispatcher("/loginPage").forward(ctx.req, ctx.res);
			
		});
		
		app.get("/loginPage", ctx -> {
			
			loggy.info("Forwarding to Login.html");
			ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res);
		});
		
		//Calls Authenticate controller when user inputs credentials and clicks submit
		app.post("/authenticate", ctx -> {
			
			loggy.info("User attempting to authenticate...");
			ac.authenticate(ctx);
			
		});
		
		//Go to employee home page once user authenticates successfully
		app.get("/home", ctx -> {
			
			if(checkSession(ctx)) {
				loggy.info("Forwarding to EmployeePage.html");
				ctx.req.getRequestDispatcher("EmployeePage.html").forward(ctx.req, ctx.res);
			}else {

				System.out.println("Login Failed");
				ctx.res.sendRedirect("http://localhost:8000/");
			}
		});
		
		//Go to Finance manager home page once user authenticates successfully
		app.get("/manager", ctx -> {
			
			if(checkManagerSession(ctx)) {
				loggy.info("Forwarding to FinanceManager.html");
				ctx.req.getRequestDispatcher("FinanceManager.html").forward(ctx.req, ctx.res);
			}else if(checkSession(ctx)) {
				loggy.info("Redirecting to Employee home...");
				ctx.res.sendRedirect("http://localhost:8000/home");
			}else {

        loggy.info("Redirecting...");
				ctx.res.sendRedirect("http://localhost:8000/");
			}
			
		});
		
		//Get report data for employee home page 
		app.get("/reports", ctx -> {
			
			if(checkSession(ctx)) {
				ctx.json(uc.getAllReports(ctx));
				loggy.info("Converting to json object");
			}
		});
		
		//Called when employee attempts to submit a new expense report 
		app.post("/addReport", ctx -> {
			if(checkSession(ctx)) {
				loggy.info("Attempting to add report...");
				uc.addReport(ctx);

				loggy.info("Refreshing page");
				ctx.redirect("http://localhost:8000/home");

			}

			
		});
		
		//Called when manger logs in or changes view to 'All' reports
		app.get("/viewReport", ctx -> {
			
			if(checkManagerSession(ctx)) {
				ctx.json(uc.viewAllReports(ctx));
				loggy.info("Converting to json object");
			}
		
		});
		
		//Used to update the page view. Called when manager changes the current view type and clicks submit.
		app.put("/manager", ctx -> {
		
			if(checkManagerSession(ctx)) {
				uc.viewSelect(ctx);
				loggy.info("Manager view updated");
			}
			
		});
		
		//Used to update report status. Called when manager clicks the approved button for a specific report. 
		app.put("/managerApproved/{id}", ctx ->{
			if(checkManagerSession(ctx)) {
				uc.updateStatusApproved(ctx);
			}
		
		});
		
		//Used to update report status. Called when manager clicks the denied button for a specific report. 
		app.put("/managerRejected/{id}", ctx ->{
			if(checkManagerSession(ctx)) {
				uc.updateStatusRejected(ctx);	
			}
	
		});
		
		//Consumes session attributes and redirects to login page. Called when user clicks the logout button
		app.post("/logOut", ctx -> {
			loggy.info("Consuming session data");
			ctx.consumeSessionAttribute("user");
			ctx.consumeSessionAttribute("access");

			loggy.info("Redirecting...");
			ctx.redirect("http://localhost:8000/");

		});
		
		
	}

}
