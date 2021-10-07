package com.revature;

import com.revature.controller.RequestHandler;
import com.revature.controller.UserController;
import com.revature.models.Employee;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		UserController uc = new UserController();
		
		
		
		
		 Javalin app = Javalin.create(config -> config.addStaticFiles(
					staticFiles ->
					{
						staticFiles.directory = "/";
					}
					)).start(8000);
		 
		 RequestHandler.setupEndPoints(app);
	}

}
