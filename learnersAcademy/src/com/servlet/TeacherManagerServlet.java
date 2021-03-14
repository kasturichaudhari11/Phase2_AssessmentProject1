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

import com.Handler.TeacherListHandler;
import com.entity.Teacher;

@WebServlet(urlPatterns = {"/listteacher", "/addteacher", "/newteacher", "/deleteteacher", "/editteacher", "/updateteacher", "/teacherlist"})
public class TeacherManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherManagerServlet() {
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
			case "/newteacher":
				showNewForm(request, response);
				break;
			case "/addteacher":
				insertTeacher(request, response);
				break;
			case "/deleteteacher":
				deleteTeacher(request, response);
				break;
			case "/editteacher":
				showEditForm(request, response);
				break;
			case "/updateteacher":
				updateTeacher(request, response);
				break;
			case "/listteacher":
			default:
				listTeacher(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		TeacherListHandler tlhandler = new TeacherListHandler();
		List<Teacher> listTeacher = tlhandler.listAllTeachers();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacherlist.jsp");
		request.setAttribute("listTeachers", listTeacher);
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("teacherform.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		TeacherListHandler tlhandler = new TeacherListHandler();
		Teacher existingTeacher = tlhandler.getTeacher(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("teachereditform.jsp");
		request.setAttribute("teacher", existingTeacher);
		dispatcher.forward(request, response);
	}

	private void insertTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		TeacherListHandler tlhandler = new TeacherListHandler();
		List<Teacher> currentList = tlhandler.listAllTeachers();

		// Find if teacher is already added to database. One teacher can only be
		// assigned to one class
		boolean teacherAlreadyAdded = false;
		for (Teacher t : currentList) {
			if (t.getPersonName().equals(name) && t.getEmail().equals(email) && t.getAddress().equals(address)) {
				teacherAlreadyAdded = true;
				break;
			}
		}

		// If teacher is already added, show the error info
		if (teacherAlreadyAdded) {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacherform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Teacher is already added.</span></body></html>");
			dispatcher.include(request, response);
			return;
		}

		// Check if teacher name is valid. Letters followed by zero or more spaces and letters is allowed
		if ((name != null) && (name.matches("^[A-Za-z]+[ ]*[A-Za-z]*$"))) {

			Teacher newTeacher = new Teacher(name, email, address);

			tlhandler.addTeacher(newTeacher);
			response.sendRedirect("teacherlist");

		} else {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacherform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Invalid teacher name.</span></body></html>");
			dispatcher.include(request, response);
		}
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		Teacher teacher = new Teacher(name, email, address);
		teacher.setpId(id);
		
		TeacherListHandler tlhandler = new TeacherListHandler();
		tlhandler.updateTeacher(teacher);

		response.sendRedirect("teacherlist");
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TeacherListHandler tlhandler = new TeacherListHandler();
		tlhandler.deleteTeacher(id);
		response.sendRedirect("teacherlist");
	}

}
