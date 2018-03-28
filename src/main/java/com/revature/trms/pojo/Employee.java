package com.revature.trms.pojo;

import static com.revature.trms.database.EmployeeTable.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents an Employee.
 * @author James Taylor
 *
 */
public class Employee {
	/**
	 * Parses the ResultSet as an Employee.
	 * @param set a ResultSet containing the Employee representation to be parsed
	 * @return The Employee object represented by the argument
	 * @throws SQLException
	 */
	public static Employee parseEmployee(ResultSet set) throws SQLException {
		Employee employee = new Employee();
		
		employee.setId(set.getInt(EMPLOYEE_ID));
		employee.setType(set.getInt(EMPLOYEE_TYPE));
		employee.setDepartment(set.getInt(EMPLOYEE_DEPARTMENT));
		employee.setFirstName(set.getString(EMPLOYEE_FIRST_NAME));
		employee.setLastName(set.getString(EMPLOYEE_LAST_NAME));
		employee.setEmail(set.getString(EMPLOYEE_EMAIL));
		employee.setPhoneNumber(set.getString(EMPLOYEE_PHONE_NUMBER));
		employee.setReportsTo(set.getInt(EMPLOYEE_REPORTS_TO));
		employee.setIsActive(set.getShort(EMPLOYEE_IS_ACTIVE));
		
		return employee;
	}
	
	private int id;
	private int type;
	private int department;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private int reportsTo;
	private short isActive;
	
	public int getId() {
		return this.id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getDepartment() {
		return department;
	}
	
	public void setDepartment(int department) {
		this.department = department;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public short getIsActive() {
		return isActive;
	}

	public void setIsActive(short isActive) {
		this.isActive = isActive;
	}
}
