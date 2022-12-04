package com.portal.movieticketbooking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.movieticketbooking.model.MovieDescModel;



@Repository
public interface MovieDescRepository extends JpaRepository<MovieDescModel,Long>
{	
	@Query(value = "select distinct Moviename from movie_desc_mstr where Status='Y'", nativeQuery = true) 
	List<String> fetchmovies();
	
	@Query(value = "select distinct Theatrename from movie_desc_mstr where Status='Y' and Moviename=?1 and Startdate=?2 and Starttime>=?3", nativeQuery = true) 
	List<String> fetchtheatres(String moviename,String date,String time);
	
	@Transactional
	@Modifying
	@Query(value = "update movie_desc_mstr set Status='C' where Enddate<=?1 and Status='R' and Endtime<=?2", nativeQuery = true) 
	void autocompletion(String date,String time);
	
	@Transactional
	@Modifying
	@Query(value = "update movie_desc_mstr set Status='R' where Startdate<=?1 and Status='Y' and Starttime<=?2", nativeQuery = true) 
	void autorunning(String date,String time);
	
	@Query(value = "select * from movie_desc_mstr where Status='Y' and Moviename=?1 and Startdate=?2 and Starttime>=?3 and Theatrename=?4", nativeQuery = true) 
	List<MovieDescModel> fetchtheatresbyname(String moviename,String date,String time,String thname);
	
	@Query(value = "select * from movie_desc_mstr where Status='Y'", nativeQuery = true) 
	List<MovieDescModel> fetchallacticemovies();
	
	@Query(value = "select max(Id)+1 from movie_desc_mstr", nativeQuery = true) 
	Long fetchmaxid();

}
