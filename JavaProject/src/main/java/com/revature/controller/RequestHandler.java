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
		
		app.get("/loginPage", 
				ctx -> 
		ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res)
				);
		
		
		app.get("/", ctx -> {
			ctx.req.getRequestDispatcher("Login.html").forward(ctx.req, ctx.res);
		}
			);
		
		
		app.post("/", ctx -> {
			
					ac.authenticate(ctx);
			
			});
		
		app.get("/home", ctx -> 
		{
			if(checkSession(ctx)) {
				ctx.req.getRequestDispatcher("EmployeePage.html").forward(ctx.req, ctx.res);
			}else {
				ctx.res.sendRedirect("http://localhost:8000/");
			}
		
		
		});
		
		
		
	}

}
