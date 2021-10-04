package com.revature.controller;

import io.javalin.http.Context;

public class AuthenticateController {

	public String authenticate(Context ctx) {
		

		
		System.out.println(ctx.formParam("username"));
		System.out.println(ctx.formParam("password"));
		String page = "";
		if(ctx.formParam("username").equals("user") 
				&& ctx.formParam("password").equals("p4ss")){
					page = "EmployeePage.html";
				}else {
					page = "Login.html";
				}
		
//		if(service.authenticate(ctx.queryParam(username))) What we would do in a full stack. 
		
		return page;
		
	}
}
