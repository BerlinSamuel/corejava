package com.chainsys.miniproject.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.chainsys.miniproject.commonutil.InvalidInputDataException;
import com.chainsys.miniproject.commonutil.Validator;
import com.chainsys.miniproject.dao.AppointmentDao;
import com.chainsys.miniproject.pojo.Appointments;
import com.chainsys.miniproject.pojo.Doctor;

public class AppointmentsUi {

	public static void addNewAppointments() {
		Scanner sc = new Scanner(System.in);
		try {
		Appointments newappointment = new Appointments();
		System.out.println("Enter Appointment Id ");
		String id = sc.nextLine();
		int appId;
	
		try {
			Validator.checkStringForParseInt(id);
			appId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}	newappointment.setApp_id(appId);

		System.out.println("Enter Patient Name ");
		String name = sc.nextLine();
		try {
			Validator.checkStringOnly(name);
			Validator.checklengthOfString(name);
			newappointment.setPatient_name(name);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		System.out.println("Enter Appointment Date as dd/mm/yyyy");
		String sDate = sc.nextLine();
		Date date;
		try {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		newappointment.setApp_date(date);
		} catch (ParseException e) {
		e.printStackTrace();
		}try {
			Validator.checkDateFormat(sDate);
			newappointment.getApp_date();
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		
		System.out.println("Enter Doctor Id");
		String id1 = sc.nextLine();
		int docId;
	
		try {
			Validator.checkStringForParseInt(id1);
			docId = Integer.parseInt(id1);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}	newappointment.setDoctor_id(docId);


		System.out.println("Enter fee Collected ");
		String stdFees = sc.nextLine();
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(stdFees);
			stdfees1 = Float.parseFloat(stdFees);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		
		newappointment.setFees_collected(stdfees1);

		int result = AppointmentDao.insertAppointment(newappointment);
		System.out.println(result);
		} catch (NumberFormatException e) {
		e.printStackTrace();
		} finally {
		sc.close();
		}
		}

		public static void updateAppointments() {
		Scanner sc = new Scanner(System.in);
		try {
		Appointments oldappointment= new Appointments();
		System.out.println("Enter Appointment Id ");
		String id = sc.nextLine();
		int appId;
	
		try {
			Validator.checkStringForParseInt(id);
			appId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}	oldappointment.setApp_id(appId);

		
		System.out.println("Enter Patient Name ");
		String name = sc.nextLine();
		try {
			Validator.checkStringOnly(name);
			Validator.checklengthOfString(name);
			oldappointment.setPatient_name(name);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		System.out.println("Enter Appointment Date as dd/mm/yyyy");
		String sDate = sc.nextLine();
		Date date;
		try {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		oldappointment.setApp_date(date);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		try {
			Validator.checkDateFormat(sDate);
			oldappointment.getApp_date();
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		
		System.out.println("Enter Doctor Id");
		String id1 = sc.nextLine();
		int docId;
	
		try {
			Validator.checkStringForParseInt(id1);
			docId = Integer.parseInt(id1);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}	oldappointment.setDoctor_id(docId);



		System.out.println("Enter fee Collected ");
		String stdFees = sc.nextLine();
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(stdFees);
			stdfees1 = Float.parseFloat(stdFees);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		
		oldappointment.setFees_collected(stdfees1);

		int result = AppointmentDao.updateAppointment(oldappointment);
		System.out.println(result);
		} catch (NumberFormatException e) {
		e.printStackTrace();
		}finally{
		sc.close();
		}
		}

		public static void deleteAppointmentsById() {
		Scanner sc = new Scanner(System.in);
		String s1 =null;
		int result =0;
		try {
		System.out.println("Enter Appointment Id ");
		String id = sc.nextLine();
		int appId;
	
		try {
			Validator.checkStringForParseInt(id);
			appId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}	
		result = AppointmentDao.deleteAppointment(appId);
		System.out.println(result);
		} catch (NumberFormatException e) {
		e.printStackTrace();
		}finally {
		sc.close();
		}


		}

		public static void getPatientById() {

		Scanner sc = new Scanner(System.in);
		String s1 =null;
		try {
		System.out.println("Enter Appointment id");
		String id = sc.nextLine();
		int appId;
	
		try {
			Validator.checkStringForParseInt(id);
			appId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		Appointments doc = AppointmentDao.getAppointmentById(appId);
		System.out.println(doc.getApp_id() + " " + doc.getPatient_name()+" "
		+doc.getApp_date()+" "+doc.getFees_collected()+" "+doc.getApp_id());
		} catch (NumberFormatException e) {
		e.printStackTrace();
		}finally {
		sc.close();
		}
		}

		public static void getAllAppointments() {

		List<Appointments> allAppointmentss = AppointmentDao.getAllAppointments();
		Iterator<Appointments> dociterator = allAppointmentss.iterator();
		while (dociterator.hasNext()) {
		Appointments doc = dociterator.next();
		System.out.println(doc.getApp_id() + " " + doc.getPatient_name()+" "
		+doc.getApp_date()+" "+doc.getFees_collected()+" "+doc.getApp_id()
		);
		}

		}

		

		public static void updateDateOfAppointments() {
		Scanner sc = new Scanner(System.in);
		int result =0;
		int id =0;
		try {
		System.out.println("Enter Appointment Id: ");
		id =sc.nextInt();
		System.out.println("Enter Appointment Date as dd/mm/yyyy");
		String sDate = sc.nextLine();
		Date date = null;
		try {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		} catch (ParseException e) {
		e.printStackTrace();
		}

		result = AppointmentDao.updateDateOfAppointment(id,date);
		System.out.println(result+" updated Successfully");
		} catch (Exception e) {
		e.printStackTrace();
		}finally {
		sc.close();
		}
		}

		public static void updateCollectedFees() {
		Scanner sc =new Scanner(System.in);
		int result =0;
		int id =0;
		
		try {
		System.out.println("Enter Doctor Id: ");
		id =sc.nextInt();
		System.out.println("Enter Collected Fee: ");
		int CollectedFee = sc.nextInt();
		result = AppointmentDao.updateAppointmentsCollectedFee(id, CollectedFee);
		System.out.println(result+" updated Successfully");
		} catch (Exception e) {
		e.printStackTrace();
		}finally {
		sc.close();
		}
		}

	

		

		

}
