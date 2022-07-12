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
import com.chainsys.miniproject.dao.DoctorDao;
import com.chainsys.miniproject.pojo.Doctor;


/**
 * Servlet implementation class Employees
 */
public class Doctors extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Doctors() {
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
		List<Doctor> allDoctors = DoctorDao.getAllDoctors();
		Iterator<Doctor> dociterator = allDoctors.iterator();

		while (dociterator.hasNext()) {
			out.println("<hr/>");
			Doctor doc = dociterator.next();
			out.println(doc.getDoctor_id() + ", " + doc.getDoctor_name() + ", " + doc.getPhoneno() + " ,"
					+ doc.getSpeciality() + "," + doc.getStandard_fees() + ", " + doc.getCity() + "</p>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String submitvalue = request.getParameter("submit");
		if (submitvalue.equals("Add Doctor")) {
			String source = "Add Doctor";
			String message = "<h1>Error while " + source + "</h1>";

			PrintWriter out = response.getWriter();
			System.out.println("post method is activited");
			String id = request.getParameter("id");
			int docId;
			Doctor newdoctor = new Doctor();
			try {
				Validator.checkStringForParseInt(id);
				docId = Integer.parseInt(id);

			} catch (InvalidInputDataException err) {
				message += " Error in Doctor id input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}

			try {
				Validator.checkNumberForGreaterThanZero(docId);
				newdoctor.setDoctor_id(docId);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor id input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}

			String Fname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(Fname);
				Validator.checklengthOfString(Fname);
				newdoctor.setDoctor_name(Fname);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor FirstName input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;

			}

			String sDate = request.getParameter("dob");
			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				newdoctor.setDob(date);
			} catch (ParseException e) {
				message += " Error in Doctor dob input: <p/>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkDateFormat(sDate);
				newdoctor.setDob(date);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor dob input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}

			String city = request.getParameter("city");
			try {
				Validator.checkStringOnly(city);
				newdoctor.setCity(city);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor city input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}

			String phno = request.getParameter("phno");
			long docPhNo = 0;
			try {
				Validator.checkPhoneNumer(phno);
			} catch (InvalidInputDataException err) {
				message += " Error in Doctor phon input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkStringForParseFloat(phno);
				docPhNo = Long.parseLong(phno);
			} catch (InvalidInputDataException err) {
				err.printStackTrace();

			}
			newdoctor.setPhoneno(docPhNo);

			String speciality = request.getParameter("speciality");
			try {
				Validator.checkStringOnly(speciality);
			} catch (InvalidInputDataException e) {
				message += " Error in Doctor speciality input: <p/>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;

			}
			newdoctor.setSpeciality(speciality);

			String stdFees = request.getParameter("stdfees");
			float stdfees1 = 0;
			try {
				Validator.checkStringForParseFloat(stdFees);
				stdfees1 = Float.parseFloat(stdFees);
			} catch (InvalidInputDataException err) {
				message += " Error in Standard Fees input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}

			newdoctor.setStandard_fees(stdfees1);
			int result = DoctorDao.insertDoctor(newdoctor);
			System.out.println(result);
			out.println("<div> Add New Doctors: " + result + "</div>");
// do not give object only browser response to string
		}

		else if (submitvalue.equals("Update Doctor")) {

			doPut(request, response);

		} else if (submitvalue.equals("Delete Doctor")) {
			doDelete(request, response);

		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---put method called---");
		String source = "Update  Doctor";
		String message = "<h1>Error while " + source + "</h1>";

		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		int docId = 0;
		Doctor newdoctor = new Doctor();
		try {
			Validator.checkStringForParseInt(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			message += " Error in Doctor id  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		try {
			Validator.checkNumberForGreaterThanZero(docId);

		} catch (InvalidInputDataException err) {
			message += " Error in Doctor id input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		String Fname = request.getParameter("fname");
		try {
			Validator.checkStringOnly(Fname);
			Validator.checklengthOfString(Fname);
			newdoctor.setDoctor_name(Fname);
		} catch (InvalidInputDataException err) {
			message += " Error in FirstName  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		String sDate = request.getParameter("dob");
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

		} catch (ParseException e) {
			message += " Error in Doctor dob input: <p/>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			Validator.checkDateFormat(sDate);

		} catch (InvalidInputDataException err) {
			message += " Error in Doctor id input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		newdoctor.setDob(date);

		String city = request.getParameter("city");
		try {
			Validator.checkStringOnly(city);
		} catch (InvalidInputDataException err) {
			message += " Error in City  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		newdoctor.setCity(city);

		String phno = request.getParameter("phno");
		long docPhNo = 0;
		try {
			Validator.checkPhoneNumer(phno);
		} catch (InvalidInputDataException err) {
			message += " Error in Doctor Phonenumber  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		try {
			Validator.checkStringForParseFloat(phno);
			docPhNo = Long.parseLong(phno);
		} catch (InvalidInputDataException err) {
			message += " Error in Doctor phoneNumber  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}
		newdoctor.setPhoneno(docPhNo);

		String speciality = request.getParameter("speciality");
		try {
			Validator.checkStringOnly(speciality);
		} catch (InvalidInputDataException e) {
			message += " Error in Speciality  input: <p/>";
			String errorPage = ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;

		}
		newdoctor.setSpeciality(speciality);

		String stdFees = request.getParameter("stdfees");
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(stdFees);
			stdfees1 = Float.parseFloat(stdFees);
		} catch (InvalidInputDataException err) {
			message += " Error in Standard Fees input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		newdoctor.setStandard_fees(stdfees1);

		int result = DoctorDao.updateDoctor(newdoctor);
		out.println("<div> Update Doctor " + result + "<div/>");

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---delete  method called---");
		String source = "Delete   Doctor";
		String message = "<h1>Error while " + source + "</h1>";
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		int docId = 0;

		try {
			Validator.checkStringForParseInt(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			message += " Error in Doctor id  input: <p/>";
			String errorPage = ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;

		}

		int result = DoctorDao.deleteDoctor(docId);
		out.println("<div> Delete Doctor " + result + "<div/>");

	}

}
