package com.chainsys.webapp.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.miniproject.commonutil.ExceptionManager;
import com.chainsys.miniproject.commonutil.InvalidInputDataException;
import com.chainsys.miniproject.commonutil.Validator;
import com.chainsys.miniproject.dao.AppointmentDao;
import com.chainsys.miniproject.pojo.Appointments;




/**
 * Servlet implementation class Employees
 */
public class Appointmentss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Appointmentss() {
		super();
// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		List<Appointments> allAppointmentss = AppointmentDao.getAllAppointments();
		Iterator<Appointments> appiterator = allAppointmentss.iterator();
		while (appiterator.hasNext()) {
			out.println("<hr/>");
			Appointments app = appiterator.next();
			out.println(app.getApp_id() + " " + app.getPatient_name() + " " + app.getApp_date() + " "
					+ app.getFees_collected() + " " + app.getApp_id());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submitvalue = request.getParameter("submit");
		if (submitvalue.equals("Add Appointment")) {
			System.out.println("---post method called---");
			String source = "Add  Appointment ";
			String message = "<h1>Error while " + source + "</h1>";

			PrintWriter out = response.getWriter();
			Appointments newappointment = new Appointments();

			String id = request.getParameter("id");
			int appId;

			try {
				Validator.checkStringForParseInt(id);
				appId = Integer.parseInt(id);

			} catch (InvalidInputDataException err) {
				message += " Error in Appointment  id  input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;

			}
			newappointment.setApp_id(appId);

			String name = request.getParameter("pname");
			try {
				Validator.checkStringOnly(name);
				Validator.checklengthOfString(name);
				newappointment.setPatient_name(name);
			} catch (InvalidInputDataException err) {
				message += " Error in Patient Name   input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;

			}

			String sDate = request.getParameter("adate");
			Date date = null;

			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				newappointment.setApp_date(date);

			} catch (ParseException e) {
				message += " Error in Appointment date input: <p/>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;

			}

			String id1 = request.getParameter("docid");
			int docId;

			try {
				Validator.checkStringForParseInt(id1);
				docId = Integer.parseInt(id1);

			} catch (InvalidInputDataException err) {
				message += " Error in Doctor   id  input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			newappointment.setDoctor_id(docId);

			String stdFees = request.getParameter("fees");
			float stdfees1 = 0;
			try {
				Validator.checkStringForParseFloat(stdFees);
				stdfees1 = Float.parseFloat(stdFees);
				newappointment.setFees_collected(stdfees1);

			} catch (InvalidInputDataException err) {
				message += " Error in Appointment  Fees  input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;

			}

			int result = AppointmentDao.insertAppointment(newappointment);
			out.println("<div> Add new appointment " + result + "</div>");
		}

		else if (submitvalue.equals("Update Appointment")) {
			doPut(request, response);
		} else if (submitvalue.equals("Delete Appointment")) {
			doDelete(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		Appointments oldappointment = new Appointments();
		System.out.println("---put method called---");
		String source = "Update  Appointment ";
		String message = "<h1>Error while " + source + "</h1>";

		String id = request.getParameter("id");
		int appId;

		try {
			Validator.checkStringForParseInt(id);
			appId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			message += " Error in Appointment  id input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}
		oldappointment.setApp_id(appId);

		String name = request.getParameter("pname");
		try {
			Validator.checkStringOnly(name);
			Validator.checklengthOfString(name);
			oldappointment.setPatient_name(name);
		} catch (InvalidInputDataException err) {
			message += " Error in Patient Name input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		String sDate = request.getParameter("adate");
		Date date = null;

		try {
			Validator.checkDateFormat(sDate);

		} catch (InvalidInputDataException err) {
			message += " Error in Appointment  Date  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			oldappointment.setApp_date(date);

		} catch (ParseException e) {
			message += " Error in Appointment  Date  input: <p/>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;

		}

		String id1 = request.getParameter("docid");
		int docId;

		try {
			Validator.checkStringForParseInt(id1);
			docId = Integer.parseInt(id1);

		} catch (InvalidInputDataException err) {
			message += " Error in Doctor id input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}
		oldappointment.setDoctor_id(docId);

		String collectedFees = request.getParameter("fees");
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(collectedFees);
			stdfees1 = Float.parseFloat(collectedFees);
		} catch (InvalidInputDataException err) {
			message += " Error in Appointment  Fees  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		oldappointment.setFees_collected(stdfees1);

		int result = AppointmentDao.updateAppointment(oldappointment);
		out.println("<div> Update Appointment " + result + "<div/>");
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		System.out.println("---delete method called---");
		String source = "Delete  Appointment ";
		String message = "<h1>Error while " + source + "</h1>";

		String s1 = request.getParameter("appid");
		int appId = 0;
		try {
			Validator.checkStringForParseInt(s1);
			appId = Integer.parseInt(s1);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		try {
			AppointmentDao.getAppointmentById(appId);
			Validator.checkNumberForGreaterThanZero(appId);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;
		}
		int result = AppointmentDao.deleteAppointment(appId);
		out.println("<div> Delete The Appointment" + result + "<div>");

	}

}
