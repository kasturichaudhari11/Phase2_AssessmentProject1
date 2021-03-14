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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "SUBID") })
public class Subject implements Serializable {
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBID", unique = true, nullable = false)
	private Integer subId;

	@Column(name = "SUBNAME", unique = true, nullable = false, length = 100)
	private String subName;

	public Subject() {
		super();
	}
	
	public Subject(String subName) {
		super();
		this.subName = subName;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}