package com.revature.trms.database.dao;

import static com.revature.trms.database.AttachmentTable.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.pojo.Attachment;
import com.revature.util.ConnectionFactory;

public class AttachmentDataAccessObject {
	public static int create(Attachment attachment) throws SQLException {
		String sql = String.format("INSERT INTO ATTACHMENT (%s, %s, %s) VALUES (?, ?, ?)",
				ATTACHMENT_TYPE, ATTACHMENT_FILEPATH, ATTACHMENT_REQUEST_ID);
		int rowsInserted = 0;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, attachment.getType());
				statement.setString(2, attachment.getFilepath());
				statement.setInt(3, attachment.getRequestId());

				rowsInserted = statement.executeUpdate();
			}
		}

		return rowsInserted;
	}

	public static Attachment select(int id) throws SQLException {
		String sql = String.format("SELECT * FROM ATTACHMENT WHERE %s = ?", ATTACHMENT_ID);
		Attachment attachment = null;

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);

				ResultSet set = statement.executeQuery();
				attachment = set.next() ? Attachment.parseAttachment(set) : null;
			}
		}

		return attachment;
	}

	public static List<Attachment> selectAll() throws SQLException {
		String sql = "SELECT * FROM ATTACHMENT";
		List<Attachment> attachments = new ArrayList<>();

		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet set = statement.executeQuery();
				while (set.next()) {
					attachments.add(Attachment.parseAttachment(set));
				}
			}
		}

		return attachments;
	}

	public static int update(Attachment attachment) throws SQLException {
		String sql = String.format("UPDATED ATTACHMENT SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
				ATTACHMENT_TYPE, ATTACHMENT_FILEPATH, ATTACHMENT_REQUEST_ID, ATTACHMENT_ID);
		int rowsUpdated = 0;
		
		try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, attachment.getType());
				statement.setString(2, attachment.getFilepath());
				statement.setInt(3, attachment.getRequestId());
				statement.setInt(4, attachment.getId());

				rowsUpdated = statement.executeUpdate();
			}
		}

		return rowsUpdated;
	}

	public static int delete(int id) throws SQLException {
		String sql = String.format("DELETE FROM ATTACHMENT WHERE %s = ?", ATTACHMENT_ID);
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
