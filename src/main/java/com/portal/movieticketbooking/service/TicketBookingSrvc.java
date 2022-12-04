package com.portal.movieticketbooking.service;

import java.util.List;
import com.portal.movieticketbooking.model.MovieDescModel;
import com.portal.movieticketbooking.model.SeatDtlsModel;
import com.portal.movieticketbooking.pojo.Savepojo;

public interface TicketBookingSrvc {
	public List<String> fetchactivemovie();
	public List<String> fetchtheatres(String moviename,String date,String time);
	public List<SeatDtlsModel> fetchseatdtls(MovieDescModel moviedtls);
	public List<String> savedetails(Savepojo pojo);
	public List<MovieDescModel> fetchtheatresbyname(String thname,String moviename,String date,String time);
}
