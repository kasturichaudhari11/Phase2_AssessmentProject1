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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(uniqueConstraints = {
@UniqueConstraint(columnNames = "TID"),
@UniqueConstraint(columnNames = "ADDRESS") })
public class Teacher implements Serializable {
 
    private static final long serialVersionUID = -1798070786993154676L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TID", unique = true, nullable = false)
    private Integer pId;
    
    @Column(name = "NAME", unique = false, nullable = false)
    private String personName;
    
    @Column(name = "EMAIL", unique = true)
    private String email;
    
    @Column(name = "ADDRESS", unique = true, nullable = false)
    private String address;
 
    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(
    		name="TEACHER_SUBJECT",
    		joinColumns={@JoinColumn(name="TEACHER_ID", referencedColumnName="TID")},
     		inverseJoinColumns={@JoinColumn(name="SUBJECT_ID", referencedColumnName="SUBID")})
    private List<Subject> subjectList = new ArrayList<Subject>();

	public Teacher() {
    	super();
    }
    
	public Teacher(String personName, String email, String address) {
		super();
		this.personName = personName;
		this.email = email;
		this.address = address;
	}

	public Integer getpId() {
		return pId;
	}


	public void setpId(Integer pId) {
		this.pId = pId;
	}


	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subject) {
		this.subjectList = subject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}