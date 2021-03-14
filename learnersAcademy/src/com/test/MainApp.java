package com.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.LearnersAcademyClass;
import com.entity.Student;
import com.entity.Subject;
import com.entity.Teacher;
import com.utility.HibernateUtility;

public class MainApp {

	public static void main(String[] args) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		Student s1 = new Student("Thakur1", "thakur@sholay.com", "Ramgarh", "01-01-1991");
		Student s2 = new Student("Basanti2", "basanti@sholay.com", "Chandigarh", "01-01-1991");
		
		Student s3 = new Student("Amar3", "amar@aaa.com", "Ooty", "01-01-1991");
		Student s4 = new Student("Prem4", "prem@aaa.com", "Simala", "02-02-1992");
		
		Teacher t1 = new Teacher("Baburao", "baburao@herapheri.com", "Mumbai");
		Teacher t2 = new Teacher("Raju", "raju@herapheri.com", "Patna");
		Teacher t3 = new Teacher("Sham", "sham@herapheri.com", "Delhi");
		
		Subject sub1 = new Subject("Mathematics-I");
		Subject sub2 = new Subject("C Programming");
		Subject sub3 = new Subject("C++ Programming");
		Subject sub4 = new Subject("Java Programming");
		Subject sub5 = new Subject("Mathematics-II");
		
		LearnersAcademyClass c1 = new LearnersAcademyClass("Sem-I");
		LearnersAcademyClass c2 = new LearnersAcademyClass("Sem-II");
		LearnersAcademyClass c3 = new LearnersAcademyClass("Sem-III");
				
		List<Subject> subList = new ArrayList<Subject>();
		subList.add(sub1);
		subList.add(sub5);
		t1.setSubjectList(subList);
		
		List<Subject> subList2 = new ArrayList<Subject>();
		subList2.add(sub2);
		subList2.add(sub3);
		t2.setSubjectList(subList2);
		
		List<Subject> subList3 = new ArrayList<Subject>();
		subList3.add(sub4);
		t3.setSubjectList(subList3);

		List<Subject> subList4 = new ArrayList<Subject>();
		subList4.add(sub1);
		subList4.add(sub2);
		
		List<Subject> subList5 = new ArrayList<Subject>();
		subList5.add(sub3);
		subList5.add(sub5);
		
		List<Subject> subList6 = new ArrayList<Subject>();
		subList6.add(sub4);

//		List<LearnersAcademyClass> classList = new ArrayList<LearnersAcademyClass>();
//		classList.add(c1);
//		classList.add(c2);
//		t1.setClassList(classList);
//		
//		List<LearnersAcademyClass> classList2 = new ArrayList<LearnersAcademyClass>();
//		classList2.add(c3);
//		t2.setClassList(classList2);

		c1.setSubjectList(subList4);
		c2.setSubjectList(subList5);
		c3.setSubjectList(subList6);
		
		c1.getStudentList().add(s1);
		c1.getStudentList().add(s2);
		c2.getStudentList().add(s3);
		c3.getStudentList().add(s4);

		
//		
//		List<LearnersAcademyClass> classList = new ArrayList<LearnersAcademyClass>();
//		classList.add(c1);
//		classList.add(c2);
//		t1.setClassList(classList);
//		t1.getSubjectList().add(sub1);
//		t1.getSubjectList().add(sub5);
//		t2.getSubjectList().add(sub2);
//		t2.getSubjectList().add(sub3);		
//		t3.getSubjectList().add(sub4);
		
//		sub1.setTeacher(t1);
//		sub2.setTeacher(t2);
//		sub3.setTeacher(t2);
//		sub4.setTeacher(t3);
//		sub5.setTeacher(t1);
		
//		t1.getClassList().add(c1);
//		t1.getClassList().add(c2);		
//		t2.getClassList().add(c1);
//		t2.getClassList().add(c2);
//		t3.getClassList().add(c3);
		
		session.save(t1);
		session.save(t2);
		session.save(t3);		
		
		session.save(c1);
		session.save(c2);
		session.save(c3);
		
		session.save(sub1);
		session.save(sub2);
		session.save(sub3);
		session.save(sub4);
		session.save(sub5);
		
//		Student s = (Student) session.get(Student.class, 4);
//		System.out.println("Student Details:" + s);
//		
//		Student ss = (Student) session.get(Student.class, 6);
//		System.out.println("Student Details:" + ss);
//		
//		Query query2 = session.createQuery("from Teacher");
//		List<Teacher> teacherList = query2.list();
//		
//		for (Teacher t : teacherList)
//		{
//			System.out.println("Teacher Details:" + t);
//		}
//		
//		tx.commit();
//		session.close();
//		
//		session = HibernateUtility.getSession();
//		tx = session.beginTransaction();
//
////		session.delete(t1);
//		
//
//	    query2 = session.createQuery("from Teacher");
//		teacherList = query2.list();
//		
//		for (Teacher t : teacherList)
//		{
//			System.out.println("Teacher Details:" + t);
//		}
		
		tx.commit();
		session.close();

//		session = HibernateUtility.getSession();
//		tx = session.beginTransaction();
//		session.delete(t2);
//		session.delete(c2);
//		tx.commit();
//		session.close();

	}

}
