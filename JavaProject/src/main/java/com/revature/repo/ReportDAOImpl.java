package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.util.Communication;

public class ReportDAOImpl implements ReportDAO{
	Communication conn = new Communication();
	
	
	@Override
	public boolean insertReimbursement(Employee em, Report report) {
		boolean success = false;
		
		String sql = "insert into expense_reports (employee_id, expense_type, description,  amount) values ((select employee_id from employee where username = ? ),?,?,?)";
		try{
			Connection connection = conn.connection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, em.getUsername());
			ps.setString(2, report.getReportType());
			ps.setString(3, report.getDescription());
			ps.setDouble(4, report.getAmount());
			
			ps.execute();
			
					
					success = true;
					
		}catch(SQLException e) {
			success = false;
			e.printStackTrace();		
		}
	return success;
	}

	@Override
	public List<Report> selectAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> selectApprovedReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> selectRejectedReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> selectPendingReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> selectEmployeeReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateReimbursement(Report report) {
		// TODO Auto-generated method stub
		return false;
	}

}
