package com.library.LibraryRestApi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.BibliothequeDao;
import com.library.LibraryRestApi.model.Bibliotheque;


@RestController
public class BibliothequeController {
	
	   @Autowired	   
	   public BibliothequeDao bibliothequeDao ;
	
	   @GetMapping(value = "/Bibliotheques")   
	   public List<Bibliotheque> getBibliotheques() {
	   
	   List<Bibliotheque> bibliotheques = bibliothequeDao.findAll();
	   
	   return bibliotheques;
	    }
	   	   
	   @GetMapping(value = "/Bibliotheques/{bibliothequeId}")
	   public Bibliotheque getBibliotheque(@PathVariable("bibliothequeId") int bibliothequeId) {
		   		   		   
		  Bibliotheque bibliotheque = bibliothequeDao.findById(bibliothequeId);
		   
		   return bibliotheque;
	   }
	   
	   @PostMapping(value = "/Bibliotheques")
	   public void ajouterBibliotheque(@RequestBody Bibliotheque bibliotheque) {
		   
		   bibliothequeDao.save(bibliotheque);
	   }
	   
	   @DeleteMapping(value ="/Bibliotheques/{bibliothequeId}")
	   public void supprimerBibliotheque(@PathVariable("bibliothequeId") int bibliothequeId) {
	   		   
			  bibliothequeDao.deleteById(bibliothequeId);
}
	   
}
