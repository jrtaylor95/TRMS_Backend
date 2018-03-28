package com.revature.trms.servlet;

import static com.revature.util.HttpErrorMessages.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.database.dao.UtilDataAccessObject;
import com.revature.trms.pojo.Employee;
import com.revature.util.ObjectMapperFactory;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -455626645967846542L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			writer.print(String.format("{\"loggedIn\": %s", true));
		} else {
			resp.setStatus(403);
			writer.print(String.format("{\"loggedIn\": %s}", false));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();
		Employee employee = null;
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			employee = UtilDataAccessObject.checkLogin(email, password);
			writer.print(mapper.writeValueAsString(employee));
			if (employee != null) {
				HttpSession session = req.getSession();
				session.setAttribute("employee", mapper.writeValueAsString(employee));
			} else {
				resp.setStatus(403);
			}
		} catch (SQLException e) {
			writer.print(STATUS_500);
		} finally {
			writer.close();
		}
	}
}
