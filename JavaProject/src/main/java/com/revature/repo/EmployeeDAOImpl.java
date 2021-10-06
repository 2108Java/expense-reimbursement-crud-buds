package com.revature.repo;

import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.util.Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	Communication comm = new Communication();
	//UPDATE
	//stretch goal email 
	
	//CREATE
	//stretch goal New User
	
	//DELETE
	//Not needed here 
	
	//READ
	@Override
	public Employee selectEmployeeByUsername(Employee user){
		
		Employee em = new Employee();
		
		try(Connection connection = comm.connection()){
			
			String sql = "SELECT * FROM employee where username = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, user.getUsername());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
		
			em.setUsername(rs.getString("username"));
			em.setPassword(rs.getString("password"));
			em.setFinanceManager(rs.getBoolean("is_finance_manager"));
				
			connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();		
		}
	return em;
	}

	@Override
	public boolean isEmployee(String username) {
		
		boolean isEm = false;
		
		try(Connection connection = comm.connection()){
			
			String sql = "SELECT * FROM employee where username = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
				
			if (rs.getString("username").equals(username)) {
					isEm = true;
				}else {
					System.out.println("User Does Not Exist");
			}	
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();		
		}
	return isEm;
	}
	
}
