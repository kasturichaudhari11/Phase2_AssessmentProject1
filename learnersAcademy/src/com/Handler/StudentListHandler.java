package com.Handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.utility.HibernateUtility;

import com.entity.Student;

public class StudentListHandler {

	public void addStudent(Student student) {
		
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.save(student);
		
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Student> listAllStudents() {

		List<Student> studentList = new ArrayList<>();
		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("From Student");
		studentList = query.list();
		return studentList;
	}

	public void deleteStudent(int id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Student deleteStudent = (Student) session.get(Student.class, id);

		if (deleteStudent != null) {
			session.delete(deleteStudent);
			System.out.println("Student name is deleted");
		}

		tx.commit();
		session.close();
	}

	public Student getStudent(int id) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Student student = (Student) session.get(Student.class, id);
		session.close();
		
		return student;
	}

	public void updateStudent(Student student) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.update(student);
		
		tx.commit();
		session.close();
	}
}
