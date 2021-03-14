package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Handler.SubjectListHandler;
import com.entity.Subject;

@WebServlet(urlPatterns = {"/listsubject", "/addsubject", "/newsubject", "/deletesubject", "/editsubject", "/updatesubject", "/subjectlist"})
public class SubjectManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void Init() {
	}

	public SubjectManagerServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newsubject":
				showNewForm(request, response);
				break;
			case "/addsubject":
				insertSubject(request, response);
				break;
			case "/deletesubject":
				deleteSubject(request, response);
				break;
			case "/editsubject":
				showEditForm(request, response);
				break;
			case "/updatesubject":
				updateSubject(request, response);
				break;
			case "/listsubject":
			default:
				listSubject(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		SubjectListHandler slhandler = new SubjectListHandler();
		List<Subject> listSubject = slhandler.listAllSubjects();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("subjectlist.jsp");
		request.setAttribute("listSubjects", listSubject);
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("subjectform.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		SubjectListHandler slhandler = new SubjectListHandler();
		Subject existingSubject = slhandler.getSubject(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("subjecteditform.jsp");
		request.setAttribute("subject", existingSubject);
		dispatcher.forward(request, response);
	}

	private void insertSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String name = request.getParameter("subjectname");
		SubjectListHandler slhandler = new SubjectListHandler();
		List<Subject> currentList = slhandler.listAllSubjects();

		// Find if subject is already added to database. One subject can only be
		// assigned to one class
		boolean subjectAlreadyAdded = false;
		for (Subject s : currentList) {
			if (s.getSubName().equals(name)) {
				subjectAlreadyAdded = true;
				break;
			}
		}

		// If subject is already added, show the error info
		if (subjectAlreadyAdded) {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("subjectform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Subject is already added.</span></body></html>");
			dispatcher.include(request, response);
			return;
		}

		// Check if subject name is valid. Alphanumeric followed by zero or more spaces
		// and alphanumerics is allowed
		if ((name != null) && (name.matches("^[A-Za-z0-9]+[ ]*[A-Za-z0-9- ]*$"))) {

			Subject newSubject = new Subject(name);

			slhandler.addSubject(newSubject);
			response.sendRedirect("subjectlist");

		} else {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("subjectform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Invalid subject name.</span></body></html>");
			dispatcher.include(request, response);
		}
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("subjectname");

		Subject subject = new Subject(name);
		subject.setSubId(id);
		
		SubjectListHandler slhandler = new SubjectListHandler();
		slhandler.updateSubject(subject);

		response.sendRedirect("subjectlist");
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SubjectListHandler slhandler = new SubjectListHandler();
		slhandler.deleteSubject(id);
		response.sendRedirect("subjectlist");
	}

}
