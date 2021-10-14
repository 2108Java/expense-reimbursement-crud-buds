package com.revature.controller;

import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class RequestHandler {
	
	public static boolean checkSession(Context ctx) {
		boolean success = false;
		if(ctx.sessionAttribute("access") != null) {
			if(ctx.sessionAttribute("access").equals("customer") 
					|| ctx.sessionAttribute("access").equals("manager")){
						success = true;
			}
		
		}else {
			return success;
		}
		return success;
	}
	
	public static boolean checkManagerSession(Context ctx) {
		boolean success = false;
		if(ctx.sessionAttribute("access") != null) {
			if(ctx.sessionAttribute("access").equals("manager")){
						success = true;
			}
		
		}else {
			return success;
		}
		return success;
	}
	public static void setupEndPoints(Javalin app) {
		EmployeeDAO emDao = new EmployeeDAOImpl();
		ReportDAO reDao = new ReportDAOImpl();
		Service service = new ServiceImpl(emDao,reDao);
		
		AuthenticateController ac = new AuthenticateController(service);
		UserController uc = new UserController(service);
		//uc.initalizeList();
		
	
		//The first thing the user is going to see is the login page!
		app.get("/", ctx -> {
			
			ctx.req.getRequestDispatcher("/loginPage").forward(ctx.req, ctx.res);
			
		});
		
		app.get("/loginPage", ctx -> {
			
			ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res);
		});
		
		app.post("/authenticate", ctx -> {
			
			ac.authenticate(ctx);
			
		});
		
		app.get("/home", ctx -> {
			if(checkSession(ctx)) {
				ctx.req.getRequestDispatcher("EmployeePage.html").forward(ctx.req, ctx.res);
			}else {
				System.out.println("Login Failed");
				ctx.res.sendRedirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com/");
			}
		});
		
		
		app.get("/manager", ctx -> {
			
			if(checkManagerSession(ctx)) {
				ctx.req.getRequestDispatcher("FinanceManager.html").forward(ctx.req, ctx.res);
			}else {
				ctx.res.sendRedirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com/");
			}
			
		});
		
		
		app.get("/reports", ctx -> {
			
			if(checkSession(ctx)) {
				ctx.json(uc.getAllReports(ctx));
			}
//			 localhost:8000/planets is going to return planets
		});
	
		app.post("/addReport", ctx -> {
			if(checkSession(ctx)) {
				uc.addReport(ctx);
			ctx.redirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com/");
			}
			
		});
		
		app.get("/viewReport", ctx -> {
			
			if(checkManagerSession(ctx)) {
				ctx.json(uc.viewAllReports(ctx));
			}
		
		});
		
		
		app.put("/manager", ctx -> {
		
			if(checkManagerSession(ctx)) {
				uc.viewSelect(ctx);
			}
			
//			ctx.redirect("http://localhost:8000/manager");
		});
		
		app.put("/managerApproved/{id}", ctx ->{
			if(checkManagerSession(ctx)) {
				uc.updateStatusApproved(ctx);
			}
		
		});
		
		app.put("/managerRejected/{id}", ctx ->{
			if(checkManagerSession(ctx)) {
				uc.updateStatusRejected(ctx);	
			}
			System.out.println("Rejected");
	
		});
		app.post("/logOut", ctx -> {
			ctx.consumeSessionAttribute("user");
			ctx.consumeSessionAttribute("access");
			ctx.redirect("http://ec2-52-15-202-41.us-east-2.compute.amazonaws.com/");
		});
		
		
	}

}
