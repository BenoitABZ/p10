package com.library.LibraryRestApi.controller;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.BibliothequeDao;
import com.library.LibraryRestApi.dao.ExemplaireDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.model.Bibliotheque;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;

@RestController
public class ExemplaireController {
	
	@Autowired
	ExemplaireDao exemplaireDao;
	
	@Autowired
	BibliothequeDao bibliothequeDao;
	
	@Autowired
	OuvrageDao ouvrageDao;
	
	@GetMapping(value = "/Exemplaires")
	public List<Exemplaire> getExemplaires() {
		
		List<Exemplaire> exemplaires = exemplaireDao.findAll();
		
		return exemplaires;
	}
	
	@GetMapping(value = "/Exemplaires/{exemplaireId}")
	public Exemplaire getExemplaire(@PathVariable("exemplaireId") int exemplaireId) {
		
		Exemplaire exemplaire = exemplaireDao.findById(exemplaireId);
		
		return exemplaire;
	}
	
	@GetMapping(value = "/Search/Exemplaires/{ouvrageTitre}")
	public List<Exemplaire> getExemplairesOuvrage(@PathVariable String ouvrageTitre) {
		
		List<Exemplaire> exemplairesBuff = new ArrayList<>();
		
		List<Exemplaire> exemplaires = exemplaireDao.findAll();
		
		for (Exemplaire exemplaire:exemplaires) {
			
			String titre = exemplaire.getOuvrage().getTitre();
			
			if (titre==ouvrageTitre) {
				
				exemplairesBuff.add(exemplaire);
			}
							
		}
		
		return exemplairesBuff;
	}

	
	 @PostMapping(value = "/Exemplaires")
	 public void ajouterExemplaire(@RequestBody Exemplaire exemplaire) {
		
		   
		   int bibliothequeId = exemplaire.getBibliotheque().getId();
		 
		   int ouvrageId = exemplaire.getOuvrage().getId();
		  
		   Bibliotheque bibliotheque = bibliothequeDao.findById(bibliothequeId);
		   
		   Ouvrage ouvrage = ouvrageDao.findById(ouvrageId);
		   
		   exemplaire.setBibliotheque(bibliotheque);
		   
		   exemplaire.setOuvrage(ouvrage);
		 
		   exemplaireDao.save(exemplaire);
		   
	   }
	   
	   @DeleteMapping(value ="/Exemplaires/{exemplaireId}")
	   public void supprimerExemplaire(@PathVariable("exemplaireId") Integer exemplaireId) {
	   		   
		  exemplaireDao.deleteById(exemplaireId);
}
	

}
