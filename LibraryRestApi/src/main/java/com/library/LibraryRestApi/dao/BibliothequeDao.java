package com.library.LibraryRestApi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.LibraryRestApi.model.Bibliotheque;


	@Repository
	public interface BibliothequeDao extends JpaRepository <Bibliotheque, Integer> {
		
		List<Bibliotheque> findAll();
		
		Bibliotheque findById(int id);
	}

