package com.library.LibraryRestApi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class EmprunteurControlleur {

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(value = "/Emprunteurs/retardataires")
	public List<Emprunteur> getEmprunteursRetardataires() {

		List<Emprunteur> emprunteurs = emprunteurDao.findRetardataires();

		Emprunteur emprunteur = emprunteurs.get(0);

		System.out.println(emprunteur.getNom());

		return emprunteurs;
	}

	@GetMapping(value = "/Emprunteurs/notifyEmprunteur")
	public List<Emprunteur> notifyEmprunteur() {

		List<Ouvrage> ouvrages = ouvrageDao.findToNotify();

		List<Emprunteur> emprunteursNotified = new ArrayList<>();

		for (Ouvrage ouvrage : ouvrages) {

			int count = ouvrage.getExemplaires().size();

			List<Reservation> reservationsOuvrage = ouvrage.getReservations();

			Collections.sort(reservationsOuvrage, new Comparator<Reservation>() {
				public int compare(Reservation r1, Reservation r2) {
					return (r2.getDateReservation()).compareTo(r1.getDateReservation());
				}
			});

			for (Reservation reservation : reservationsOuvrage) {

				count--;

				if (count > 0) {

					reservation.setNotification(true);

					Date date = new Date();

					reservation.setDateNotification(date);

					reservationDao.save(reservation);

					Emprunteur emprunteur = reservation.getEmprunteur();

					emprunteursNotified.add(emprunteur);

				} else {

					break;
				}
			}
		}

		return emprunteursNotified;

	}

	@GetMapping(value = "/Emprunteurs/checkIfBorrowed")
	public List<Emprunteur> checkIfBorrowed() {

		List<Reservation> reservations = reservationDao.findToWarn();

		List<Emprunteur> emprunteursWarned = new ArrayList<>();

		for (Reservation reservation : reservations) {

			Emprunteur emprunteur = reservation.getEmprunteur();

			Date dateNotification = reservation.getDateNotification();

			Calendar datePlus48 = Calendar.getInstance();

			datePlus48.setTime(dateNotification);

			datePlus48.add(Calendar.HOUR_OF_DAY, 48);

			if (datePlus48.getTime().after(new Date())) {

				emprunteursWarned.add(emprunteur);

				reservationDao.delete(reservation);

			}
		}

		return emprunteursWarned;

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

	@DeleteMapping(value = "/Emprunteurs/{emprunteurId}")
	public void supprimerEmprunteur(@PathVariable("emprunteurId") Integer emprunteurId) {

		emprunteurDao.deleteById(emprunteurId);
	}

}
