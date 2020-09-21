package com.library.LibraryRestApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.LibraryRestApi.model.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

	List<Reservation> findAll();

	Reservation findById(int id);
}
