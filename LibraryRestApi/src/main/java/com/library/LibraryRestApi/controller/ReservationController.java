package com.library.LibraryRestApi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationDao reservationDao;

	@GetMapping(value = "/Search/Reservations/{emprunteurId}")
	public List<ReservationDto> getReservationsEmprunteur(@PathVariable("emprunteurId") int emprunteurId) {

		List<Reservation> reservations = reservationDao.findAll();

		List<ReservationDto> reservationsBuff = new ArrayList<ReservationDto>();

		for (Reservation reservation : reservations) {

			int id = reservation.getEmprunteur().getId();

			if (id == emprunteurId) {

				ReservationDto reservationDto = new ReservationDto();

				reservationDto.setId(reservation.getId());

				reservationDto.setDateReservation(reservation.getDateReservation());

				Set<Reservation> reservationsOuvrage = reservation.getOuvrage().getReservations();

				for (Reservation reservationOuvrage : reservationsOuvrage) {

					int count = 1;

					count++;

					if (id == reservationOuvrage.getEmprunteur().getId()) {

						reservationDto.setPositionListe(count);
					}
				}

				reservationsBuff.add(reservationDto);

			}

		}

		return reservationsBuff;
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
	public ReservationDto ajouterReservation(@RequestBody Reservation reservation) {

		ReservationDto reservationDto = new ReservationDto();

		Set<Emprunt> emprunts = reservation.getEmprunteur().getEmprunts();

		for (Emprunt emprunt : emprunts) {

			if ((reservation.getOuvrage()).equals(emprunt.getExemplaire().getOuvrage())) {
				reservationDto.setAutorisation(false);

				reservationDto.setMessage("Vous ne pouvez reserver un ouvrage que vous avez deja emprunté");

				return reservationDto;
			}
		}
		
		if (reservation.getOuvrage().getDisponibilite()==true) {

			reservationDto.setAutorisation(false);

			reservationDto.setMessage("Cet ouvrage est disponible, inutile de le reserver");

			return reservationDto;

		}

		if (reservation.getOuvrage().getReservations().size() >= 2 * reservation.getOuvrage().getExemplaires().size()) {

			reservationDto.setAutorisation(false);

			reservationDto.setMessage("La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage");

			return reservationDto;

		}
		reservationDto.setAutorisation(true);

		reservation.setDateReservation(LocalDate.now());

		reservationDto.setMessage("Vous avez reservé l'ouvrage");

		reservationDao.save(reservation);

		return reservationDto;

	}

	@DeleteMapping(value = "/Reservationss/{reservationId}")
	public void supprimerReservation(@PathVariable int id) {

		reservationDao.deleteById(id);
	}

}
