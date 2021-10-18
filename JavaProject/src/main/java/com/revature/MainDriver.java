package com.revature;

import com.revature.controller.RequestHandler;

import org.apache.log4j.Logger;

import io.javalin.Javalin;

public class MainDriver {

	private static final Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		 loggy.info("Running Main Driver...");
		 loggy.info("Initializing Javalin...");
		
		 Javalin app = Javalin.create(config -> config.addStaticFiles(
					staticFiles ->
					{
						staticFiles.directory = "/";
					}
					)).start(8000);
		 
		 loggy.info("Setting Up Endpoints...");
		 RequestHandler.setupEndPoints(app);
	}

}
