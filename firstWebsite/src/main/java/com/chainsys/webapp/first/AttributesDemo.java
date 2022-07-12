package com.chainsys.webapp.first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttributesDemo
 */
public class AttributesDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttributesDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    public String salary=null;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><head><title>Attributes</title></head><body>");
        String submitvalue = request.getParameter("submit");
//System.out.println("value "+submitvalue);
if(submitvalue.equals("set")) {
        String salaryInput = request.getParameter("salary");
salary = salaryInput; // storing data in global variable
out.println("<h1>value set</h1>" +salary);
}
//System.out.println("salary Input "+salaryInput);
else if(submitvalue.equals("fetch") ){
    out.println("<h1>value fetched</h1>" +salary);    //returning value from global variablr to html
}

        //System.out.println("Dopost called");



out.println("</body></html>");    
    }
}
/* The global variable declared in servlet is stateless(multiuser) in nature.
The data is shared with all users accessing the servlet.
The data will be available in the servlet until the servlet is destroyed.
If a user modifies the value of the global variable ,
then all other users currently using the servlet,
or users who may user the servlet in the future will get the last modified value.
Global variable are not suitable for single user state(stateful).*/