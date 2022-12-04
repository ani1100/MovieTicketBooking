package com.portal.movieticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.portal.movieticketbooking.model.SeatDtlsModel;

@Repository
public interface SeatDtlsRepository extends JpaRepository<SeatDtlsModel,Long>{
	
	@Query(value = "select * from seat_dtls_mstr where Moviedescid=?1", nativeQuery = true) 
	List<SeatDtlsModel> fetchseatdtls(Long movieid);
	
	@Query(value = "select * from seat_dtls_mstr where Moviedescid=?1 and Seatno=?2", nativeQuery = true) 
	SeatDtlsModel fetchseat(Long movieid,Integer seatno);
	
	@Query(value = "select max(Id)+1 from seat_dtls_mstr", nativeQuery = true) 
	Long fetchmaxid();

}
