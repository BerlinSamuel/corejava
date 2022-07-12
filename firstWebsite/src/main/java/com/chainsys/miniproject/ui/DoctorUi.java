package com.chainsys.miniproject.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.chainsys.miniproject.commonutil.InvalidInputDataException;
import com.chainsys.miniproject.commonutil.Validator;
import com.chainsys.miniproject.dao.DoctorDao;
import com.chainsys.miniproject.pojo.Doctor;

public class DoctorUi {

	public static void addNewDoctor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Doctor id");
		String id = sc.nextLine();
		int docId;
		Doctor newdoctor = new Doctor();
		try {
			Validator.checkStringForParseFloat(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		try {
			Validator.checkNumberForGreaterThanZero(docId);
			newdoctor.setDoctor_id(docId);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;
		}

		System.out.println("Enter Doctor name");
		String Fname = sc.nextLine();
		try {
			Validator.checkStringOnly(Fname);
			Validator.checklengthOfString(Fname);
			newdoctor.setDoctor_name(Fname);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}

		System.out.println("Enter dob");
		String sDate = sc.nextLine();
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			newdoctor.setDob(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Validator.checkDateFormat(sDate);
			newdoctor.setDob(date);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}

		System.out.println("Enter city");
		String city = sc.nextLine();
		try {
			Validator.checkStringOnly(city);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}newdoctor.setCity(city);

		System.out.println("Enter doctor phone number");
		String phno = sc.nextLine();
		long docPhNo = 0;
		try {
			Validator.checkPhoneNumer(phno);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		try {
			Validator.checkStringForParseFloat(phno);
			docPhNo = Long.parseLong(phno);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}
		newdoctor.setPhoneno(docPhNo);
		System.out.println("Enter Speciality");
		String speciality = sc.nextLine();
		try {
			Validator.checkStringOnly(speciality);
		} catch (InvalidInputDataException e) {
			e.printStackTrace();
			
		}
		newdoctor.setSpeciality(speciality);

		System.out.println("Enter standard fees");
		String stdFees = sc.nextLine();
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(stdFees);
			stdfees1 = Float.parseFloat(stdFees);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}finally {sc.close();}
		
		newdoctor.setStandard_fees(stdfees1);
		int result = DoctorDao.insertDoctor(newdoctor);
		System.out.println(result);
	}

	public static void updateDoctor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Doctor id");
		String id = sc.nextLine();
		int docId = 0;
		Doctor newdoctor = new Doctor();
		try {
			Validator.checkStringForParseFloat(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}

		try {
			Validator.checkNumberForGreaterThanZero(docId);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}

		System.out.println("Enter Doctor name");
		String Fname = sc.nextLine();
		try {
			Validator.checkStringOnly(Fname);
			Validator.checklengthOfString(Fname);
			newdoctor.setDoctor_name(Fname);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}
		System.out.println("Enter dob");
		String sDate = sc.nextLine();
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Validator.checkDateFormat(sDate);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		newdoctor.setDob(date);

		System.out.println("Enter city");
		String city = sc.nextLine();
		try {
			Validator.checkStringOnly(city);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		newdoctor.setCity(city);
		System.out.println("Enter doctor phone number");
		String phno = sc.nextLine();
		long docPhNo = 0;
		try {
			Validator.checkPhoneNumer(phno);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}
		try {
			Validator.checkStringForParseFloat(phno);
			docPhNo = Long.parseLong(phno);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}
		newdoctor.setPhoneno(docPhNo);
		
		System.out.println("Enter Speciality");
		String speciality = sc.nextLine();
		try {
			Validator.checkStringOnly(speciality);
		} catch (InvalidInputDataException e) {
			e.printStackTrace();
			
		}
		newdoctor.setSpeciality(speciality);
		System.out.println("Enter standard fees");
		String stdFees = sc.nextLine();
		float stdfees1 = 0;
		try {
			Validator.checkStringForParseFloat(stdFees);
			stdfees1 = Float.parseFloat(stdFees);
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
		}finally {
			sc.close();
		}
		newdoctor.setStandard_fees(stdfees1);

		int result = DoctorDao.updateDoctor(newdoctor);
		System.out.println(result);
	}

	public static void deleteDoctorById() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter doctor id");
		String id = sc.nextLine();
		int docId = 0;
		
		try {
			Validator.checkStringForParseFloat(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}finally {
			sc.close();
		}
		
		int result = DoctorDao.deleteDoctor(docId);
		System.out.println(result);

	}

	public static void getDoctorById() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter doctor id");
		String id = sc.nextLine();
		int docId = 0;
		try {
			Validator.checkStringForParseFloat(id);
			docId = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}finally {
			sc.close();
		}

		Doctor doc = DoctorDao.getDoctorById(docId);
		System.out.println(doc.getDoctor_id() + " " + doc.getDoctor_name() + " " + doc.getPhoneno() + " "
				+ doc.getSpeciality() + " " + doc.getStandard_fees());

	}

	public static void getAllDoctor() {

		List<Doctor> allDoctors = DoctorDao.getAllDoctors();
		Iterator<Doctor> dociterator = allDoctors.iterator();
		while (dociterator.hasNext()) {
			Doctor doc = dociterator.next();
			System.out.println(doc.getDoctor_id() + " " + doc.getDoctor_name() + " " + doc.getPhoneno() + " "
					+ doc.getSpeciality() + " " + doc.getStandard_fees() + " " + doc.getCity());
		}

	}
//
//	public static void updateDoctorFees() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter doctor id");
//		String id = sc.nextLine();
//		int docId = 0;
//		try {
//			Validator.checkStringForParse(id);
//			docId = Integer.parseInt(id);
//
//		} catch (InvalidInputDataException err) {
//			err.printStackTrace();
//
//		}
//
//		System.out.println("Enter Doctor fees");
//		String stdFees = sc.nextLine();
//		float stdfees1 = 0;
//		try {
//			Validator.checkStringForParse(stdFees);
//			stdfees1 = Float.parseFloat(stdFees);
//		} catch (InvalidInputDataException err) {
//			err.printStackTrace();
//		}finally {
//			sc.close();
//		}
//
//		int result = DoctorDao.updateDoctorFees(docId, stdfees1);
//
//		System.out.println(result);
//	}

	public static void updateDoctorName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter doctor id");
		String id = sc.nextLine();
		int docid=0;
				try {
			Validator.checkStringForParseFloat(id);
			docid = Integer.parseInt(id);

		} catch (InvalidInputDataException err) {
			err.printStackTrace();

		}

		System.out.println("Enter DoctorName");
		String Fname = sc.nextLine();
		try {
			Validator.checkStringOnly(Fname);
			Validator.checklengthOfString(Fname);
			
		} catch (InvalidInputDataException err) {
			err.printStackTrace();
			return;

		}finally{
			sc.close();
		}
		

		int result = DoctorDao.updateDoctorName(docid,Fname);

		System.out.println(result);
	}

}
