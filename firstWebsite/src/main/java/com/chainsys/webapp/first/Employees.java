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
import com.chainsys.miniproject.commonutil.HTMLHelper;
import com.chainsys.miniproject.commonutil.InvalidInputDataException;
import com.chainsys.miniproject.commonutil.LogManager;
import com.chainsys.miniproject.commonutil.Validator;
import com.chainsys.miniproject.dao.EmployeeDao;
import com.chainsys.miniproject.pojo.Employee;

/**
 * Servlet implementation class Employees
 */
public class Employees extends HttpServlet {
private static final long serialVersionUID = 1L;

/**
* @see HttpServlet#HttpServlet()
*/
public Employees() {
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
List<Employee> allEmployees = EmployeeDao.getAllEmployee();
Iterator<Employee> dociterator = allEmployees.iterator();
while (dociterator.hasNext()) {
Employee doc = dociterator.next();

out.println("<hr/>");
out.println(doc.getEmployee_Id() + ", " + doc.getFirst_name() + ", " + doc.getLast_name() + " ," + doc.getEmail()
+ ", " + doc.getJob_id() + " ," + doc.getSalary() + "</p>");
}

}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
*      response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String submitvalue = request.getParameter("submit");

if (submitvalue.equals("Add Employee")) {
String source = "AddNewEmployee";
String message = "<h1>Error while " + source + "</h1>";
PrintWriter out = response.getWriter();
Employee newEmp = new Employee();

String id = request.getParameter("id");

int empId;
try {
Validator.checkStringForParseInt(id);
empId = Integer.parseInt(id);
} catch (InvalidInputDataException err) {
message += " Error in Employee id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;  // It terminates the code execution beyond this point
}

try {
Validator.checkNumberForGreaterThanZero(empId);
newEmp.setEmployee_Id(Integer.parseInt(id));
} catch (InvalidInputDataException err) {
message += " Error in Employee id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}


String Fname = request.getParameter("fname");
try {
Validator.checkStringOnly(Fname);
Validator.checklengthOfString(Fname);
newEmp.setFirst_name(Fname);
} catch (InvalidInputDataException err) {
message += " Error in Employee first name input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return; }



String Lname = request.getParameter("lname");
try {
Validator.checkStringOnly(Lname);
Validator.checklengthOfString(Lname);
newEmp.setLast_name(Lname);
} catch (InvalidInputDataException err) {
message += " Error in Employee last name input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;

}


String email = request.getParameter("email");
try {
Validator.checkEmail(email);
newEmp.setEmail(email);
} catch (InvalidInputDataException e) {
message += " Error in Employee email input: <p/>";
String errorPage = ExceptionManager.handleException(e, source, message);
out.print(errorPage);
return;
}



String jobid = request.getParameter("jobid");
try {
Validator.checkJobId(jobid);
newEmp.setJob_id(jobid);
} catch (InvalidInputDataException err) {
message += " Error in Employee job_id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;}


String sDate = request.getParameter("hdate");
Date date = null;
try {
date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

} catch (ParseException e) {
message += " Error in Employee hire_date input: <p/>";
String errorPage = ExceptionManager.handleException(e, source, message);
out.print(errorPage);
return;
}
try {
Validator.checkDateFormat(sDate);
newEmp.setHire_date(date);
} catch (InvalidInputDataException err) {
message += " Error in Employee hire_date input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return; }


String salary = request.getParameter("salary");
Float fsalary;
try {
Validator.checkStringForParseFloat(salary);
fsalary = Float.parseFloat(salary);

} catch (InvalidInputDataException err) {
message += " Error in Employee salary input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}
try {
Validator.checkSalLimit(fsalary);
newEmp.setSalary(fsalary);
} catch (InvalidInputDataException err) {
message += " Error in Employee salary input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}
int result = EmployeeDao.insertEmployee(newEmp);

out.println("<div> Add New Employee: " + result + "</div>");// do not give object only browser response to
// string

}

else if (submitvalue.equals("Update Employee")) {
doPut(request, response);
} else if (submitvalue.equals("Delete Employee")) {
doDelete(request, response);
}

}

protected void doPut(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
System.out.println("--put--");
String source = "UpdateEmployee";
String message = "<h1>Error while " + source + "</h1>";
PrintWriter out = response.getWriter();

Employee oldEmp = new Employee();

String id = request.getParameter("id");
int empId;
try {
Validator.checkStringForParseInt(id);
empId = Integer.parseInt(id);

} catch (InvalidInputDataException err) {
message += " Error in Employee id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;

}

try {
Validator.checkNumberForGreaterThanZero(empId);
Employee emp = EmployeeDao.getEmployeeById(empId);
if (emp == null) {
System.out.println("Employee Doesn't Exit For Id" + empId);
return;
}
oldEmp.setEmployee_Id(empId);
} catch (InvalidInputDataException err) {
message += " Error in Employee id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}

String Fname = request.getParameter("fname");
try {
Validator.checkStringOnly(Fname);
Validator.checklengthOfString(Fname);
oldEmp.setFirst_name(Fname);
} catch (InvalidInputDataException err1) {
message += " Error in Employee FirstName input: <p/>";
String errorPage = ExceptionManager.handleException(err1, source, message);
out.print(errorPage);
return;

}

String Lname = request.getParameter("lname");
try {
Validator.checkStringOnly(Lname);
} catch (InvalidInputDataException err1) {
message += " Error in Employee LastName input: <p/>";
String errorPage = ExceptionManager.handleException(err1, source, message);
out.print(errorPage);
return; }

oldEmp.setLast_name(Lname);

String email = request.getParameter("email");
try {
Validator.checkEmail(email);
} catch (InvalidInputDataException e) {
message += " Error in Employee email input: <p/>";
String errorPage = ExceptionManager.handleException(e, source, message);
out.print(errorPage);
return;
}
oldEmp.setEmail(email);

String jobid = request.getParameter("jobid");
try {
Validator.checkJobId(jobid);
oldEmp.setJob_id(jobid);
} catch (InvalidInputDataException err1) {
message += " Error in Employee Job_id input: <p/>";
String errorPage = ExceptionManager.handleException(err1, source, message);
out.print(errorPage);
return;
}
String sDate = request.getParameter("hdate");
Date date;
try {
date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
oldEmp.setHire_date(date);
} catch (ParseException e) {
message += " Error in Employee Hire_date input: <p/>";
String errorPage = ExceptionManager.handleException(e, source, message);
out.print(errorPage);
return; }
try {
Validator.checkDateFormat(sDate);
oldEmp.getHire_date();
} catch (InvalidInputDataException err1) {
err1.printStackTrace();
}

String sal = request.getParameter("salary");

float salParse = 0;
try {
Validator.checkStringForParseFloat(sal);
salParse = Float.parseFloat(sal);
} catch (InvalidInputDataException err1) {
message += " Error in Employee Salary input: <p/>";
String errorPage = ExceptionManager.handleException(err1, source, message);
out.print(errorPage);
return;
}

try {
Validator.checkSalLimit(salParse);
oldEmp.setSalary(salParse);
} catch (InvalidInputDataException er) {
message += " Error in Employee Salary input: <p/>";
String errorPage = ExceptionManager.handleException(er, source, message);
out.print(errorPage);
return;
}

int result = EmployeeDao.updateEmployee(oldEmp);
out.println("<div> Update new Employee" + result + "</div> ");
}

protected void doDelete(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
String source = "Delete Employee";
String message = "<h1>Error while " + source + "</h1>";

PrintWriter out = response.getWriter();
String s1 = request.getParameter("emp_id");
int empId = 0;
try {
Validator.checkStringForParseInt(s1);
empId = Integer.parseInt(s1);

} catch (InvalidInputDataException err) {
message += " Error in Employee Emp_id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}

try {
EmployeeDao.getEmployeeById(empId);
Validator.checkNumberForGreaterThanZero(empId);
} catch (InvalidInputDataException err) {
message += " Error in Employee Emp_id input: <p/>";
String errorPage = ExceptionManager.handleException(err, source, message);
out.print(errorPage);
return;
}
int result = EmployeeDao.deleteEmployee(empId);
out.println("<div> Delete The Employee " + result + "<div>");

}

}