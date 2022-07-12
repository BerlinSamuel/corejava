package com.chainsys.webapp.second;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo
 */
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionDemo() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   System.out.println("service");
	/*String methodName= request.getMethod();
	System.out.println(methodName);
	if(methodName.equals("Post")) {
		doPost(request,response);
	}else {
		doGet(request,response);
	}*/
		HttpSession currentSession =request.getSession(true);
	int defaultTimeOut = currentSession.getMaxInactiveInterval(); // default time out period in seconds
	//System.out.println(defaultTimeOut);
	currentSession.setMaxInactiveInterval(0);//no timeout period if value is zero or less than zero.It is infinite.
	currentSession.setMaxInactiveInterval(5*60); //5 is minutes and 60 is seconds.so total 300 seconds
	defaultTimeOut = currentSession.getMaxInactiveInterval();
	System.out.println(defaultTimeOut);
	}
}
