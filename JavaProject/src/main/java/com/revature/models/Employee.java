package com.revature.models;

public class Employee {
	
	//Attributes
	private int employeeID;
	private String username;
	private String password;
	private boolean isFinanceManager;
	//private String email;
	
	//Constructors
	
	public Employee() {
		super();
	}

	
	//Getters and Setters
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isFinanceManager() {
		return isFinanceManager;
	}


	public void setFinanceManager(boolean isFinanceManager) {
		this.isFinanceManager = isFinanceManager;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
}

