package com.library.LibraryRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.business.EmpruntBusiness;
import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.model.Emprunt;

@RestController
public class EmpruntController {

	@Autowired
	EmpruntDao empruntDao;

	@Autowired
	EmpruntBusiness empruntBusiness;

	@GetMapping(value = "/Emprunts")
	public List<Emprunt> getEmprunts() {

		List<Emprunt> emprunts = empruntDao.findAll();

		return emprunts;
	}

	@GetMapping(value = "/Emprunt/{empruntId}")
	public Emprunt getEmprunt(@PathVariable("empruntId") int empruntId) {

		Emprunt emprunt = empruntDao.findById(empruntId);

		return emprunt;
	}

	@GetMapping(value = "/Search/Emprunts/{emprunteurId}")
	public List<Emprunt> getEmpruntsEmprunteur(@PathVariable("emprunteurId") int emprunteurId) {

		List<Emprunt> emprunts = empruntBusiness.getEmpruntsEmprunteur(emprunteurId);

		return emprunts;

	}

	@PostMapping(value = "/Pret")
	public void ajouterEmprunt(@RequestBody Emprunt emprunt) {

		empruntBusiness.ajouterEmprunt(emprunt);
	}

	@GetMapping(value = "/Retour/{exemplaireId}")
	public void retourEmprunt(@PathVariable("exemplaireId") int exemplaireId) {

		empruntBusiness.retourEmprunt(exemplaireId);

	}

	@GetMapping(value = "/Prolongation/{empruntId}")
	public void prolongerEmprunt(@PathVariable("empruntId") int empruntId) {

		empruntBusiness.prolonger(empruntId);

	}

	@DeleteMapping(value = "/Emprunts/{empruntId}")
	public void supprimerEmprunt(@PathVariable int id) {

		empruntDao.deleteById(id);
	}

}
