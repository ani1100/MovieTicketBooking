package com.portal.movieticketbooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "seat_dtls_mstr")
public class SeatDtlsModel {
	@Id
	@Column(name = "Id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "Moviedescid", nullable = false)
	private Long moviedescid;
	
	@Column(name = "Seatno", nullable = false)
	private int seatno;

	
	@Column(name = "Status", nullable = false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMoviedescid() {
		return moviedescid;
	}

	public void setMoviedescid(Long moviedescid) {
		this.moviedescid = moviedescid;
	}


	public int getSeatno() {
		return seatno;
	}

	public void setSeatno(int seatno) {
		this.seatno = seatno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
