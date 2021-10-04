package com.revature;

import com.revature.controller.RequestHandler;


import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		 Javalin app = Javalin.create(config -> config.addStaticFiles(
					staticFiles ->
					{
						staticFiles.directory = "/";
					}
					)).start(8000);
		 
		 RequestHandler.setupEndPoints(app);
	}

}
