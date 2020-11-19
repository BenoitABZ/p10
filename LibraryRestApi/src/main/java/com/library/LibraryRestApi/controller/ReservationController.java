package com.library.LibraryRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.business.ReservationBusiness;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	ReservationBusiness reservationBusiness;

	@GetMapping(value = "/Search/Reservations/Emprunteur/{emprunteurId}")
	public List<ReservationDto> getReservationsEmprunteur(@PathVariable("emprunteurId") int emprunteurId) {

		List<ReservationDto> reservations = reservationBusiness.getReservationsEmprunteur(emprunteurId);

		return reservations;
	}

	@GetMapping(value = "/Search/Reservations/Ouvrage/{ouvrageId}")
	public List<Reservation> getReservationsOuvrage(@PathVariable("ouvrageId") int ouvrageId) {

		List<Reservation> reservations = reservationBusiness.getReservationsOuvrage(ouvrageId);

		return reservations;
	}

	@PostMapping(value = "/Reservations/CheckReservation")
	public ReservationDto checkReservation(@RequestBody ReservationDto reservationDto) {

		ReservationDto reservationDtoPerm = reservationBusiness.checkReservation(reservationDto);

		return reservationDtoPerm;

	}

	@PostMapping(value = "/Reservations")
	public ReservationDto ajouterReservation(@RequestBody ReservationDto reservationDto) {

		ReservationDto reservationDtoAdd = reservationBusiness.ajouterReservation(reservationDto);

		return reservationDtoAdd;
	}

	@GetMapping(value = "/Reservations/supprimer/{reservationId}")
	public void supprimerReservation(@PathVariable("reservationId") int reservationId) {

		reservationDao.deleteById(reservationId);
	}
}
