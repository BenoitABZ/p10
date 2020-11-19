package com.library.LibraryRestApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.LibraryRestApi.business.OuvrageBusiness;
import com.library.LibraryRestApi.business.ReservationBusiness;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@ExtendWith(SpringExtension.class)
public class ReservationTest {

	@MockBean
	ReservationDao reservationDao;

	@MockBean
	OuvrageDao ouvrageDao;

	@MockBean
	EmprunteurDao emprunteurDao;

	@InjectMocks
	ReservationBusiness reservationBusiness;

	@MockBean
	OuvrageBusiness ouvrageBusiness;

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReservationsEmprunteur_shouldReturnListReservationDto_ofEmprunteurId() {

		List<ReservationDto> reservationsDto = new ArrayList<ReservationDto>();

		List<Reservation> reservations = new ArrayList<Reservation>();

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Reservation reservation = new Reservation();
		int idReservation = 1;
		reservation.setId(idReservation);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(new Ouvrage());

		reservations.add(reservation);

		OuvrageDto ouvrageDto = new OuvrageDto();

		Ouvrage ouvrage = reservation.getOuvrage();

		when(reservationDao.findAll()).thenReturn(reservations);

		doReturn(ouvrageDto).when(ouvrageBusiness).populateOuvrageDto(ouvrage);

		reservationsDto = reservationBusiness.getReservationsEmprunteur(idEmprunteur);

		assertEquals(1, reservationsDto.size());
	}

	@Test
	public void populateReservationDto_shouldReturnReservationDto_ofReservation() {

		List<Reservation> reservations = new ArrayList<Reservation>();

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Ouvrage ouvrage = new Ouvrage();

		Reservation reservation = new Reservation();
		int idReservation = 1;
		reservation.setId(idReservation);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);

		reservations.add(reservation);

		ouvrage.setReservations(reservations);

		ReservationDto reservationDto = reservationBusiness.populateReservationDto(reservation);

		assertEquals(1, reservationDto.getPositionListe());
	}

	@Test
	public void deleteIfReservation_shouldDeleteReservation_ofEmprunteurAndOuvrage() {

		List<Reservation> reservations = new ArrayList<Reservation>();

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setTitre("test");

		Reservation reservation = new Reservation();
		int idReservation = 1;
		reservation.setId(idReservation);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);

		reservations.add(reservation);

		ouvrage.setReservations(reservations);
		emprunteur.setReservations(reservations);

		Emprunteur emprunteurReal = reservationBusiness.deleteIfReservation(emprunteur, ouvrage);

		assertTrue(emprunteurReal.getReservations().isEmpty());
	}

	@Test
	public void checkReservation_shouldCheckIfNoSizeConstraint_OfReservationDto() {

		List<Reservation> reservations = new ArrayList<Reservation>();
		Set<Exemplaire> exemplaires = new HashSet<Exemplaire>();

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("test");

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setOuvrage(ouvrage);
		exemplaires.add(exemplaire);

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Emprunteur emprunteur2 = new Emprunteur();
		int idEmprunteur2 = 2;
		emprunteur.setId(idEmprunteur2);

		Emprunteur emprunteur3 = new Emprunteur();
		int idEmprunteur3 = 3;
		emprunteur.setId(idEmprunteur3);

		Reservation reservation = new Reservation();
		int idReservation = 1;
		reservation.setId(idReservation);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur2);
		reservation.setOuvrage(ouvrage);

		Reservation reservation2 = new Reservation();
		int idReservation2 = 2;
		reservation.setId(idReservation2);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur3);
		reservation.setOuvrage(ouvrage);

		reservations.add(reservation);
		reservations.add(reservation2);

		ouvrage.setReservations(reservations);
		ouvrage.setExemplaires(exemplaires);

		ReservationDto reservationDtoExpected = new ReservationDto();

		OuvrageDto ouvrageDto = new OuvrageDto();
		ouvrageDto.setId(1);
		reservationDtoExpected.setOuvrageDto(ouvrageDto);

		doReturn(ouvrage).when(ouvrageDao).findById(reservationDtoExpected.getOuvrageDto().getId());

		ReservationDto reservationDtoActual = reservationBusiness.checkReservation(reservationDtoExpected);

		assertEquals(
				"La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage",
				reservationDtoActual.getMessage());
	}

	@Test
	public void checkReservation_shouldCheckIfDispoConstraint_OfReservationDto() {

		List<Reservation> reservations = new ArrayList<Reservation>();
		Set<Exemplaire> exemplaires = new HashSet<Exemplaire>();

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setTitre("test");

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setOuvrage(ouvrage);
		exemplaires.add(exemplaire);

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Emprunteur emprunteur2 = new Emprunteur();
		int idEmprunteur2 = 2;
		emprunteur.setId(idEmprunteur2);

		Emprunteur emprunteur3 = new Emprunteur();
		int idEmprunteur3 = 3;
		emprunteur.setId(idEmprunteur3);

		Reservation reservation = new Reservation();
		int idReservation = 1;
		reservation.setId(idReservation);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur2);
		reservation.setOuvrage(ouvrage);

		Reservation reservation2 = new Reservation();
		int idReservation2 = 2;
		reservation.setId(idReservation2);
		reservation.setDateReservation(LocalDate.now());
		reservation.setEmprunteur(emprunteur3);
		reservation.setOuvrage(ouvrage);

		reservations.add(reservation);
		reservations.add(reservation2);

		ouvrage.setReservations(reservations);
		ouvrage.setExemplaires(exemplaires);

		ReservationDto reservationDtoExpected = new ReservationDto();

		OuvrageDto ouvrageDto = new OuvrageDto();
		ouvrageDto.setId(1);
		reservationDtoExpected.setOuvrageDto(ouvrageDto);

		doReturn(ouvrage).when(ouvrageDao).findById(reservationDtoExpected.getOuvrageDto().getId());

		ReservationDto reservationDtoActual = reservationBusiness.checkReservation(reservationDtoExpected);

		assertEquals("Cet ouvrage est disponible, inutile de le reserver", reservationDtoActual.getMessage());
	}

}