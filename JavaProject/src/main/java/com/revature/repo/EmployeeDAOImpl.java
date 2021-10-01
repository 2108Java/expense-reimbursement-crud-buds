package com.revature.repo;

import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.util.Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	Communication conn = new Communication();
	
	@Override
	public boolean selectEmployee(String username, String password) {
		boolean success = false;
		Employee em = new Employee();
		String sql = "SELECT * FROM employee where username = ? and password = ?";
		try{
			Connection connection = conn.connection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("username")!=null) {
					em.setEmployeeID(rs.getInt("id"));
					success = true;
				}
			}
			
		
		}catch(SQLException e) {
			success = false;
			e.printStackTrace();		
		}
	return success;
	}

}
