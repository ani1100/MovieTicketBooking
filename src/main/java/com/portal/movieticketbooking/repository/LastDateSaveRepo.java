package com.portal.movieticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.movieticketbooking.model.MovieDescModel;
import com.portal.movieticketbooking.model.SchModel;

@Repository
public interface LastDateSaveRepo extends JpaRepository<SchModel,Long> {

}
