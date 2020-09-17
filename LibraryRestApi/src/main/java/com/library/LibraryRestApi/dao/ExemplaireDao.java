package com.library.LibraryRestApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.LibraryRestApi.model.Exemplaire;

@Repository
public interface ExemplaireDao extends JpaRepository<Exemplaire, Integer> {

	Exemplaire findById(int id);

	List<Exemplaire> findAll();

}
