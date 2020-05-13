package com.library.LibraryRestApi.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.model.Emprunteur;

@RestController
public class EmprunteurControlleur {
	
	   @Autowired	   
	   public EmprunteurDao emprunteurDao;
	   
	   @Autowired
		PasswordEncoder passwordEncoder;
	   
	   @GetMapping(value= "/Emprunteurs/retardataires")
	   public List<Emprunteur> getEmprunteursRetardataires() {
		   
		   List<Emprunteur> emprunteurs = emprunteurDao.findRetardataires();
		   
		   Emprunteur emprunteur=emprunteurs.get(0);
		   
		   System.out.println(emprunteur.getNom());
		   
		   return emprunteurs;
		    }
	
	
	   @GetMapping(value = "/Emprunteurs")   
	   public List<Emprunteur> getEmprunteurs() {
	   
	   List<Emprunteur> emprunteurs = emprunteurDao.findAll();
	   
	   return emprunteurs;
	    }
	   	   
	   @GetMapping(value = "/Emprunteurs/{emprunteurId}")
	   public Emprunteur getEmprunteur(@PathVariable("emprunteurId") String emprunteurId) {
		   
		   Emprunteur emprunteur = emprunteurDao.findByIdentifiant(emprunteurId).get();
		   
		   return emprunteur;
	   }
	   
	   @PostMapping(value = "/Emprunteurs")
	   public void ajouterEmprunteur(@RequestBody Emprunteur emprunteur) {
		   
		  
		   String password = passwordEncoder.encode(emprunteur.getMotDePasse());
		   
		   emprunteur.setMotDePasse(password);
		   	   
		   emprunteurDao.save(emprunteur);
	   }
	   
	   @DeleteMapping(value ="/Emprunteurs/{emprunteurId}")
	   public void supprimerEmprunteur(@PathVariable("emprunteurId") Integer emprunteurId) {
	   		   
			  emprunteurDao.deleteById(emprunteurId);
}
	
}
