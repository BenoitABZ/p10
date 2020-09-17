package com.library.LibraryRestApi.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.LibraryRestApi.model.Emprunteur;

@Repository
public interface EmprunteurDao extends JpaRepository<Emprunteur, Integer> {

	Emprunteur findById(int id);

	Optional<Emprunteur> findByIdentifiant(String identifiant);

	List<Emprunteur> findAll();

	@Query("SELECT em " 
	        + "FROM Emprunteur em " 
			+ "JOIN Emprunt e ON em.id=e.emprunteur.id  "
			+ "WHERE current_date > e.dateRetour")

	List<Emprunteur> findRetardataires();

}
