package com.revature.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.database.dao.UtilDataAccessObject;
import com.revature.util.ObjectMapperFactory;

public class UtilServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1168571016311487923L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();
		
		try {
			writer.print(mapper.writeValueAsString(UtilDataAccessObject.getReimbursementPercentage()));
		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} finally {
			writer.close();
		}
	}
	
}
