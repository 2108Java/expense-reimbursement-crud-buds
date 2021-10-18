package com.revature.service;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.models.Employee;
import com.revature.models.Report;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;
import com.revature.repo.ReportDAO;
import com.revature.repo.ReportDAOImpl;

public class TestServiceImpl {
	
	@Mock
	private static ReportDAO reDAO;
	@Mock
	private static EmployeeDAO emDAO;
	private static Report newReport = new Report(1, 60, "travel", "gobletly gook",
													"pending", "jeff");
	private static Employee user = new Employee("Brandon","starfish");
	private static Employee employee = new Employee("Harley","starfish");
	private static Employee employee2 = new Employee();
	
	
	
	private Service serv = new ServiceImpl(emDAO, reDAO);
	
	@BeforeClass
	public static void fakeMyDAO() {
		reDAO = Mockito.mock(ReportDAOImpl.class);
		
		emDAO = Mockito.mock(EmployeeDAOImpl.class);

		
		
		Mockito.when(reDAO.updateReport(newReport)).thenReturn(true);
		Mockito.when(reDAO.insertReport(newReport)).thenReturn(true);
		Mockito.when(reDAO.selectEmployeeReports(1)).thenReturn(null);

		Mockito.when(emDAO.isEmployee("Harley")).thenReturn(true);
		Mockito.when(emDAO.isEmployee("Brandon")).thenReturn(true);
		Mockito.when(emDAO.isEmployee("user")).thenReturn(false);
		Mockito.when(emDAO.selectEmployeeByUsername(user)).thenReturn(employee);
		Mockito.when(emDAO.selectEmployeeByUsername(employee2)).thenReturn(null);
		Mockito.when(emDAO.selectEmployeeByUsername(employee)).thenReturn(user);

	}

	@Test
	public void testGetEmployee() {
		
		System.out.println(serv.getEmployee(user));
		assertNotNull(serv.getEmployee(user));
		assertNull(serv.getEmployee(employee2));
		assertNotNull(serv.getEmployee(employee));
		
	}
	
	@Test
	public void testVerifyEmployeeExists() {
		
		assertTrue(serv.verifyEmployeeExists("Harley"));
		assertTrue(serv.verifyEmployeeExists("Brandon"));
		assertFalse(serv.verifyEmployeeExists("user"));
	}
	
	@Test
	public void testAuthenticateEmployee() {
		
		
		assertTrue(serv.authenticateEmployee(user));
		assertFalse(serv.authenticateEmployee(employee2));
		assertTrue(serv.authenticateEmployee(employee));
		
	}
	
	@Test
	public void testVerifyManager() {

		user.setFinanceManager(true);
		
		assertTrue(serv.verifyManager(user));
		assertFalse(serv.verifyManager(employee2));
		assertFalse(serv.verifyManager(employee));
	}
	
	@Test
	public void testGetEmployeeReports() {

		
		assertNotNull(serv.getEmployeeReports(user));

		
	}
	
	@Test
	public void testCreateEmployeeReport() {


		assertTrue(serv.createEmployeeReport(newReport));
	}
	
	@Test
	public void testGetAllReports() {
		assertNotNull(serv.getAllReports());
	}
	
	@Test
	public void testUpdateReportStatus() {
		
		assertTrue(serv.updateReportStatus(newReport));
		
	}
}
