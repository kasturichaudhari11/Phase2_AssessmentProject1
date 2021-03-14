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

import com.Handler.LearnersAcademyClassHandler;
import com.Handler.SubjectListHandler;
import com.entity.LearnersAcademyClass;
import com.entity.Subject;

@WebServlet(urlPatterns = {"/listlaclass", "/addlaclass", "/newlaclass", "/deletelaclass", "/editlaclass", "/updatelaclass", "/laclasslist"})
public class LearnersAcademyClassManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void Init() {
	}

	public LearnersAcademyClassManagerServlet() {
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
			case "/newlaclass":
				showNewForm(request, response);
				break;
			case "/addlaclass":
				insertLearnersAcademyClass(request, response);
				break;
			case "/deletelaclass":
				deleteLearnersAcademyClass(request, response);
				break;
			case "/editlaclass":
				showEditForm(request, response);
				break;
			case "/updatelaclass":
				updateLearnersAcademyClass(request, response);
				break;
			case "/listlaclass":
			default:
				listLearnersAcademyClass(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLearnersAcademyClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		LearnersAcademyClassHandler lacHandler = new LearnersAcademyClassHandler();
		List<LearnersAcademyClass> listLearnersAcademyClass = lacHandler.listAllLearnersAcademyClass();
//		SubjectListHandler slHandler = new SubjectListHandler();
//		List<Subject> subjectList = slHandler.listAllSubjects();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("laclasslist.jsp");
		request.setAttribute("listLearnersAcademyClass", listLearnersAcademyClass);
		//request.setAttribute("listSubjects", subjectList);
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SubjectListHandler slHandler = new SubjectListHandler();
		List<Subject> subjectList = slHandler.listAllSubjects();
		request.setAttribute("listSubjects", subjectList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("laclassform.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		LearnersAcademyClassHandler lacHandler = new LearnersAcademyClassHandler();
		LearnersAcademyClass existingLearnersAcademyClass = lacHandler.getLearnersAcademyClass(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("laclasseditform.jsp");
		request.setAttribute("laclass", existingLearnersAcademyClass);
		dispatcher.forward(request, response);
	}

	private void insertLearnersAcademyClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String name = request.getParameter("name");
		String[] subjectIdArray = request.getParameterValues("selectSubjects");

		LearnersAcademyClassHandler lacHandler = new LearnersAcademyClassHandler();
		List<LearnersAcademyClass> currentList = lacHandler.listAllLearnersAcademyClass();

		// Find if laclass is already added to database.
		
		boolean laclassAlreadyAdded = false;
		for (LearnersAcademyClass c : currentList) {
			if (c.getClassName().equals(name)) {
				laclassAlreadyAdded = true;
				break;
			}
		}

		// If class is already added, show the error info
		if (laclassAlreadyAdded) {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("laclassform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>LearnersAcademyClass is already added.</span></body></html>");
			dispatcher.include(request, response);
			return;
		}

		// Check if laclass name is valid.
		if ((name != null) && (name.matches("^[A-Za-z0-9]+[ ]*[A-Za-z0-9]*$"))) {

			LearnersAcademyClass newLearnersAcademyClass = new LearnersAcademyClass(name);

			SubjectListHandler slHanlder = new SubjectListHandler();
			for(String subId:subjectIdArray)
			{
				newLearnersAcademyClass.getSubjectList().add(slHanlder.getSubject(Integer.parseInt(subId)));
			}
			
			lacHandler.addLearnersAcademyClass(newLearnersAcademyClass);
			
			System.out.println(newLearnersAcademyClass.toString());
			response.sendRedirect("laclasslist");

		} else {
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher = request.getRequestDispatcher("laclassform.jsp");
			out.print("<html><head></head><body><span style='color:Red'>Invalid class name.</span></body></html>");
			dispatcher.include(request, response);
		}
	}

	private void updateLearnersAcademyClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		LearnersAcademyClass laclass = new LearnersAcademyClass(name);
		laclass.setcId(id);
		
		LearnersAcademyClassHandler lacHandler = new LearnersAcademyClassHandler();
		lacHandler.updateLearnersAcademyClass(laclass);

		response.sendRedirect("laclasslist");
	}

	private void deleteLearnersAcademyClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		LearnersAcademyClassHandler lacHandler = new LearnersAcademyClassHandler();
		lacHandler.deleteLearnersAcademyClass(id);
		response.sendRedirect("laclasslist");
	}

}
