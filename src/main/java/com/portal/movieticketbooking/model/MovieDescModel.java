package com.portal.movieticketbooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_desc_mstr")
public class MovieDescModel {
	@Id
	@Column(name = "Id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "Moviename", nullable = false)
	private String moviename;
	
	@Column(name = "Theatrename", nullable = false)
	private String theatrename;
	
	@Column(name = "Startdate", nullable = false)
	private String startdate;
	
	@Column(name = "Starttime", nullable = false)
	private String starttime;
	
	@Column(name = "Enddate",nullable = false)
	private String enddate;
	
	@Column(name = "Endtime", nullable = false)
	private String endtime;
	
	@Column(name = "Seats", nullable = false)
	private int seats;
	
	@Column(name = "Status", nullable = false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
