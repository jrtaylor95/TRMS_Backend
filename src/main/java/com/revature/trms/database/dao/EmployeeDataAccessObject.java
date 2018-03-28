package com.revature.trms.database.dao;

import static com.revature.trms.database.EmployeeTable.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.pojo.Employee;
import com.revature.util.ConnectionFactory;

/**
 * This class acts as the go between for the TRMS java application and the TRMS database for employees.
 * @author James Taylor
 *
 */
public class EmployeeDataAccessObject {
	
	/**
	 * Inserts a row in the Employee table based on the given employee object
	 * @param employee The employee to insert into the database
	 * @return The amount of rows affected by this insert.
	 * @throws SQLException 
	 */
	public static int create(Employee employee) throws SQLException {
		String sql = String.format("INSERT INTO EMPLOYEE (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
				EMPLOYEE_TYPE, EMPLOYEE_DEPARTMENT, EMPLOYEE_FIRST_NAME, EMPLOYEE_LAST_NAME, EMPLOYEE_EMAIL,
				EMPLOYEE_PHONE_NUMBER, EMPLOYEE_REPORTS_TO);
		int rowsInserted = 0;
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, employee.getType());
				statement.setInt(2, employee.getDepartment());
				statement.setString(3, employee.getFirstName());
				statement.setString(4, employee.getLastName());
				statement.setString(5, employee.getEmail());
				statement.setString(6, employee.getPhoneNumber());
				statement.setInt(7, employee.getReportsTo());
				
				rowsInserted = statement.executeUpdate();
			}
		}
		
		return rowsInserted;
	}
	
	/**
	 * Selects an employee with the specified id from the database.
	 * @param id The id of the employee to return
	 * @return An employee object with the specified id
	 * @throws SQLException 
	 */
	public static Employee select(int id) throws SQLException {
		String sql = String.format("SELECT * FROM EMPLOYEE WHERE %s = ?", EMPLOYEE_ID);
		Employee employee = null;
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				
				ResultSet set = statement.executeQuery();		
				employee = set.next() ? Employee.parseEmployee(set) : null;
			}
		}
		
		return employee;
	}
	
	/**
	 * Selects all employees from the database
	 * @return A list of employee objects representing all of the employees in the database.
	 * @throws SQLException 
	 */
	public static List<Employee> selectAll() throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE";
		List<Employee> employees = new ArrayList<>();
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet set = statement.executeQuery();
				while (set.next()) {
					employees.add(Employee.parseEmployee(set));
				}
			}
		}
		
		return employees;
	}
	
	/**
	 * Updates the row in the database containing this employee
	 * @param employee The employee object containing the information to be updated
	 * @return The number of rows updated
	 * @throws SQLException 
	 */
	public static int update(Employee employee) throws SQLException {
		String sql = String.format("UPDATE EMPLOYEE SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
				EMPLOYEE_TYPE, EMPLOYEE_DEPARTMENT, EMPLOYEE_FIRST_NAME, EMPLOYEE_LAST_NAME, EMPLOYEE_EMAIL,
				EMPLOYEE_PHONE_NUMBER, EMPLOYEE_REPORTS_TO, EMPLOYEE_ID);
		int rowsUpdated = 0;
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, employee.getType());
				statement.setInt(2, employee.getDepartment());
				statement.setString(3, employee.getFirstName());
				statement.setString(4, employee.getLastName());
				statement.setString(5, employee.getEmail());
				statement.setString(6, employee.getPhoneNumber());
				if (employee.getReportsTo() == 0)
					statement.setNull(7, java.sql.Types.INTEGER);
				else
					statement.setInt(7, employee.getReportsTo());
				statement.setInt(9, employee.getId());
				
				rowsUpdated = statement.executeUpdate();
			}
		}
		
		return rowsUpdated;
	}
	
	/**
	 * Soft Deletes the row in the database corresponding to the employee with this id
	 * @param id The id of the employee to delete
	 * @return The number of rows affected
	 * @throws SQLException 
	 */
	public static int delete(int id) throws SQLException {
		String sql = String.format("UPDATE EMPLOYEE SET %s = 0 FROM EMPLOYEE WHERE %s = ?", EMPLOYEE_IS_ACTIVE, EMPLOYEE_ID);
		int rowsDeleted = 0;
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				
				rowsDeleted = statement.executeUpdate();
			}
		}
		
		return rowsDeleted;
	}
}
