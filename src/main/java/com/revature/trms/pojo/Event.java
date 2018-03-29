package com.revature.trms.pojo;

import static com.revature.trms.database.EventTable.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Event {
	public static Event parseEvent(ResultSet set) throws SQLException {
		Event event = new Event();
		
		event.setId(set.getInt(EVENT_ID));
		event.setType(set.getInt(EVENT_TYPE));
		event.setDate(set.getDate(EVENT_DATE));
		event.setLocation(set.getString(EVENT_LOCATION));
		event.setDescription(EVENT_DESCRIPTION);
		event.setCost(set.getDouble(EVENT_COST));
		event.setGradingFormat(set.getString(EVENT_GRADE_FORMAT));
		event.setPassingGrade(set.getString(EVENT_PASSING_GRADE));
		
		return event;
	}
	
	private int id;
	private int type;
	private Date date;
	private String location;
	private String description;
	private double cost;
	private String gradingFormat;
	private String passingGrade;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getGradingFormat() {
		return gradingFormat;
	}
	
	public void setGradingFormat(String gradeFormat) {
		this.gradingFormat = gradeFormat;
	}
	
	public String getPassingGrade() {
		return passingGrade;
	}
	
	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}
}
