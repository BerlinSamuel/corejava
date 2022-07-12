package com.chainsys.webapp.second;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.chainsys.miniproject.pojo.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session=request.getSession(true);
      User userData=new User();
      userData.setUserId("berl3129");
      userData.setPassword("berlinsamuel@gmail.com");
      session.setAttribute("user", userData);
      //value bound() in the user class will be 
      response.setContentType("text/html");
      PrintWriter p= response.getWriter();
      p.print("<form method='post'><input type='text' name='txt1'><input type='submit' name='btn1' value=ok></form>");
	  p.print("<p>USER SET TO SESSION !!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  response.setContentType("text/html");
	      PrintWriter p= response.getWriter();
	      HttpSession session=request.getSession(false);
	      if(session==null) {
	    	  p.print("SESSION NOT CREATED");
	    	  return;
	      }else {
	    	  User userData=(User)session.getAttribute("user");
	    	  if(userData==null) {
	    		  p.print("OBJECT REMOVED FROM SESSION ");
	    		  return;
	    	  }
	    	  p.print("<p> username:" +userData.getUserId());
	    	  p.print("<p> Password:" +userData.getPassword());
	    	  session.removeAttribute("user");
	    	  //
	      }
	}

}
