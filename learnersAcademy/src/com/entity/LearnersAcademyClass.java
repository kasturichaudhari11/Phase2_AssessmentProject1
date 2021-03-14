package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(uniqueConstraints = {
@UniqueConstraint(columnNames = "CID"),
@UniqueConstraint(columnNames = "NAME") })
public class LearnersAcademyClass implements Serializable {
 
    private static final long serialVersionUID = -8398070787333154675L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CID", unique = true, nullable = false)
    private Integer cId;
    
    @Column(name = "NAME", unique = true, nullable = false)
    private String className;
 
    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinTable(
    		name="LACLASS_SUBJECT",
    		joinColumns={@JoinColumn(name="LACLASS_ID", referencedColumnName="CID")},
     		inverseJoinColumns={@JoinColumn(name="SUBJECT_ID", referencedColumnName="SUBID")})
    private List<Subject> subjectList = new ArrayList<Subject>();

	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "CLASS_STUDENT")
	@JoinTable(
	    		name="LACLASS_STUDENT",
	    		joinColumns={@JoinColumn(name="LACLASS_ID", referencedColumnName="CID")},
	     		inverseJoinColumns={@JoinColumn(name="STUDENT_ID", referencedColumnName="SID")})
	List<Student> studentList = new ArrayList<Student>();
    
	public LearnersAcademyClass() {
    	super();
    }
    
	public LearnersAcademyClass(String className) {
		super();
		this.className = className;
	}

    public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Integer getcId() {
		return cId;
	}


	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}


	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LearnersAcademyClass [cId=" + cId + ", className=" + className + ", subjectList=" + subjectList
				+ ", studentList=" + studentList + ", getClassName()=" + getClassName() + ", getStudentList()="
				+ getStudentList() + ", getcId()=" + getcId() + ", getSubjectList()=" + getSubjectList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


}