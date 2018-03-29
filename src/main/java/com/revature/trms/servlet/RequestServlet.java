package com.revature.trms.servlet;

import static com.revature.util.HttpErrorMessages.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.database.dao.RequestDataAccessObject;
import com.revature.trms.database.dao.EmployeeDataAccessObject;
import com.revature.trms.database.dao.EventDataAccessObject;
import com.revature.trms.pojo.Employee;
import com.revature.trms.pojo.Event;
import com.revature.trms.pojo.Request;
import com.revature.util.LoggingUtil;
import com.revature.util.ObjectMapperFactory;

public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = -1682943500141900239L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = req.getParameterMap();
		PrintWriter writer = resp.getWriter();
		ObjectMapper mapper = ObjectMapperFactory.getInstance();
		Object result = null;
		Event event = null;
		Request request = null;
		Employee employee = null;

		HttpSession session = req.getSession(false);

		try {
			if (parameters.containsKey("requestId")) {
				List<Object> list = new ArrayList<Object>();
				request = RequestDataAccessObject.select(Integer.parseInt(parameters.get("requestId")[0]));
				event = EventDataAccessObject.select(request.getEventId());
				employee = EmployeeDataAccessObject.select(request.getEmployeeId());

				list.add(request);
				list.add(event);
				list.add(employee);
				result = list;
				LoggingUtil.logInfo("doGet() Get Request successful");
			} else if (parameters.containsKey("employeeId")) {
				result = RequestDataAccessObject.selectHandledSummary(Integer.parseInt(parameters.get("employeeId")[0]));
				LoggingUtil.logInfo("doGet() Get pending requests successful");
			} else if (parameters.containsKey("myId")) {
				result = RequestDataAccessObject.selectSummary(Integer.parseInt(parameters.get("myId")[0]));
				LoggingUtil.logInfo("doGet() Get my requests successful");
			}

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

		if (parameters.isEmpty() || !parameters.containsKey("request"))
			writer.print(STATUS_400);

		try {
			RequestDataAccessObject.create(mapper.readValue(parameters.get("request")[0], Request.class), mapper.readValue(parameters.get("event")[0], Event.class));
			LoggingUtil.logInfo("doPost(): Create Request Successful"); 
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
		int id = 0;
		if (parameters.isEmpty() || !parameters.containsKey("option"))
			writer.print(STATUS_400);

		try {
			switch (parameters.get("option")[0]) {
			case "1":
				// Update request
				RequestDataAccessObject.update(mapper.readValue(parameters.get("request")[0], Request.class));
				LoggingUtil.logInfo("doPut() Request Update successful"); 
				break;
			case "2":
				// Reject Request
				id = Integer.parseInt(parameters.get("requestId")[0]);
				String reason = parameters.get("reason")[0];
				RequestDataAccessObject.rejectRequest(id, reason);
				LoggingUtil.logInfo("doPut() Request reject successful");
				break;
			case "3":
				// Accept Request
				id = Integer.parseInt(parameters.get("requestId")[0]);
				RequestDataAccessObject.accept(id);
				LoggingUtil.logInfo("doPut() Request accept successful");
				break;
			case "4":
				// Final Accept
				id = Integer.parseInt(parameters.get("requestId")[0]);
				RequestDataAccessObject.finalAccept(id);
				LoggingUtil.logInfo("doPut() Request final accept successful");
				break;
			}
		} catch (SQLException e) {
			resp.getWriter().write(STATUS_500);
			LoggingUtil.logError("doPut() Error: " + e.getMessage());
		} finally {
			writer.close();
		}
	}
}