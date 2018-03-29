package com.revature.trms.database.dao;

import static com.revature.trms.database.EmployeeTable.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.trms.pojo.Employee;
import com.revature.util.ConnectionFactory;

public class UtilDataAccessObject {
	private static final String EMPLOYEE_PASSWORD = "E_PASSWORD";

	public static Employee checkLogin(String email, String password) throws SQLException {
		String sql = String.format("SELECT %s, %s, %s FROM LOGON WHERE %s = ?", EMPLOYEE_ID, EMPLOYEE_EMAIL, EMPLOYEE_PASSWORD, EMPLOYEE_EMAIL);
		Employee employee = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, email);

				ResultSet set = statement.executeQuery();

				if (set.next()) {
					String hash = set.getString(EMPLOYEE_PASSWORD);
					employee = verifyPassword(password, hash) ? EmployeeDataAccessObject.select(set.getInt(EMPLOYEE_ID)) : null;
				}
			}
		}

		return employee;
	}

	private static boolean verifyPassword(String password, String hash) {
		return password.equals(hash);
	}
	
	public static Map<String, Double> getReimbursementPercentage() throws SQLException {
		Map<String, Double> percentages = new HashMap<>();
		
		String sql = "SELECT * FROM EVENT_TYPE";
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet set = statement.executeQuery();
				
				while (set.next()) {
					percentages.put(set.getString(2), set.getDouble(3));
				}
			}
		}
		
		return percentages;
	}
}
