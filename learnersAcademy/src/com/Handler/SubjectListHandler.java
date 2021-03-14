package com.Handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.utility.HibernateUtility;

import com.entity.Subject;

public class SubjectListHandler {

	public void addSubject(Subject sub) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.save(sub);
		
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Subject> listAllSubjects() {

		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("From Subject");
		List<Subject> subjectsList = query.list();
		return subjectsList;
	}

	public void deleteSubject(int id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Subject deleteSub = (Subject) session.get(Subject.class, id);

		if (deleteSub != null) {
			session.delete(deleteSub);
			System.out.println("Subject is deleted");
		}

		tx.commit();
		session.close();
	}

	public Subject getSubject(int id) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Subject subject = (Subject) session.get(Subject.class, id);
		session.close();
		
		return subject;
	}

	public void updateSubject(Subject subject) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.update(subject);
		
		tx.commit();
		session.close();
	}

}
