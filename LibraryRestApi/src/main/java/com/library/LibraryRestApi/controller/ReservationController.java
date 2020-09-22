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

import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationDao reservationDao;

	@GetMapping(value = "/Search/Reservations/{emprunteurId}")
	public List<Reservation> getReservationsEmprunteur(@PathVariable("emprunteurId") int emprunteurId) {

		List<Reservation> reservations = reservationDao.findAll();

		List<Reservation> reservationBuff = new ArrayList<Reservation>();

		for (Reservation reservation : reservations) {

			int id = reservation.getEmprunteur().getId();

			if (id == emprunteurId) {

				reservationBuff.add(reservation);

			}

		}

		return reservationBuff;
	}

	@GetMapping(value = "/Search/Reservations/{ouvrageId}")
	public List<Reservation> getReservationsOuvrage(@PathVariable("ouvrageId") int ouvrageId) {

		List<Reservation> reservations = reservationDao.findAll();

		List<Reservation> reservationBuff = new ArrayList<Reservation>();

		for (Reservation reservation : reservations) {

			int id = reservation.getOuvrage().getId();

			if (id == ouvrageId) {

				reservationBuff.add(reservation);

			}

		}

		return reservationBuff;
	}

	@PostMapping(value = "/Reservation")
	public void ajouterReservation(@RequestBody Reservation reservation) {

		reservationDao.save(reservation);

	}

	@DeleteMapping(value = "/Reservationss/{reservationId}")
	public void supprimerReservation(@PathVariable int id) {

		reservationDao.deleteById(id);
	}

}
