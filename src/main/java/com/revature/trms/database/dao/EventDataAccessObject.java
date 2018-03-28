package com.revature.trms.database.dao;

import static com.revature.trms.database.EventTable.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojo.Event;
import com.revature.util.ConnectionFactory;

public class EventDataAccessObject {
	public static Event select(int id) throws SQLException {
		String sql = String.format("SELECT * FROM EVENT WHERE %s = ?", EVENT_ID);
		Event event = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);

				ResultSet set = statement.executeQuery();
				event = set.next() ? Event.parseEvent(set) : null;
			}
		}

		return event;
	}
}
