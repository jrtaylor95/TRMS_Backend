package com.revature.trms.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.revature.trms.database.dao.EmployeeDataAccessObject;
import com.revature.trms.pojo.Employee;

public class EmployeeDatabaseTests {
	@Test
	public void testEmployeeCreate() {
		Employee employee = new Employee();
		employee.setType(1);
		employee.setDepartment(1);
		employee.setFirstName("Test");
		employee.setLastName("Test");
		employee.setEmail("test@example.com");
		employee.setPhoneNumber("555-555-5555");
		employee.setReportsTo(2);
		
		try {
			int rowsInserted = EmployeeDataAccessObject.create(employee);
			
			assertEquals(1, rowsInserted);
			
			EmployeeDataAccessObject.delete(10);
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testEmployeeSelect() {
		try {
			Employee employee = EmployeeDataAccessObject.select(1);
			
			assertEquals(1, employee.getId());
			assertEquals(3, employee.getType());
			assertEquals(1, employee.getDepartment());
			assertEquals("Michael", employee.getFirstName());
			assertEquals("Scott", employee.getLastName());
			assertEquals("michael.scott@dundermifflin.com", employee.getEmail());
			assertEquals("555-555-5555", employee.getPhoneNumber());
			assertEquals(0, employee.getReportsTo());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testEmployeeSelectAll() {
		List<Employee> employees;
		try {
			employees = EmployeeDataAccessObject.selectAll();
			
			assertTrue(employees.size() > 0);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testEmployeeUpdate() {
		try {
			Employee employee = EmployeeDataAccessObject.select(6);
			employee.setLastName("Halpert");
			assertEquals(1, EmployeeDataAccessObject.update(employee));
			
			employee = EmployeeDataAccessObject.select(6);
			assertEquals("Halpert", employee.getLastName());
			
			employee = EmployeeDataAccessObject.select(7);
			employee.setLastName("Schrute");
			assertEquals(1, EmployeeDataAccessObject.update(employee));
			
			employee = EmployeeDataAccessObject.select(7);
			assertEquals("Schrute", employee.getLastName());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testEmployeeDelete() {
		try {
			int rowsDeleted = EmployeeDataAccessObject.delete(9);
			
			assertEquals(1, rowsDeleted);
			assertEquals(0, EmployeeDataAccessObject.select(9).getIsActive());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

}
