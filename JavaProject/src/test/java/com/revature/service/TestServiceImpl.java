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
	private static ReportDAO rDAO;
	@Mock
	private static EmployeeDAO eDAO;
//	@Mock
//	private static Employee user;
//	@Mock
//	private static Employee em;
	
	private Service serv = new ServiceImpl(eDAO, rDAO);
	
	@BeforeClass
	public static void fakeMyDAO() {
		rDAO = Mockito.mock(ReportDAOImpl.class);
		
		eDAO = Mockito.mock(EmployeeDAOImpl.class);
		
	
		Employee user = new Employee("Brandon","starfish");
		Report re = new Report();
//		Mockito.when(user.isFinanceManager()).thenReturn(true);
//		Mockito.when(em.getPassword()).thenReturn("starfish");
		Mockito.when(rDAO.insertReport(null)).thenReturn(true);
		Mockito.when(rDAO.selectEmployeeReports(1)).thenReturn(null);
		Mockito.when(eDAO.isEmployee("Harley")).thenReturn(true);
		Mockito.when(eDAO.isEmployee("Brandon")).thenReturn(true);
		Mockito.when(eDAO.selectEmployeeByUsername(user)).thenReturn(user);
	}

	
//	@Test
//	public void testAuthenticateEmployee() {
//		Employee user = new Employee("Brandon","starfish");
//		
//		assertTrue(serv.authenticateEmployee(user));
//		
//	}
	
	@Test
	public void testVerifyManager() {
		Employee user = new Employee("Brandon","starfish");
		user.setFinanceManager(true);
		
		assertTrue(serv.verifyManager(user));
	}
	
	@Test
	public void testGetEmployeeReports() {
		Employee user = new Employee("Brandon","starfish");
		
		assertNotNull(serv.getEmployeeReports(user));
		
	}
	
	@Test
	public void testCreateEmployeeReport() {
		Report re = new Report();
		
		assertTrue(serv.createEmployeeReport(re));
	}
	
	@Test
	public void testGetAllReports() {
		assertNotNull(serv.getAllReports());
	}
	
	@Test
	public void testUpdateReportStatus() {
		Report re = new Report();
		assertTrue(serv.updateReportStatus(re));
	}
}
