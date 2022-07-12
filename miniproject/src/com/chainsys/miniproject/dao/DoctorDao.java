package com.chainsys.miniproject.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.chainsys.miniproject.pojo.Doctor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorDao {
	   
	/* getConnection()
	* insertDoctor(Doctor newdoc)
	* getDoctorById(int id)
	* getAllDoctors()
	* updateDoctors(Doctor newdoc)
	* deleteDoctor(int id)
	*/


	public static Connection getConnection() {
	Connection mycon=null;

	String driver= "oracle.jdbc.driver.OracleDriver";
	String url= "jdbc:oracle:thin:@localhost:1521:xe";
	String username="system";
	String password="oracle";

	   try {
	Class.forName(driver);
	mycon=DriverManager.getConnection(url,username,password);
	} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	   return mycon;
	}

	private static java.sql.Date convertToSql(java.util.Date date){
	java.sql.Date sqlDate= new java.sql.Date(date.getTime());
	return sqlDate ;

	}

public static int insertDoctor(Doctor newdoc) {
        
        Connection mycon= getConnection();
        PreparedStatement stmt= null;
        String insertquery="insert into DOCTOR (DOCTOR_ID,DOCTOR_NAME,DOB,SPECIALITY,CITY,PHONENO,STANDARD_FEES) values (?,?,?,?,?,?,?)";
        int rows=0;
        try {
            stmt=mycon.prepareStatement(insertquery);
            stmt.setInt(1, newdoc.getDoctor_id());
            stmt.setString(2, newdoc.getDoctor_name());
            stmt.setDate(3, convertToSql(newdoc.getDob()));
            stmt.setString(4, newdoc.getSpeciality());
            stmt.setString(5, newdoc.getCity());
            stmt.setLong(6, newdoc.getPhoneno());
            stmt.setFloat(7, newdoc.getStandard_fees());
            rows=stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                mycon.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         return rows;
    }
	
public static int updateDoctor(Doctor newdoc) {
    String updatequery = "update doctor set DOCTOR_NAME=?,DOB=?,SPECIALITY=?,CITY=?,PHONENO=?,STANDARD_FEES=? where DOCTOR_ID=?";
    Connection con = null;
    int rows = 0;
    PreparedStatement ps = null;
    try {
        con = getConnection();
        ps = con.prepareStatement(updatequery);
        ps.setString(1, newdoc.getDoctor_name());
        ps.setInt(7, newdoc.getDoctor_id());
        ps.setDate(2, convertToSql(newdoc.getDob()));
        ps.setString(3, newdoc.getSpeciality());
        // convert java.util.Date to java.sql.date
        ps.setString(4, newdoc.getCity());
        ps.setLong(5, newdoc.getPhoneno());
        ps.setFloat(6, newdoc.getStandard_fees());
        
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
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return rows;
}

	public static  int updateDoctorName(int id,String name) {
	  String updatequery = "update DOCTOR set DOCTOR_NAME=? where DOCTOR_ID=?";
	  Connection con = null;
	  int rows = 0;
	  PreparedStatement ps = null;
	  try {
	  con = getConnection();
	  ps = con.prepareStatement(updatequery);
	  ps.setString(1, name);
	  ps.setInt(2, id);
	  rows= ps.executeUpdate();
	  rows = ps.executeUpdate();
	  } catch (SQLException e) {
	  e.printStackTrace();
	  }finally {
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

	public static int deleteDoctor(int id) {
	String deletequery = "delete from DOCTOR where DOCTOR_ID=?";
	Connection con = null;
	int rows = 0;
	PreparedStatement ps = null;
	try {
	con = getConnection();
	ps = con.prepareStatement(deletequery);
	ps.setInt(1, id);
	rows = ps.executeUpdate();
	}catch(SQLException e) {
	e.printStackTrace();
	}finally {
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
	
	public static Doctor  getDoctorById(int id) {
	Doctor  doc =null;
	String selectquery = " select DOCTOR_ID,DOCTOR_NAME,DOB,SPECIALITY,CITY,PHONENO,STANDARD_FEES from DOCTOR where DOCTOR_ID= ? ";
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs  = null;
	try {
	con = getConnection();
	ps = con.prepareStatement(selectquery);
	ps.setInt(1, id);
	rs =ps.executeQuery();
	doc= new Doctor();
	  if(rs.next()) {
	         doc.setDoctor_id(rs.getInt(1));
	         doc.setDoctor_name(rs.getString(2));
	         java.util.Date date= new java.util.Date(rs.getDate(3).getTime());
	         doc.setDob(rs.getDate(3));
	         doc.setSpeciality(rs.getString(4));
	         doc.setCity(rs.getString(5));
	         doc.setPhoneno(rs.getLong(6));
	         doc.setStandard_fees(rs.getInt(7));
	       }
	}catch(SQLException e) {
	e.printStackTrace();
	}finally {
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
	return doc;
	}
	
	public static List<Doctor> getAllDoctors() {
        Doctor  doc =null;
        String selectquery = " select DOCTOR_ID, DOCTOR_NAME,DOB,SPECIALITY,CITY,PHONENO,STANDARD_FEES from DOCTOR";
        Connection con = null;
        PreparedStatement ps = null;
         ResultSet rs  = null;
         List<Doctor> alldoctors=new ArrayList();
        try {
            con = getConnection();
            ps = con.prepareStatement(selectquery);
            
            rs =ps.executeQuery();
               while(rs.next()) {
                   doc=new Doctor();
                   doc.setDoctor_id(rs.getInt(1));
                    doc.setDoctor_name(rs.getString(2));
                    java.util.Date date= new java.util.Date(rs.getDate(3).getTime());
                   doc.setDob(date);
                  doc.setSpeciality(rs.getString(4));
                  doc.setCity(rs.getString(5));
                  doc.setPhoneno(rs.getLong(6));
                  doc.setStandard_fees(rs.getInt(7));
                  alldoctors.add(doc);
                  
                }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) 
            {
                e.printStackTrace();
            }
            try 
            {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alldoctors;
    }
}