package com.revature.trms.database.dao;

import static com.revature.trms.database.RequestTable.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.pojo.Event;
import com.revature.trms.pojo.Request;
import com.revature.trms.pojo.RequestSummary;
import com.revature.util.ConnectionFactory;

public class RequestDataAccessObject {
	public static void create(Request request, Event event) throws SQLException {
		String sql = String.format("call INSERT_REQUEST(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (CallableStatement statement = connection.prepareCall(sql)) {
				statement.setInt(1, request.getEmployeeId());
				statement.setString(2, request.getWorkJustification());
				statement.setInt(3, request.getHoursMissed());
				statement.setInt(4, request.getStatus());
				statement.setInt(5, event.getType());
				statement.setDate(6,  event.getDate());
				statement.setString(7, event.getLocation());
				statement.setString(8, event.getDescription());
				statement.setDouble(9, event.getCost());
				statement.setString(10, event.getGradingFormat());
				statement.setString(11, event.getPassingGrade());

				statement.executeUpdate();
			}
		}
	}

	public static Request select(int id) throws SQLException {
		String sql = String.format("SELECT * FROM REQUEST WHERE %s = ?", REQUEST_ID);
		Request request = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);

				ResultSet set = statement.executeQuery();
				request = set.next() ? Request.parseRequest(set) : null;
			}
		}

		return request;
	}

	public static List<RequestSummary> selectSummary(int employeeId) throws SQLException {

		String sql = String.format("SELECT REQUEST.R_ID, EVENT_TYPE.E_TYPE, EMPLOYEE.E_ID, E_FIRST_NAME, E_LAST_NAME, R_DATE_SUBMITTED, R_IS_URGENT\n" + 
				"    FROM REQUEST\n" + 
				"    JOIN EVENT ON R_EVENT_ID = EVENT.E_ID\n" + 
				"    JOIN EMPLOYEE ON R_EMPLOYEE_ID = EMPLOYEE.E_ID\n" + 
				"    JOIN EVENT_TYPE ON EVENT.E_EVENT_TYPE = EVENT_TYPE.E_TYPE_ID "
				+ "WHERE EMPLOYEE.E_ID = ?");
		List<RequestSummary> requestSummaries = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (CallableStatement statement = connection.prepareCall(sql)) {
				statement.setInt(1, employeeId);

				ResultSet set = statement.executeQuery();
				while (set.next()) {
					requestSummaries.add(RequestSummary.parseRequestSummary(set));
				}
			}
		}

		return requestSummaries;
	}

	public static List<RequestSummary> selectHandledSummary(int employeeId) throws SQLException {
		//		String sql = String.format("SELECT %s, %s, %s, %s, %s, %s FROM REQUEST JOIN EMPLOYEE ON %s = %s WHERE %s = ?",
		//				REQUEST_ID, EMPLOYEE_ID, EMPLOYEE_FIRST_NAME, EMPLOYEE_LAST_NAME, REQUEST_DATE_SUBMITTED, 
		//				REQUEST_IS_URGENT, REQUEST_EMPLOYEE_ID, EMPLOYEE_ID, REQUEST_EMPLOYEE_ID);
		String sql = String.format("SELECT REQUEST.R_ID, EVENT_TYPE.E_TYPE, EMPLOYEE.E_ID, E_FIRST_NAME, E_LAST_NAME, R_DATE_SUBMITTED, R_IS_URGENT\n" + 
				"    FROM REQUEST\n" + 
				"    JOIN EVENT ON R_EVENT_ID = EVENT.E_ID\n" + 
				"    JOIN EMPLOYEE ON R_EMPLOYEE_ID = EMPLOYEE.E_ID\n" + 
				"    JOIN EVENT_TYPE ON EVENT.E_EVENT_TYPE = EVENT_TYPE.E_TYPE_ID "
				+ "WHERE REQUEST.R_HANDLED_BY = ?");
		List<RequestSummary> requestSummaries = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, employeeId);

				ResultSet set = statement.executeQuery();
				while (set.next()) {
					requestSummaries.add(RequestSummary.parseRequestSummary(set));
				}
			}
		}

		return requestSummaries;
	}

	public static void update(Request request) throws SQLException {
		String sql = String.format("UPDATE REQUEST SET %s = ?, %s = ? %s = ? %s = ?, %s = ?, %s = ?, %s = ?, %s = ?",
				REQUEST_GRADE, REQUEST_HANDLED_BY, REQUEST_HOURS_MISSED, REQUEST_JUSTIFICATION,
				REQUEST_REASON, REQUEST_IS_EXCEEDING_FUNDS, REQUEST_REIMBURSEMENT_AMOUNT, REQUEST_STATUS);

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, request.getGrade());
				statement.setInt(1, request.getHandledBy());
				statement.setInt(1, request.getHoursMissed());
				statement.setString(1, request.getWorkJustification());
				statement.setString(1, request.getReason());
				statement.setInt(1, request.getIsExceedingFunds());
				statement.setDouble(1, request.getReimbursementAmount());
				statement.setInt(1, request.getStatus());

				statement.executeUpdate();
			}
		}
	}

	public static void accept(int id) throws SQLException {
		String sql = "call ACCEPT_REQUEST(?)";

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (CallableStatement statement = connection.prepareCall(sql)) {
				statement.setInt(1, id);

				statement.executeUpdate();
			}
		}
	}

	public static void finalAccept(int id) throws SQLException {
		String sql = "call FINAL_ACCEPT_REQUEST(?)";

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (CallableStatement statement = connection.prepareCall(sql)) {
				statement.setInt(1, id);

				statement.executeUpdate();
			}
		}
	}

	public static void rejectRequest(int id, String reason) throws SQLException {
		String sql = "call REJECT_REQUEST(?, ?)";

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (CallableStatement statement = connection.prepareCall(sql)) {
				statement.setInt(1, id);
				statement.setString(2, reason);

				statement.executeUpdate();
			}
		}
	}
}
