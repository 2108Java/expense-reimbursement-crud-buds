package com.revature.repo;

import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.util.Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	Communication conn = new Communication();
	//UPDATE
	//stretch goal email 
	
	//CREATE
	//stretch goal New User
	
	//DELETE
	//Not needed here 
	
	//READ
	@Override
	public boolean selectEmployee(Employee em) {
		boolean success = false;
		
		String sql = "SELECT * FROM employee where username = ? and password = ?";
		try{
			Connection connection = conn.connection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, em.getUsername());
			ps.setString(2, em.getPassword());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("username")!=null) {
					
					success = true;
				}
			}		
		}catch(SQLException e) {
			success = false;
			e.printStackTrace();		
		}
	return success;
	}

	@Override
	public boolean selectFinanceManager(Employee em) {
		boolean success = false;
		String sql = "SELECT * FROM employee where username = ? and password = ? and is_finance_manager = ?";
		try{
			Connection connection = conn.connection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, em.getUsername());
			ps.setString(2, em.getPassword());
			ps.setBoolean(3, true);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("username")!=null) {
					
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
