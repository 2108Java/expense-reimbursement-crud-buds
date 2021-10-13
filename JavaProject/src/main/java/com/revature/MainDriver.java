package com.revature;

import com.revature.controller.RequestHandler;
import com.revature.controller.UserController;
import com.revature.models.Employee;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;
import com.revature.service.Service;
import com.revature.service.ServiceImpl;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		
		 Javalin app = Javalin.create(config -> config.addStaticFiles(
					staticFiles ->
					{
						staticFiles.directory = "/public";
					}
					)).start(8000);
		 
		 RequestHandler.setupEndPoints(app);
	}

}
