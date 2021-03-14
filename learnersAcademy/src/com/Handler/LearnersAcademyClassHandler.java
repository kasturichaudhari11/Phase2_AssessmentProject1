package com.Handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.utility.HibernateUtility;
import com.entity.LearnersAcademyClass;

public class LearnersAcademyClassHandler {

	public void addLearnersAcademyClass(LearnersAcademyClass laClass) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.save(laClass);

		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<LearnersAcademyClass> listAllLearnersAcademyClass() {

		List<LearnersAcademyClass> LearnersAcademyClassList = new ArrayList<>();
		Session session = HibernateUtility.getSession();
		Query query = session.createQuery("From LearnersAcademyClass");
		LearnersAcademyClassList = query.list();
		return LearnersAcademyClassList;
	}

	public void deleteLearnersAcademyClass(int id) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		LearnersAcademyClass deletelaClass = (LearnersAcademyClass) session.get(LearnersAcademyClass.class, id);

		if (deletelaClass != null) {
			session.delete(deletelaClass);
			System.out.println("LearnersAcademyClass is deleted");
		}

		tx.commit();
		session.close();
	}

	public LearnersAcademyClass getLearnersAcademyClass(int id) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		LearnersAcademyClass LearnersAcademyClass = (LearnersAcademyClass) session.get(LearnersAcademyClass.class, id);
		session.close();

		return LearnersAcademyClass;
	}

	public void updateLearnersAcademyClass(LearnersAcademyClass LearnersAcademyClass) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		session.update(LearnersAcademyClass);

		tx.commit();
		session.close();
	}
}
