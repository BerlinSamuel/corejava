package com.chainsys.miniproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.miniproject.pojo.Employee;

import java.sql.PreparedStatement;

public class EmployeeDao {
	/*
	 * getConnection() insertEmployee(Employee newemp) getEmployeeById(int id)
	 * getAllEmployees() updateEmployee(Employee newemp) deleteEmployee(int id)
	 */

	private static Connection getConnection() {
		String drivername = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "oracle";
		Connection con = null;
		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private static java.sql.Date convertToSqlDate(java.util.Date date) {
		java.sql.Date sqldate = new java.sql.Date(date.getTime());
		return sqldate;

	}

	public static int insertEmployee(Employee newemp) {
		String insertquery = "insert into employees(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,HIRE_DATE,JOB_ID,SALARY) values(?,?,?,?,?,?,?)";
		Connection con = getConnection();
		int rows = 0;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(insertquery);
			stmt.setInt(1, newemp.getEmployee_Id());
			stmt.setString(2, newemp.getFirst_name());
			stmt.setString(3, newemp.getLast_name());
			stmt.setString(4, newemp.getEmail());
			stmt.setDate(5, convertToSqlDate(newemp.getHire_date()));
			stmt.setString(6, newemp.getJob_id());
			stmt.setDouble(7, newemp.getSalary());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static int updateEmployee(Employee newemp) {
		String updatequery = "update employees set FIRST_NAME=?,LAST_NAME=?,EMAIL=?,HIRE_DATE=?,JOB_ID=?,SALARY=? where employee_id=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, newemp.getFirst_name());
			ps.setInt(7, newemp.getEmployee_Id());
			ps.setString(2, newemp.getLast_name());
			ps.setString(3, newemp.getEmail());
// convert java.util.Date to java.sql.date
			ps.setDate(4, convertToSqlDate(newemp.getHire_date()));
			ps.setString(5, newemp.getJob_id());
			ps.setFloat(6, newemp.getSalary());

			ps.executeUpdate();
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;

	}

	// to update only one column of the table
	public static int updateEmployeeFirstName(int id, String fname) {
		String updatequery = "update employees set FIRST_NAME=? where employee_id=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, fname);
			ps.setInt(2, id);
			ps.executeUpdate();
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;
	}

	public static int updateEmployeeSalary(int id, float salary) {
		String updatequery = "update employees set SALARY=? where employee_id=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(updatequery);
			ps.setDouble(1, salary);
			ps.setInt(2, id);
			ps.executeUpdate();
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static int deleteEmployee(int id) {
		String deletequery = "delete from employees where EMPLOYEE_ID=?";
		Connection con = null;
		int rows = 0;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(deletequery);
			ps.setInt(1, id);

			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

//To retrive specific Employee data using employee_id
	public static Employee getEmployeeById(int id) {
		Employee emp = null;
		String selectquery = "select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,HIRE_DATE,JOB_ID,SALARY  from Employees where employee_id = ? ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			emp = new Employee();
			if (rs.next()) {
				emp.setEmployee_Id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));
				emp.setEmail(rs.getString(4));
				java.util.Date date = new java.util.Date(rs.getDate(5).getTime());// why getTime method used ok now
																					// clear
				// date retrieved from the database will be of type java.sql.Date
				// (rs.getDate(5))
				// emp.setHire_date requires date of type java.util.Date
				// so we are converting sql Date to util Date
				// the constructor of java.util.Date requires a long value
				// so we use the getTime() which returns the sql date as a long value.
				emp.setHire_date(date);
				emp.setJob_id(rs.getString(6));
				emp.setSalary(rs.getFloat(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;

	}

//To retrieve all the data of employee
	public static List<Employee> getAllEmployee() {
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		Employee emp = null;
		String selectquery = "select EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,HIRE_DATE,JOB_ID,SALARY  from Employees ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(selectquery);
			rs = ps.executeQuery();
// emp = new Employee();
			while (rs.next()) {
				emp = new Employee();
				emp.setEmployee_Id(rs.getInt(1));
				emp.setFirst_name(rs.getString(2));
				emp.setLast_name(rs.getString(3));
				emp.setEmail(rs.getString(4));
				java.util.Date date = new java.util.Date(rs.getDate(5).getTime());
				emp.setHire_date(date);
				emp.setJob_id(rs.getString(6));
				emp.setSalary(rs.getFloat(7));

				listOfEmployees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfEmployees;
	}

}
