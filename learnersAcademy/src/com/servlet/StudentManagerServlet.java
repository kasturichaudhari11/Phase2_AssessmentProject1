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

import com.Handler.StudentListHandler;
import com.entity.Student;

@WebServlet(urlPatterns = {"/liststudent", "/addstudent", "/newstudent", "/deletestudent", "/editstudent", "/updatestudent", "/studentlist"})
public class StudentManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void Init() {
	}

	public StudentManagerServlet() {
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
			case "/newstudent":
				showNewForm(request, response);
				break;
			case "/addstudent":
				insertStudent(request, response);
				break;
			case "/deletestudent":
				deleteStudent(request, response);
				break;
			case "/editstudent":
				showEditForm(request, response);
				break;
			case "/updatestudent":
				updateStudent(request, response);
				break;
			case "/liststudent":
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		StudentListHandler stlhandler = new StudentListHandler();
		List<Student> listStudent = stlhandler.listAllStudents();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentlist.jsp");
		request.setAttribute("listStudents", listStudent);
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("studentform.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		StudentListHandler stlhandler = new StudentListHandler();
		Student existingStudent = stlhandler.getStudent(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studenteditform.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);
	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String dob = request.getParameter("dob");

		StudentListHandler stlhandler = new StudentListHandler();
		List<Student> currentList = stlhandler.listAllStudents();

		// Find if student is already added to database. One student can only be assigned to one class
		boolean studentAlreadyAdded = false;
		for (Student s : currentList) {
			if (s.getPersonName().equals(name) && s.getEmail().equals(email) && s.getAddress().equals(address)) {
				studentAlreadyAdded = true;
				break;
			}
		}

		// If student is already added, show the error info
		if (studentAlreadyAdded) {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Student is already added.</span></body></html>");
			dispatcher.include(request, response);
			return;
		}

		// Check if student name is valid. Letters followed by zero or more spaces and letters is allowed
		if ((name != null) && (name.matches("^[A-Za-z]+[ ]*[A-Za-z]*$"))) {

			Student newStudent = new Student(name, email, address, dob);

			stlhandler.addStudent(newStudent);
			response.sendRedirect("studentlist");

		} else {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("studentform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Invalid student name.</span></body></html>");
			dispatcher.include(request, response);
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String dob = request.getParameter("dob");

		Student student = new Student(name, email, address, dob);
		student.setpId(id);
		
		StudentListHandler stlhandler = new StudentListHandler();
		stlhandler.updateStudent(student);

		response.sendRedirect("studentlist");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StudentListHandler stlhandler = new StudentListHandler();
		stlhandler.deleteStudent(id);
		response.sendRedirect("studentlist");
	}

}
