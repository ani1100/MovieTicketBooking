package com.portal.movieticketbooking.pojo;

import java.util.List;

public class Savepojo {
	List<Integer> seats;
	Long movieid;
	
	public List<Integer> getSeats() {
		return seats;
	}
	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}
	public Long getMovieid() {
		return movieid;
	}
	public void setMovieid(Long movieid) {
		this.movieid = movieid;
	}
}
