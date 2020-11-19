package com.library.LibraryRestApi.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@Service
public class ReservationBusiness {

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	OuvrageBusiness ouvrageBusiness;

	public Emprunteur deleteIfReservation(Emprunteur emprunteur, Ouvrage ouvrage) {

		List<Reservation> reservations = emprunteur.getReservations();

		for (Reservation reservation : reservations) {

			if (reservation.getOuvrage().getTitre().equals(ouvrage.getTitre())) {

				reservationDao.delete(reservation);

				reservations.remove(reservation);

				emprunteur.setReservations(reservations);

				break;

			}
		}

		return emprunteur;
	}

	public List<ReservationDto> getReservationsEmprunteur(int emprunteurId) {

		List<Reservation> reservations = reservationDao.findAll();

		List<ReservationDto> reservationsBuff = new ArrayList<ReservationDto>();

		for (Reservation reservation : reservations) {

			int id = reservation.getEmprunteur().getId();

			if (id == emprunteurId) {

				Ouvrage ouvrage = reservation.getOuvrage();

				ReservationDto reservationDto = populateReservationDto(reservation);

				OuvrageDto ouvrageDto = ouvrageBusiness.populateOuvrageDto(ouvrage);

				reservationDto.setOuvrageDto(ouvrageDto);

				reservationsBuff.add(reservationDto);

			}

		}

		return reservationsBuff;
	}

	public List<Reservation> getReservationsOuvrage(int ouvrageId) {

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

	public ReservationDto checkReservation(ReservationDto reservationDto) {

		Ouvrage ouvrage = ouvrageDao.findById(reservationDto.getOuvrageDto().getId());
      
		Emprunteur emprunteur = null;;
		try {
			
			emprunteur = emprunteurDao.findById(reservationDto.getEmprunteurDto().getId()).get();
			
		} catch (NoSuchElementException e) {
	
			e.printStackTrace();
		}catch (NullPointerException e) {
	
			e.printStackTrace();
		}

		try {

			if (ouvrage.getDisponibilite() == true) {

				reservationDto.setAutorisation(false);

				reservationDto.setMessage("Cet ouvrage est disponible, inutile de le reserver");

				return reservationDto;

			}

			if (ouvrage.getReservations().size() >= 2 * ouvrage.getExemplaires().size()) {

				reservationDto.setAutorisation(false);

				reservationDto.setMessage(
						"La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage");

				return reservationDto;

			}

			List<Reservation> reservations = emprunteur.getReservations();

			for (Reservation reservation : reservations) {

				if (reservation.getOuvrage().getTitre().equals(reservationDto.getOuvrageDto().getTitre())) {

					reservationDto.setAutorisation(false);

					reservationDto.setMessage("Vous ne pouvez reserver plus de 2x le meme ouvrage");

					return reservationDto;

				}
			}

		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		Set<Emprunt> emprunts = emprunteur.getEmprunts();

		for (Emprunt emprunt : emprunts) {

			if (ouvrage.getTitre().equals(emprunt.getExemplaire().getOuvrage().getTitre())) {

				reservationDto.setAutorisation(false);

				reservationDto.setMessage("Vous ne pouvez reserver un ouvrage que vous avez deja emprunté");

				return reservationDto;
			}
		}

		reservationDto.setAutorisation(true);

		reservationDto.setDateReservation(LocalDate.now());

		reservationDto.setMessage("Vous pouvez reserver l'ouvrage");

		return reservationDto;

	}

	public ReservationDto ajouterReservation(ReservationDto reservationDto) {

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

	public ReservationDto populateReservationDto(Reservation reservation) {
		ReservationDto reservationDto = new ReservationDto();
		try {

			reservationDto.setId(reservation.getId());

			reservationDto.setDateReservation(reservation.getDateReservation());

			int count = calculatePositionListe(reservation.getOuvrage(), reservation.getEmprunteur().getId());

			reservationDto.setPositionListe(count);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return reservationDto;

	}

	private int calculatePositionListe(Ouvrage ouvrage, int id) {

		List<Reservation> reservationsOuvrage = ouvrage.getReservations();

		int count = 0;

		for (Reservation reservationOuvrage : reservationsOuvrage) {

			count++;

			if (id == reservationOuvrage.getEmprunteur().getId()) {

				break;
			}
		}

		return count;
	}
}
