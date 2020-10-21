package com.library.LibraryRestApi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	EmprunteurDao emprunteurDao;

	@GetMapping(value = "/Search/Reservations/Emprunteur/{emprunteurId}")
	public List<ReservationDto> getReservationsEmprunteur(@PathVariable("emprunteurId") int emprunteurId) {

		List<Reservation> reservations = reservationDao.findAll();

		List<ReservationDto> reservationsBuff = new ArrayList<ReservationDto>();

		for (Reservation reservation : reservations) {

			int id = reservation.getEmprunteur().getId();

			if (id == emprunteurId) {

				ReservationDto reservationDto = new ReservationDto();

				reservationDto.setId(reservation.getId());

				reservationDto.setDateReservation(reservation.getDateReservation());

				Ouvrage ouvrage = reservation.getOuvrage();

				OuvrageDto ouvrageDto = new OuvrageDto();

				ouvrageDto.setId(ouvrage.getId());
				ouvrageDto.setAnneeParution(ouvrage.getAnneeParution());
				ouvrageDto.setTitre(ouvrage.getTitre());
				ouvrageDto.setAuteur(ouvrage.getAuteur());
				ouvrageDto.setResume(ouvrage.getResume());
				ouvrageDto.setCategorie(ouvrage.getCategorie());
				ouvrageDto.setImage(ouvrage.getImage());
				ouvrageDto.setDisponibilite(ouvrage.getDisponibilite());

				Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

				for (Exemplaire exemplaireBoucle1 : exemplaires) {

					LocalDate dateBoucle1 = exemplaireBoucle1.getEmprunt().getDateRetour();

					ouvrageDto.setCloserDate(dateBoucle1);

					for (Exemplaire exemplaireBoucle2 : exemplaires) {

						LocalDate dateBoucle2 = exemplaireBoucle2.getEmprunt().getDateRetour();

						if (dateBoucle1.isAfter(dateBoucle2)) {

							ouvrageDto.setCloserDate(null);

							break;
						}

					}

				}

				reservationDto.setOuvrageDto(ouvrageDto);

				List<Reservation> reservationsOuvrage = ouvrage.getReservations();

				for (Reservation reservationOuvrage : reservationsOuvrage) {

					int count = 0;

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

	@GetMapping(value = "/Search/Reservations/Ouvrage/{ouvrageId}")
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

	@PostMapping(value = "/Reservations/CheckReservation")
	public ReservationDto checkReservation(@RequestBody ReservationDto reservationDto) {

		Ouvrage ouvrage = ouvrageDao.findById(reservationDto.getOuvrageDto().getId());

		Emprunteur emprunteur = emprunteurDao.findById(reservationDto.getEmprunteurDto().getId()).get();

		Set<Emprunt> emprunts = emprunteur.getEmprunts();

		for (Emprunt emprunt : emprunts) {

			if (ouvrage.getTitre().equals(emprunt.getExemplaire().getOuvrage().getTitre())) {

				reservationDto.setAutorisation(false);

				reservationDto.setMessage("Vous ne pouvez reserver un ouvrage que vous avez deja emprunté");

				return reservationDto;
			}
		}

		if (ouvrage.getDisponibilite() == true) {

			reservationDto.setAutorisation(false);

			reservationDto.setMessage("Cet ouvrage est disponible, inutile de le reserver");

			return reservationDto;

		}

		if (ouvrage.getReservations().size() >= 2 * ouvrage.getExemplaires().size()) {

			reservationDto.setAutorisation(false);

			reservationDto.setMessage("La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage");

			return reservationDto;

		}
		
		Set<Reservation> reservations = emprunteur.getReservations();
		
		for (Reservation reservation : reservations) {
			
			if (reservation.getOuvrage().getTitre().equals(reservationDto.getOuvrageDto().getTitre())) {
				
				reservationDto.setAutorisation(false);
				
				reservationDto.setMessage( "Vous ne pouvez reserver plus de 2x le meme ouvrage");
				
				return reservationDto;
				
			}
		}
		
		reservationDto.setAutorisation(true);

		reservationDto.setDateReservation(LocalDate.now());

		reservationDto.setMessage("Vous pouvez reserver l'ouvrage");

		return reservationDto;

	}

	@PostMapping(value = "/Reservations")
	public ReservationDto ajouterReservation(@RequestBody ReservationDto reservationDto) {

		if (reservationDto.isAutorisation()) {

			Ouvrage ouvrage = ouvrageDao.findById(reservationDto.getOuvrageDto().getId());

			Emprunteur emprunteur = emprunteurDao.findById(reservationDto.getEmprunteurDto().getId()).get();

			Reservation reservation = new Reservation();

			reservation.setDateReservation(reservationDto.getDateReservation());

			reservation.setEmprunteur(emprunteur);

			reservation.setOuvrage(ouvrage);

			reservationDao.save(reservation);

			reservationDto.setMessage("L'ouvrage est reservé");

			return reservationDto;

		} else {

			reservationDto.setMessage("Vous ne pouvez pas reserver cet ouvrage");

			return reservationDto;
		}

	}

	@GetMapping(value = "/Reservations/supprimer/{reservationId}")
	public void supprimerReservation(@PathVariable("reservationId") int reservationId) {

		reservationDao.deleteById(reservationId);
	}
}
