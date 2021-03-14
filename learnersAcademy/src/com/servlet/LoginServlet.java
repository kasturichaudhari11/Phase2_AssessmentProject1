package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("uname");
		String password = request.getParameter("password");

		if (username.equals("admin") && password.equals("pwd")) {
			
			//response.sendRedirect("adminOperation.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("adminOperation.jsp");
			rd.forward(request, response);

		} else {
			//request.setAttribute("message", "<br/><span style=\"color:Red\">Invalid Login</span>"); // Will be available as ${message}
			//request.getRequestDispatcher("login.jsp").forward(request,response);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			out.print("<br/><span style='color:Red'>Invalid Login</span>");
		}

	}

}
