package com.library.LibraryRestApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.LibraryRestApi.model.Ouvrage;

@Repository
public interface OuvrageDao extends JpaRepository<Ouvrage, Integer> {

	Ouvrage findById(int id);

	Ouvrage findByTitre(String titre);

	List<Ouvrage> findAll();

}