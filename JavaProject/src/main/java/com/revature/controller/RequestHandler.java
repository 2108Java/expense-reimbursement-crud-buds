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
		if(ctx.sessionAttribute("user") != null) {
			return true;
		}else {
			return false;
		}
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
				ctx.res.sendRedirect("http://localhost:8000/");
			}
		});
		
		app.get("/reports", ctx -> {
			
//			if(checkSession(ctx)) {
				ctx.json(uc.getAllReports(ctx));
//			}
			 //localhost:8000/planets is going to return planets
		});
		
		app.post("/addReport", ctx -> {
			uc.addReport(ctx);
			ctx.redirect("http://localhost:8000/home");
		});
		
		app.post("/logOut", ctx -> {
			ctx.consumeSessionAttribute("user");
			ctx.consumeSessionAttribute("access");
			ctx.redirect("http://localhost:8000/");
		});
	}

}
