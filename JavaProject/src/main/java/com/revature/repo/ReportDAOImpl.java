package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.util.Communication;

public class ReportDAOImpl implements ReportDAO{
	Communication comm = new Communication();
	
	
	@Override
	public boolean insertReimbursement(Employee em, Report report) {
		boolean success = false;
		
		String sql = "insert into expense_reports (employee_id, expense_type, description,  amount) values ((select employee_id from employee where username = ? ),?,?,?)";
		try{
			Connection connection = comm.connection();
			
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
	
	public List<Report> selectEmployeeReimbursements(int employeeId) {
		
		List<Report> reportList = new ArrayList<Report>();
		
		try(Connection connection = comm.connection()){
		
		String sql = "SELECT * FROM expense_reports where employee_id = ? order by creation_time asc";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, employeeId);
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Report tempReport = new Report();
			
			tempReport.setReportId(rs.getInt("report_id"));
			tempReport.setAmount(rs.getInt("amount"));
			tempReport.setReportType(rs.getString("expense_type"));
			tempReport.setDescription(rs.getString("description"));
			tempReport.setApprovalStatus(rs.getString("approval_status"));
			tempReport.setTimestamp(rs.getString("creation_time"));
			
			reportList.add(tempReport);
			
		}
		connection.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	return reportList;
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
	public List<Report>  selectAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateReimbursement(Report report) {
		// TODO Auto-generated method stub
		return false;
	}

}
