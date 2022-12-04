package com.portal.movieticketbooking.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sch_mstr")
public class SchModel {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ldate")
	private String ldate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	
	
	

}
