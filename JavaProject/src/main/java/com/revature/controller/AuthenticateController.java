package com.revature.controller;

import io.javalin.http.Context;

public class AuthenticateController {

	//What should happen if I fail to login?
	// 	If it fails I should be renavigated back to the login page
	
	//IF it succeeds what should happen?
	//  We will go the planetsLanding page. 
	public String authenticate(Context ctx) {
		
		//My information is all stored within the request! 
		//ctx will have the information I need. 
		
		/*
		 * The browser can send information on the URL e.g. Planets/1
		 * Query Parameters (Through the form) e.g. login?username=Bob&pass=p4ss
		 * JSON: infomration can be sent via the request body, in the format of JSON
		 */
		
		System.out.println(ctx.queryParam("username"));
		System.out.println(ctx.queryParam("password"));
		String page = "";
		if(ctx.queryParam("username").equals("user") 
				&& ctx.queryParam("password").equals("p4ss")){
					page = "PlanetsLandingPage.html";
				}else {
					page = "failedLogin.html";
				}
		
//		if(service.authenticate(ctx.queryParam(username))) What we would do in a full stack. 
		
		return page;
		
	}
}
