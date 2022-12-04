package com.portal.movieticketbooking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.portal.movieticketbooking.model.MovieDescModel;
import com.portal.movieticketbooking.model.SchModel;
import com.portal.movieticketbooking.model.SeatDtlsModel;
import com.portal.movieticketbooking.repository.LastDateSaveRepo;
import com.portal.movieticketbooking.repository.MovieDescRepository;
import com.portal.movieticketbooking.repository.SeatDtlsRepository;

@Configuration
@EnableScheduling
public class SchedulerSrvc {
	@Autowired
	MovieDescRepository movierepo;
	
	@Autowired
	SeatDtlsRepository seatrepo;
	
	@Autowired
	LastDateSaveRepo schrepo;
	
	@Scheduled(fixedDelay = 60000)
    public void initialization() throws InterruptedException, ParseException {
		List<String> movies= Arrays.asList("Movie1", "Movie2", "Movie3","Movie4");
		List<String> theatres= Arrays.asList("Theatre1", "Theatre2", "Theatre3","Theatre4");
		List<String> stime= Arrays.asList("09:00:00", "13:00:00","19:00:00","22:00:00");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date();  
        String date1=formatter.format(date).split(" ")[0];
        SchModel zobj=schrepo.findAll().get(0);
        if(!(zobj.getLdate().equals(date1)))
        {
	        for(String mov:movies)
	        {
	        	for(String th:theatres)
	        	{
	        		for(String st:stime)
	        		{
	        			String temp=date1.concat(" ").concat(st).concat(":00");
	        			Date datear=formatter.parse(temp);
	        			Calendar caldep = Calendar.getInstance();
					    caldep.setTime(datear);
					    caldep.add(Calendar.HOUR_OF_DAY, 3);
					    Date date3 = caldep.getTime();
					    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String date4 = format1.format(date3).split(" ")[0];
					    String time4 = format1.format(date3).split(" ")[1];
			        	MovieDescModel obj=new MovieDescModel();
			        	Long id1=movierepo.fetchmaxid();
			        	obj.setEnddate(date4);
			        	obj.setEndtime(time4);
			        	obj.setMoviename(mov);
			        	obj.setId(id1);
			        	obj.setSeats(25);
			        	obj.setStartdate(date1);
			        	obj.setStarttime(st);
			        	obj.setStatus("Y");
			        	obj.setTheatrename(th);
			        	movierepo.save(obj);
	        		}
	        	}
	        }
        }
        zobj.setLdate(date1);
        schrepo.save(zobj);
		List<MovieDescModel> lisobj=movierepo.fetchallacticemovies();
		for(MovieDescModel obj:lisobj)
		{
			List<SeatDtlsModel> lis1=seatrepo.fetchseatdtls(obj.getId());
			if(lis1.size()==0)
			{
				for(int i=1;i<=obj.getSeats();i++)
				{
					Long id1=seatrepo.fetchmaxid();
					SeatDtlsModel seatobj=new SeatDtlsModel();
					seatobj.setId(id1);
					seatobj.setMoviedescid(obj.getId());
					seatobj.setSeatno(i);
					seatobj.setStatus("N");
					seatrepo.save(seatobj);
				}	
			}
		}
        date = new Date();  
        String dt1=formatter.format(date).split(" ")[0];
        String dt2=formatter.format(date).split(" ")[1];
        movierepo.autorunning(dt1, dt2);
        movierepo.autocompletion(dt1, dt2);
        
    }
}