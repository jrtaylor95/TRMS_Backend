package com.revature.trms.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.trms.database.AttachmentTable.*;

/**
 * This class represents file attachments that employees can attach to a request.
 * @author James Taylor
 *
 */
public class Attachment {
	/**
	 * Parses the ResultSet as an Attachment.
	 * @param set a ResultSet containing the Attachment representation to be parsed.
	 * @return The Attachment object represented by the argument
	 * @throws SQLException
	 */
	public static Attachment parseAttachment(ResultSet set) throws SQLException {
		Attachment attachment = new Attachment();
		
		attachment.setId(set.getInt(ATTACHMENT_ID));
		attachment.setType(set.getInt(ATTACHMENT_TYPE));
		attachment.setFilepath(set.getString(ATTACHMENT_FILEPATH));
		attachment.setRequestId(set.getInt(ATTACHMENT_REQUEST_ID));
		
		return attachment;
	}
	
	private int id;
	private int type;
	private String filepath;
	private int requestId;
	
	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getFilepath() {
		return filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getRequestId() {
		return requestId;
	}

	protected void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}
