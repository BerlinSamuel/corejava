package com.chainsys.webapp.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GlobalState
 */
public class GlobalState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GlobalState() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>GlobalState</title></head><body>");
	  /*  Enumeration<String> names=request.getParameterNames();    // return collection of strings
		
		while(names.hasMoreElements()) {
			String parameterName=names.nextElement();
			System.out.println(parameterName);
			String [] values=request.getParameterValues(parameterName);
			int length=values.length;
			for(int i=0; i<length; i++) {
				System.out.println("values: "+values[i]);
			}
		}*/
		String submitvalue = request.getParameter("submit");
		ServletContext ctx = this.getServletContext(); // Get handle to servletContext maintained by Tomcat server

		if (submitvalue.equals("set")) {
			String salaryInput = request.getParameter("salary");
			ctx.setAttribute("ctxsalary", salaryInput); // storing data in serveltcontext for all users
															// ctx.attributes store the data in a collection using key
															// and value
															// ctxsalary="key" ; salaryInput="value";
			out.println("<h1>value set</h1>" + salaryInput);

		}

		else if (submitvalue.equals("fetch")) {
			//String contextSalary = ctx.getAttribute("ctxsalary").toString();
			String contextSalary=(String) ctx.getAttribute("ctxsalary");
			if(contextSalary==null) {
				out.print("<h1>SERVLET CONTEXT NOT YET SET</h1>");
			}
		     //String sessionSalary=(String) session.getAttribute("salary").toString();
			else {
			out.println("<h1>value fetched</h1>" + contextSalary); // returning value from sessionAttribute  to html
		}

			out.println("<h1>value fetched</h1>" + contextSalary); // returning value from servletContext to html
		}
		out.println(" <input type='hidden'value='50000'  name='bonus'  id='bonus'>");
		out.println("</body></html>");
	}
}

           //***** Data stored in servlet Context is multiuser,
           //and can be read on modified from all other servlets.
