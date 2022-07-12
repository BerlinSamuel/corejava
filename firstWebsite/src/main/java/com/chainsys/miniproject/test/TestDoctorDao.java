package com.chainsys.miniproject.test;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.chainsys.miniproject.dao.DoctorDao;
import com.chainsys.miniproject.pojo.Doctor;

public class TestDoctorDao {		

	public static void testInsertDoctor() {
        
        Doctor newdoc=new Doctor();
        newdoc.setDoctor_id(101);
        newdoc.setDoctor_name("John");
        Calendar c1= Calendar.getInstance();
        java.util.Date newtime= c1.getTime();
        newdoc.setDob(newtime);
        newdoc.setSpeciality("ENT doctor");
        newdoc.setCity("CHENNAI");
        newdoc.setPhoneno(9787789683l);
        newdoc.setStandard_fees(1000);
        int result =DoctorDao.insertDoctor(newdoc);
        System.out.println(result);      
  }	
	public static void testUpdateDocotor() {
        
	     {
	            Doctor oldDoctor = DoctorDao.getDoctorById(105);
	            oldDoctor.setDoctor_name("Nishanth");
	            String s1="23/04/1986";
	            java.util.Date dob;
	            try {
	                dob = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
	                  oldDoctor.setDob(dob);
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	            oldDoctor.setCity("Chennai");
	            oldDoctor.setSpeciality("Neurologist");
	            oldDoctor.setPhoneno(9875456748l);
	            oldDoctor.setStandard_fees(4000);
	            int result = DoctorDao.updateDoctor(oldDoctor);
	            System.out.println(oldDoctor.getDoctor_id()+" "+oldDoctor.getDoctor_name()+" "+
	            oldDoctor.getStandard_fees()+" "+oldDoctor.getPhoneno()+" "+oldDoctor.getSpeciality());
	            System.out.println(result);
	     }
        }
      public static void testDeleteDoctor() {
        int result =DoctorDao.deleteDoctor(101);
        System.out.println(result);
    }   
      public static void testGetAllDoctors() {
          List<Doctor> allDoctors=DoctorDao.getAllDoctors();
          Iterator<Doctor> dociterator=allDoctors.iterator();
          while (dociterator.hasNext()) {
              Doctor doc=dociterator.next();
              System.out.println(doc.getDoctor_id()+" "+doc.getDoctor_name()+" "+doc.getPhoneno()+" "
              +doc.getSpeciality()+" "+doc.getStandard_fees()+" "+doc.getCity());
          }
         
      }
     public static void testGetDoctorById() {
        Doctor doc =DoctorDao.getDoctorById(105);
        System.out.println(doc.getDoctor_id()+" " +doc.getDoctor_name()+" "+doc.getStandard_fees());
    }
	}
