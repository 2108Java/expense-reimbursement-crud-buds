package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Report;
import com.revature.util.Communication;


public class ReportDAOImpl implements ReportDAO{
	Communication comm = new Communication();
	
	private static final Logger loggy = Logger.getLogger(ReportDAO.class);
	
	@Override
	public boolean insertReport(Report report) {
		boolean success = false;
		
		String sql = "insert into expense_reports (employee_id, expense_type, description,  amount) values ((select employee_id from employee where username = ? ),?,?,?)";
		try(Connection connection = comm.connection();){
			loggy.info("Successful connection to DB");
			
			PreparedStatement ps = connection.prepareStatement(sql);
	
			ps.setString(1, report.getEmployeeName());
			ps.setString(2, report.getReportType());
			ps.setString(3, report.getDescription());
			ps.setDouble(4, report.getAmount());
			
			ps.execute();
					
			success = true;
			
			loggy.info("Closing DB connection");
			connection.close();
			
		}catch(SQLException e) {
			success = false;
			loggy.info("insertReport SQL Exception: "+e.getMessage());		
		}
	return success;
	}

	@Override
	
	public List<Report> selectEmployeeReports(int employeeId) {
		
		List<Report> reportList = new ArrayList<Report>();
		
		try(Connection connection = comm.connection()){
			loggy.info("Successful connection to DB");
		
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
		
			loggy.info("Closing DB connection");
			connection.close();
			
		}catch(SQLException e) {
			loggy.info("selectEmployeeReports SQL Exception: "+e.getMessage());
		}
		
	return reportList;
	}

	@Override
	public List<Report> selectReportsByType(String reimbursementType) {
		
		List<Report> reportList = new ArrayList<Report>();
		
		try(Connection connection = comm.connection()){
			loggy.info("Successful connection to DB");
			
			String sql = "SELECT * FROM expense_reports LEFT JOIN employee ON expense_reports.employee_id = employee.employee_id WHERE approval_status = ? ORDER BY creation_time ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
		
			ps.setString(1 ,reimbursementType);
			ps.execute();
		
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				Report tempReport = new Report();
			
				tempReport.setReportId(rs.getInt("report_id"));
				tempReport.setAmount(rs.getInt("amount"));
				tempReport.setReportType(rs.getString("expense_type"));
				tempReport.setDescription(rs.getString("description"));
				tempReport.setApprovalStatus(rs.getString("approval_status"));
				tempReport.setEmployeeName(rs.getString("username"));
				tempReport.setTimestamp(rs.getString("creation_time"));
			
				reportList.add(tempReport);
			
			}
			loggy.info("Closing DB connection");
			connection.close();
			
		}catch(SQLException e) {
			loggy.info("selectReportsByType SQL Exception: "+e.getMessage());
		}
		
	return reportList;
	}

	@Override
	public List<Report>  selectAllReports() {
		
		List<Report> reportList = new ArrayList<Report>();
		
		try(Connection connection = comm.connection()){
			loggy.info("Successful connection to DB");
			
			String sql = "SELECT * FROM expense_reports LEFT JOIN employee ON expense_reports.employee_id = employee.employee_id ORDER BY creation_time ASC";
			PreparedStatement ps = connection.prepareStatement(sql);
		
			ps.execute();
		
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				Report tempReport = new Report();
			
				tempReport.setReportId(rs.getInt("report_id"));
				tempReport.setAmount(rs.getInt("amount"));
				tempReport.setReportType(rs.getString("expense_type"));
				tempReport.setDescription(rs.getString("description"));
				tempReport.setApprovalStatus(rs.getString("approval_status"));
				tempReport.setEmployeeName(rs.getString("username"));
				tempReport.setTimestamp(rs.getString("creation_time"));
			
				reportList.add(tempReport);
			
			}
			loggy.info("Closing DB connection");
			connection.close();
			
		}catch(SQLException e) {
			loggy.info("selectAllReports SQL Exception: "+e.getMessage());
		}
		
	return reportList;
	}


	@Override
	public boolean updateReport(Report report) {
		boolean success = false;
		int id = report.getReportId();
		String newStatus = report.getApprovalStatus();
		
		try(Connection connection = comm.connection()){
			loggy.info("Successful connection to DB");
			
			String sql = "UPDATE expense_reports SET approval_status = ? WHERE report_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1 ,newStatus);
			ps.setInt(2 ,id);
			ps.execute();
			
			success = true;
			
			loggy.info("Closing DB connection");
			connection.close();
				
		}catch(SQLException e) {
			loggy.info("updateReport SQL Exception: "+e.getMessage());
		}

	return success;
	}

}
