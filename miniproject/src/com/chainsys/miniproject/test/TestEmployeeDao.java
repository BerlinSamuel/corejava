package com.chainsys.miniproject.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.chainsys.miniproject.dao.EmployeeDao;
import com.chainsys.miniproject.pojo.Employee;

public class TestEmployeeDao {
	public static void testGetAllEmployees() {
		List<Employee> allEmployees=EmployeeDao.getAllEmployee();
		Iterator<Employee> empIterator = allEmployees.iterator();
		while(empIterator.hasNext()) {
			Employee emp= empIterator.next();
			System.out.println(emp.getEmployee_Id()+" "+emp.getFirst_name()+" "+emp.getSalary());
		}
	}
	public static void testGetEmployeeById() {
		Employee emp= EmployeeDao.getEmployeeById(126);
		System.out.println(emp.getEmployee_Id()+" "+emp.getFirst_name()+" "+emp.getSalary());	                                                                                       
	}
	public static void testInsertEmployee() {
        
        Employee newemp=new Employee();
        newemp.setEmployee_Id(800);
        newemp.setFirst_name("Berlin");
        newemp.setLast_name("Samuel");
        newemp.setEmail("rberlinsamuel@gmail.com");
        Calendar c1=Calendar.getInstance();
        java.util.Date newDate=c1.getTime();
        newemp.setHire_date(newDate);
        newemp.setJob_id("Java_programmer");
        newemp.setSalary(25000);
        int result=EmployeeDao.insertEmployee(newemp);
        System.out.println(result);
    }
    
    public static void testUpdateEmployee() {
        Employee oldEmployee = EmployeeDao.getEmployeeById(800);
        System.out.println(oldEmployee.getEmployee_Id()+" "+oldEmployee.getFirst_name()+" "+oldEmployee.getSalary());
        oldEmployee.setFirst_name("Sri");
        oldEmployee.setFirst_name("Kanth");
        oldEmployee.setEmail("Sri123@gmail.com");
        Calendar c1=Calendar.getInstance();
        java.util.Date newDate=c1.getTime();
        oldEmployee.setHire_date(newDate);
        oldEmployee.setJob_id("Angular");
        oldEmployee.setSalary(30000);
        int result=EmployeeDao.insertEmployee(oldEmployee);
        System.out.println(result);
    }
}
