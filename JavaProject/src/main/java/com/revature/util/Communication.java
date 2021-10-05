package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Communication {
	
	private static final String SERVER = "database-1.cgmzmgxjk6mv.us-east-2.rds.amazonaws.com";
	private static final String URL = "jdbc:postgresql://" + SERVER + "/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "p4ssw0rd";
	
	
	public Connection connection() throws SQLException {
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	

}
