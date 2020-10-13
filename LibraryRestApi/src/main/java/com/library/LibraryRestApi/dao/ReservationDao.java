package com.library.LibraryRestApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.LibraryRestApi.model.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

	List<Reservation> findAll();

	Reservation findById(int id);
	
	@Query("SELECT r " + "FROM Reservation r " + "WHERE r.notification=true")

	List<Reservation> findToWarn();

}
