package com.revature.trms.pojo;

import static com.revature.trms.database.RequestTable.*;
import static com.revature.trms.database.EmployeeTable.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestSummary {
	
	public static RequestSummary parseRequestSummary(ResultSet set) throws SQLException {
		RequestSummary requestSummary = new RequestSummary();
		
		requestSummary.setId(set.getInt(REQUEST_ID));
		requestSummary.setEventType(set.getString(2));
		requestSummary.setEmployeeId(set.getInt(EMPLOYEE_ID));
		requestSummary.setFirstName(set.getString(EMPLOYEE_FIRST_NAME));
		requestSummary.setLastName(set.getString(EMPLOYEE_LAST_NAME));
		requestSummary.setSubmittedDate(set.getDate(REQUEST_DATE_SUBMITTED));
		requestSummary.setIsUrgent(set.getShort(REQUEST_IS_URGENT));
		
		return requestSummary;
	}
	
	private int id;
	private String eventType;
	private int employeeId;
	private String firstName;
	private String lastName;
	private Date submittedDate;
	private short isUrgent;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getSubmittedDate() {
		return submittedDate;
	}
	
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	
	public short getIsUrgent() {
		return isUrgent;
	}
	
	public void setIsUrgent(short isUrgent) {
		this.isUrgent = isUrgent;
	}
}
