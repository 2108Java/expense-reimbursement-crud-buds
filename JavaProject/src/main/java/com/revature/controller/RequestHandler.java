package com.revature.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		AuthenticateController ac = new AuthenticateController();
		UserController uc = new UserController();
//		uc.initalizeList();
		
		app.get("/loginPage", 
				ctx -> 
		ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res)
				);
		
		
		app.get("/", ctx -> {
			ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res);
		}
			);
		
		
		app.post("/authenticate", ctx -> {
			
					ac.authenticate(ctx);
			
			});
		
		app.get("/home", ctx -> 
		{
//			if(checkSession(ctx)) {
				ctx.req.getRequestDispatcher("EmployeePage.html").forward(ctx.req, ctx.res);
//			}else {
//				ctx.res.sendRedirect("http://localhost:8000/");
//			}
		
		
		});
		
		app.get("/reports", ctx -> {
			
//			if(checkSession(ctx)) {
				ctx.json(uc.getAllReports(ctx));
//			}
			 //localhost:8000/planets is going to return planets
		});
		
		app.post("/addReport", ctx ->{
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
