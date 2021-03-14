package com.Handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Teacher;
import com.entity.Teacher;
import com.utility.HibernateUtility;

public class TeacherListHandler {

	public void addTeacher(Teacher teacher) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.save(teacher);

		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> listAllTeachers() {

		List<Teacher> teacherList = new ArrayList<>();
		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("From Teacher");
		teacherList = query.list();
		return teacherList;
	}

	public void deleteTeacher(int id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Teacher deleteTeacher = (Teacher) session.get(Teacher.class, id);

		if (deleteTeacher != null) {
			session.delete(deleteTeacher);
			System.out.println("Teacher name is deleted");
		}

		tx.commit();
		session.close();
	}


	public Teacher getTeacher(int id) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Teacher teacher = (Teacher) session.get(Teacher.class, id);
		session.close();
		
		return teacher;
	}

	public void updateTeacher(Teacher teacher) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.update(teacher);
		
		tx.commit();
		session.close();
	}
}
