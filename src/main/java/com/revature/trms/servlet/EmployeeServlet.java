package com.revature.trms.servlet;

import static com.revature.util.HttpErrorMessages.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.database.dao.EmployeeDataAccessObject;
import com.revature.trms.pojo.Employee;
import com.revature.util.ObjectMapperFactory;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 243035429184359085L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// The api says that the return of getParameterMap is a map of type String, String array
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();
		Object result = null;

		try {
			if (parameters.isEmpty())
				result = EmployeeDataAccessObject.selectAll();
			else
				result = EmployeeDataAccessObject.select(Integer.parseInt(parameters.get("employeeId")[0]));

			writer.print(result != null ? mapper.writeValueAsString(result) : STATUS_404);
		} catch (SQLException e) {
			writer.print(STATUS_500);
		} finally {
			writer.close();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();

		if (!parameters.containsKey("employee"))
			writer.print(STATUS_400);

		Employee employee = mapper.readValue(parameters.get("employee")[0], Employee.class);

		try {
			EmployeeDataAccessObject.create(employee);
		} catch (SQLException e) {
			writer.print(STATUS_500);
		} finally {
			writer.close();
		}
	}

	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();

		if (!parameters.containsKey("employee"))
			writer.print(STATUS_400);

		Employee employee = mapper.readValue(parameters.get("employee")[0], Employee.class);

		try {
			EmployeeDataAccessObject.update(employee);
		} catch (SQLException e) {
			writer.print(STATUS_500);
		} finally {
			writer.close();
		}
	}
}
