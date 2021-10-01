package com.revature.repo;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	Communication conn = new Communication();
	
	@Override
	public boolean selectEmployee(String username, String password) {
		boolean success = false;
		String sql = "SELECT * FROM employee where username = ? and password = ?";
		try{
			Connection connection = conn.connection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, username);
			ps.setString(2, password);
			
			ps.execute();
			success = true;
		
		}catch(SQLException e) {
			success = false;
			e.printStackTrace();		
		}
	return success;
	}

}
