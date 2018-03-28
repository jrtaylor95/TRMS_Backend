package com.revature.trms.pojo;

import static com.revature.trms.database.RequestTable.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents the requests that employees can create.
 * @author James Taylor
 *
 */
public class Request {
	
	public static Request parseRequest(ResultSet set) throws SQLException {
		Request request = new Request();
		
		request.setId(set.getInt(REQUEST_ID));
		request.setHandledBy(set.getInt(REQUEST_HANDLED_BY));
		request.setEventId(set.getInt(REQUEST_EVENT_ID));
		request.setEmployeeId(set.getInt(REQUEST_EMPLOYEE_ID));
		request.setSubmittedDate(set.getDate(REQUEST_DATE_SUBMITTED));
		request.setReason(set.getString(REQUEST_JUSTIFICATION));
		request.setHoursMissed(set.getInt(REQUEST_HOURS_MISSED));
		request.setStatus(set.getInt(REQUEST_STATUS));
		request.setIsUrgent(set.getShort(REQUEST_IS_URGENT));
		request.setGrade(set.getString(REQUEST_GRADE));
		request.setReason(set.getString(REQUEST_REASON));
		request.setIsExceedingFunds(set.getShort(REQUEST_IS_EXCEEDING_FUNDS));
		request.setReimbursementAmount(set.getDouble(REQUEST_REIMBURSEMENT_AMOUNT));
		
		return request;
	}
	
	private int id;
	private int handledBy;
	private int eventId;
	private int employeeId;
	private Date submittedDate;
	private String workJustification;
	private int hoursMissed;
	private int status;
	private short isUrgent;
	private String grade;
	private String reason;
	private short isExceedingFunds;
	private double reimbursementAmount;
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public int getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(int handledById) {
		this.handledBy = handledById;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public Date getSubmittedDate() {
		return submittedDate;
	}
	
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getWorkJustification() {
		return workJustification;
	}

	public void setWorkJustification(String workJustification) {
		this.workJustification = workJustification;
	}

	public int getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(int hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public short getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(short isUrgent) {
		this.isUrgent = isUrgent;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public short getIsExceedingFunds() {
		return isExceedingFunds;
	}

	public void setIsExceedingFunds(short isExceedingFunds) {
		this.isExceedingFunds = isExceedingFunds;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}
}
