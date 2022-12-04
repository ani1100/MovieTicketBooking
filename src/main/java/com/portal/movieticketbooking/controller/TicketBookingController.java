package com.portal.movieticketbooking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.portal.movieticketbooking.model.MovieDescModel;
import com.portal.movieticketbooking.model.SeatDtlsModel;
import com.portal.movieticketbooking.pojo.MovieInfoPojo;
import com.portal.movieticketbooking.pojo.Savepojo;
import com.portal.movieticketbooking.pojo.Theatreinfopojo;
import com.portal.movieticketbooking.service.TicketBookingSrvc;

@RestController
public class TicketBookingController {
	
	@Autowired
	TicketBookingSrvc srvc;
	
	@GetMapping("/getactivemovies")
	public List<String> getactivemovies()
	{
		return(srvc.fetchactivemovie());
	}
	
	@PostMapping("/fetchtheatresbymovieanddate")
	public List<MovieDescModel> fetchtheatresbymovieanddate(@RequestBody Theatreinfopojo pojo)
	{
		return(srvc.fetchtheatresbyname(pojo.getThname(),pojo.reqpojo.getMoviename(),pojo.reqpojo.getDate(),pojo.reqpojo.getTime()));
	}
	
	@PostMapping("/fetchseatdtls")
	public List<SeatDtlsModel> fetchseatdtls(@RequestBody MovieDescModel moviedtls)
	{
		return(srvc.fetchseatdtls(moviedtls));
	}

	@PostMapping("/savedetails")
	public List<String> savedetails(@RequestBody Savepojo pojo)
	{
		return(srvc.savedetails(pojo));
	}
	
	@PostMapping("/gettheatres")
	public List<String> fetchtheatres(@RequestBody MovieInfoPojo reqpojo)
	{
		return(srvc.fetchtheatres(reqpojo.getMoviename(),reqpojo.getDate(),reqpojo.getTime()));
	}
	
}
