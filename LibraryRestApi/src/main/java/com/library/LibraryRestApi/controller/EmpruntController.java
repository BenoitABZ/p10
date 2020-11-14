package com.library.LibraryRestApi.controller;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.ExemplaireDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class EmpruntController {

	@Autowired
	EmpruntDao empruntDao;

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	ExemplaireDao exemplaireDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	ReservationDao reservationDao;

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

		List<Emprunt> empruntsBuff = new ArrayList<>();

		List<Emprunt> emprunts = empruntDao.findAll();

		LocalDate today = LocalDate.now();

		for (Emprunt emprunt : emprunts) {

			int id = emprunt.getEmprunteur().getId();

			if (id == emprunteurId) {

				if (emprunt.getProlongation() == false && today.compareTo(emprunt.getDateEmprunt().plusDays(28)) > 0) {

					emprunt.setProlongation(true);

					empruntDao.save(emprunt);

				}

				empruntsBuff.add(emprunt);

			}

		}

		return empruntsBuff;
	}

	@PostMapping(value = "/Pret")
	public void ajouterEmprunt(@RequestBody Emprunt emprunt) {

		int emprunteurId = emprunt.getEmprunteur().getId();

		int exemplaireId = emprunt.getExemplaire().getId();

		Set<Exemplaire> exemplairesBuff = new HashSet<>();

		Emprunteur emprunteur = emprunteurDao.findById(emprunteurId);

		Exemplaire exemplaire = exemplaireDao.findById(exemplaireId);

		if (exemplaire.getEmprunt() == null) {

			Ouvrage ouvrage = exemplaire.getOuvrage();

			Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

			ouvrage.setDisponibilite(true);

			for (Exemplaire exemplaireBuff : exemplaires) {

				if (exemplaireBuff.getEmprunt() == null) {

					exemplairesBuff.add(exemplaireBuff);

				}
			}

			if (exemplairesBuff.size() == 1) {

				ouvrage.setDisponibilite(false);

			}

			List<Reservation> reservations = emprunteur.getReservations();

			System.out.println(reservations.get(0).getOuvrage().getTitre());

			System.out.println(ouvrage.getTitre());

			for (Reservation reservation : reservations) {

				System.out.println(reservation.getOuvrage().getTitre().equals(ouvrage.getTitre()));

				if (reservation.getOuvrage().getTitre().equals(ouvrage.getTitre())) {

					reservationDao.delete(reservation);

					reservations.remove(reservation);

					emprunteur.setReservations(reservations);

					break;

				}
			}

			LocalDate dateEmprunt = LocalDate.now();

			LocalDate dateRetour = dateEmprunt.plusDays(28);

			emprunt.setDateEmprunt(dateEmprunt);

			emprunt.setDateRetour(dateRetour);

			emprunt.setEmprunteur(emprunteur);

			emprunt.setExemplaire(exemplaire);

			empruntDao.save(emprunt);

		}

	}

	@GetMapping(value = "/Retour/{exemplaireId}")
	public void retourEmprunt(@PathVariable("exemplaireId") int exemplaireId) {

		Exemplaire exemplaire = exemplaireDao.findById(exemplaireId);

		Emprunt emprunt = exemplaire.getEmprunt();

		Emprunteur emprunteur = emprunt.getEmprunteur();

		Ouvrage ouvrage = exemplaire.getOuvrage();

		empruntDao.delete(emprunt);

		Set<Emprunt> emprunts = emprunteur.getEmprunts();

		if (emprunts.contains(emprunt)) {

			emprunts.remove(emprunt);

			emprunteur.setEmprunts(emprunts);

			emprunteurDao.save(emprunteur);

		}

		Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

		if (exemplaires.contains(exemplaire)) {

			exemplaires.remove(exemplaire);

			exemplaire.setEmprunt(null);

			exemplaires.add(exemplaire);
		}

		ouvrage.setDisponibilite(true);

		ouvrage.setExemplaires(exemplaires);

		ouvrageDao.save(ouvrage);

	}

	@GetMapping(value = "/Prolongation/{empruntId}")
	public void prolongerEmprunt(@PathVariable("empruntId") int empruntId) {

		Emprunt emprunt = empruntDao.findById(empruntId);

		LocalDate dateRetour = emprunt.getDateRetour();

		LocalDate dateProlongation = dateRetour.plusDays(28);

		emprunt.setDateRetour(dateProlongation);

		emprunt.setProlongation(true);

		empruntDao.save(emprunt);

	}

	@DeleteMapping(value = "/Emprunts/{empruntId}")
	public void supprimerEmprunt(@PathVariable int id) {

		empruntDao.deleteById(id);
	}

}
