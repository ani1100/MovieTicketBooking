package com.portal.movieticketbooking.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portal.movieticketbooking.model.MovieDescModel;
import com.portal.movieticketbooking.model.SeatDtlsModel;
import com.portal.movieticketbooking.pojo.Savepojo;
import com.portal.movieticketbooking.repository.MovieDescRepository;
import com.portal.movieticketbooking.repository.SeatDtlsRepository;

@Service
public class TicketBookingSrvcImpl implements TicketBookingSrvc{

	@Autowired
	MovieDescRepository movierepo;
	
	@Autowired
	SeatDtlsRepository seatrepo;
	
	@Override
	public List<String> fetchactivemovie() {
		List<String> resp=new ArrayList<String>();
		try
		{
			List<String> lismovies=movierepo.fetchmovies();
			if(lismovies.size()==0)
			{
				resp.add("Failed");
				resp.add("No Movies Available");
			}
			else
			{
				resp.add("Success");
				resp.addAll(lismovies);
			}		
			return resp;
		}
		catch(Exception e)
		{
			resp.clear();
			resp.add("Failed");
			resp.add("Error in processing data");
			return resp;
		}
	}

	@Override
	public List<String> fetchtheatres(String moviename, String date, String time) {
		try
		{
			List<String> lisobj=movierepo.fetchtheatres(moviename, date, time);
			if(lisobj.size()==0)
			{
				return null;
			}
			else
			{
				return lisobj;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<SeatDtlsModel> fetchseatdtls(MovieDescModel moviedtls) {
		try
		{
			List<SeatDtlsModel> seatlis= seatrepo.fetchseatdtls(moviedtls.getId());
			return seatlis;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}

	@Override
	public List<String> savedetails(Savepojo pojo) {
		List<String> resp=new ArrayList<String>();
		try
		{
			if(movierepo.getById(pojo.getMovieid()).getStatus().equals("Y"))
			{
				List<SeatDtlsModel> liseats=new ArrayList<SeatDtlsModel>();
				for(Integer st:pojo.getSeats())
				{
					SeatDtlsModel seatobj=seatrepo.fetchseat(pojo.getMovieid(),st);
					if(seatobj.getStatus().equals("Y"))
					{
						resp.add("Failed");
						resp.add("Seat Already Booked");
						return resp;
					}
					else
					{
						seatobj.setStatus("Y");
						liseats.add(seatobj);
					}
				}
				seatrepo.saveAllAndFlush(liseats);
				resp.add("Success");
				resp.add("Seat Booked Successfully");	
				return resp;
			}
			else
			{
				resp.add("Failed");
				resp.add("Booking Closed for this show");	
				return resp;
			}
			
		}
		catch(Exception e)
		{
			resp.clear();
			resp.add("Failed");
			resp.add("Error in processing data");
			return resp;
		}
	}

	@Override
	public List<MovieDescModel> fetchtheatresbyname(String thname, String moviename, String date, String time) {
		// TODO Auto-generated method stub
		try
		{
			List<MovieDescModel> lisobj=movierepo.fetchtheatresbyname(moviename, date, time,thname);
			if(lisobj.size()==0)
			{
				return null;
			}
			else
			{
				return lisobj;
			}
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
