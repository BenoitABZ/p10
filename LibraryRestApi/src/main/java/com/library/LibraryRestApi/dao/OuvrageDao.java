package com.library.LibraryRestApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.LibraryRestApi.model.Ouvrage;

@Repository
public interface OuvrageDao extends JpaRepository<Ouvrage, Integer> {

	Ouvrage findById(int id);

	Ouvrage findByTitre(String titre);

	List<Ouvrage> findAll();

	@Query("SELECT o " + "FROM Ouvrage o " + "JOIN Reservation r ON o.id=r.ouvrage.id  " + "WHERE o.disponibilite=true")

	List<Ouvrage> findToNotify();

}