package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
@UniqueConstraint(columnNames = "SID"),
@UniqueConstraint(columnNames = "ADDRESS") })
public class Student implements Serializable {

	private static final long serialVersionUID = 2798070786993138476L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SID", unique = true, nullable = false)
    private Integer pId;
    
    @Column(name = "NAME", unique = false, nullable = false)
    private String personName;
    
    @Column(name = "EMAIL", unique = true)
    private String email;
    
    @Column(name = "ADDRESS", unique = true, nullable = false)
    private String address;
	
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    
	public Student() {
		
	}

	public Student(String personName, String email, String address, String dateOfBirth) {
		super();
		this.personName = personName;
		this.email = email;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
