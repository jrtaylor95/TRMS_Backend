package com.revature.trms.servlet;

import static com.revature.util.HttpErrorMessages.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.database.dao.AttachmentDataAccessObject;
import com.revature.util.ObjectMapperFactory;

public class AttachmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8603690175218348205L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();
		Integer attachmentId = (Integer) req.getAttribute("attachmentId");
		
		Object result = null;
		
		try {
			if (attachmentId == null)
				result = AttachmentDataAccessObject.selectAll();
			else
				result = AttachmentDataAccessObject.select(attachmentId);
			
			writer.print(result != null ? mapper.writeValueAsString(result) : STATUS_404);
		} catch (SQLException e) {
			writer.print(STATUS_500);
		}
	}
	
	
}
