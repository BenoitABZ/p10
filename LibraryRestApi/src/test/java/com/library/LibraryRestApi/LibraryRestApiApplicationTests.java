package com.library.LibraryRestApi;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.library.LibraryRestApi.dao.BibliothequeDao;
import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.ExemplaireDao;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dao.ReservationDao;
import com.library.LibraryRestApi.dto.EmprunteurDto;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.dto.ReservationDto;
import com.library.LibraryRestApi.model.Bibliotheque;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.Reservation;

@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
@WithMockUser(username = "utilisateur", password = "mdp", roles = "USER")
@AutoConfigureMockMvc
@Transactional
@Rollback(true)
class LibraryRestApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	EmpruntDao empruntDao;

	@Autowired
	ExemplaireDao exemplaireDao;

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	BibliothequeDao bibliothequeDao;

	@Autowired
	OuvrageDao ouvrageDao;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void getBibliotheques_shouldReturnListBibliotheques() throws Exception {

		Bibliotheque bibliotheque1 = new Bibliotheque();
		bibliotheque1.setNom("Test1Bibliotheque");
		bibliothequeDao.save(bibliotheque1);

		Bibliotheque bibliotheque2 = new Bibliotheque();
		bibliotheque2.setNom("Test2Bibliotheque");
		bibliothequeDao.save(bibliotheque2);

		mockMvc.perform(get("/Bibliotheques")).andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void getEmpruntsEmprunteur_shouldRetournListEmprunts() throws Exception {

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Emprunt emprunt = new Emprunt();

		emprunt.setEmprunteur(emprunteur);
		emprunt.setProlongation(false);
		emprunt.setDateEmprunt(LocalDate.of(2020, 03, 27));
		emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();

		empruntsEmprunteur.add(emprunt);
		emprunteur.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur);

		mockMvc.perform(get("/Search/Emprunts/" + emprunteur.getId())).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].prolongation", is(true)));
	}

	@Test
	void postEmprunt_shouldMakeAPret_OfEmprunt() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation = new Reservation();
		List<Reservation> reservationsOuv = new ArrayList<Reservation>();
		reservation.setOuvrage(ouvrage);
		reservation.setEmprunteur(emprunteur);

		reservationDao.save(reservation);

		reservationsOuv.add(reservation);
		ouvrage.setReservations(reservationsOuv);

		ouvrageDao.save(ouvrage);

		emprunteur.setReservations(reservationsOuv);

		emprunteurDao.save(emprunteur);

		int beforePretReservationSize = reservationDao.findAll().size();
		int beforePretEmpruntSize = empruntDao.findAll().size();

		int sumBefore = beforePretReservationSize + beforePretEmpruntSize;

		System.out.println(sumBefore);

		Emprunt emprunt = new Emprunt();
		emprunt.setEmprunteur(emprunteur);
		emprunt.setExemplaire(exemplaire);

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String empruntJson = ow.writeValueAsString(emprunt);
		mockMvc.perform(post("/Pret").contentType(MediaType.APPLICATION_JSON).content(empruntJson))
				.andExpect(status().is2xxSuccessful());

		int afterPretReservationSize = reservationDao.findAll().size();

		System.out.println(afterPretReservationSize);
		int afterPretEmpruntSize = empruntDao.findAll().size();

		int sumAfter = afterPretReservationSize + afterPretEmpruntSize;

		System.out.println(sumAfter);

		assertTrue(sumAfter == sumBefore);
	}

	@Test
	void retourEmprunt_shouldMakeARetour_OfExemplaireId() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Emprunt emprunt = new Emprunt();

		emprunt.setEmprunteur(emprunteur);
		emprunt.setExemplaire(exemplaire);
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();

		empruntsEmprunteur.add(emprunt);
		emprunteur.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur);

		exemplaire.setEmprunt(emprunt);

		exemplaireDao.save(exemplaire);

		int beforeRetourEmpruntList = empruntDao.findAll().size();

		System.out.println(beforeRetourEmpruntList);

		mockMvc.perform(get("/Retour/" + exemplaire.getId()));

		int afterRetourEmpruntList = empruntDao.findAll().size();

		System.out.println(afterRetourEmpruntList);

		Ouvrage ouvrageAfterRetour = ouvrageDao.findByTitre("TestOuvrage");

		assertTrue(ouvrageAfterRetour.getDisponibilite());
		assertEquals(afterRetourEmpruntList + 1, beforeRetourEmpruntList);

	}

	@Test
	void prolongerEmprunt_shouldMakeProlongation_OfEmpruntId() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Emprunt emprunt = new Emprunt();

		emprunt.setProlongation(false);
		emprunt.setEmprunteur(emprunteur);
		emprunt.setDateEmprunt(LocalDate.of(2020, 10, 27));
		emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
		emprunt.setExemplaire(exemplaire);
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();

		empruntsEmprunteur.add(emprunt);
		emprunteur.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur);

		exemplaire.setEmprunt(emprunt);

		exemplaireDao.save(exemplaire);

		mockMvc.perform(get("/Prolongation/" + emprunt.getId()));

		Emprunt empruntAfterProlongation = empruntDao.findById(emprunt.getId()).get();

		assertTrue(empruntAfterProlongation.getProlongation());

	}

	@Test
	void getRetardataires_shouldReturnListOfEmprunteurs() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Emprunt emprunt = new Emprunt();

		emprunt.setProlongation(false);
		emprunt.setEmprunteur(emprunteur);
		emprunt.setDateEmprunt(LocalDate.of(2020, 03, 27));
		emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
		emprunt.setExemplaire(exemplaire);
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();
		empruntsEmprunteur.add(emprunt);

		emprunteur.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur);

		exemplaire.setEmprunt(emprunt);

		exemplaireDao.save(exemplaire);

		mockMvc.perform(get("/Emprunteurs/retardataires")).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].identifiant", is("TestEmprunteur")));

	}

	@Test
	void getNotified_shouldReturnListOfEmprunteurs() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation = new Reservation();
		reservation.setNotification(false);
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);
		reservation.setDateReservation(LocalDate.of(2020, 10, 11));

		List<Reservation> reservationsEmprunteur = new ArrayList<>();
		reservationsEmprunteur.add(reservation);

		emprunteur.setReservations(reservationsEmprunteur);

		ouvrage.setReservations(reservationsEmprunteur);

		emprunteurDao.save(emprunteur);

		ouvrageDao.save(ouvrage);

		mockMvc.perform(get("/Emprunteurs/notifyEmprunteur")).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].identifiant", is("TestEmprunteur")))
				.andExpect(jsonPath("$[0].reservations[0].notification", is(true)));

	}

	@SuppressWarnings("deprecation")
	@Test
	void getWarned_shouldReturnListOfEmprunteurs() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation = new Reservation();
		reservation.setNotification(true);
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);
		reservation.setDateReservation(LocalDate.of(2020, 10, 10));
		reservation.setDateNotification(new Date(2020, 11, 10));

		List<Reservation> reservationsEmprunteur = new ArrayList<>();
		reservationsEmprunteur.add(reservation);

		emprunteur.setReservations(reservationsEmprunteur);

		ouvrage.setReservations(reservationsEmprunteur);

		emprunteurDao.save(emprunteur);

		ouvrageDao.save(ouvrage);

		try {
			mockMvc.perform(get("/Emprunteurs/notifyEmprunteur")).andExpect(jsonPath("$", hasSize(1)))
					.andExpect(jsonPath("$[0].identifiant", is("TestEmprunteur")))
					.andExpect(jsonPath("$[0].reservations[0]", is(null)));
			fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void addAndLogEmprunteur_shouldSaveAndLogEmprunteurInDb_OfEmprunteur() throws Exception {

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");
		emprunteur.setMotDePasse("mdptest");

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String emprunteurJson = ow.writeValueAsString(emprunteur);

		mockMvc.perform(post("/Emprunteurs").contentType(MediaType.APPLICATION_JSON).content(emprunteurJson))
				.andExpect(status().is2xxSuccessful());

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setIdentifiant(emprunteur.getIdentifiant());
		emprunteurDto.setMotDePasse(emprunteur.getMotDePasse());

		String emprunteurDtoJson = ow.writeValueAsString(emprunteurDto);

		mockMvc.perform(post("/Connexion").contentType(MediaType.APPLICATION_JSON).content(emprunteurDtoJson))
				.andExpect(jsonPath("$.identifiant", is("TestEmprunteur")))
				.andExpect(jsonPath("$.status", is("succes")));

	}

	@Test
	void getReservationsEmprunteur_shouldReturnReservationDto_OfEmprunteur() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		Emprunteur emprunteur2 = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur2");

		emprunteurDao.save(emprunteur);

		emprunteurDao.save(emprunteur2);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation = new Reservation();
		reservation.setNotification(false);
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);
		reservation.setDateReservation(LocalDate.of(2020, 10, 10));

		List<Reservation> reservationsEmprunteur = new ArrayList<>();
		reservationsEmprunteur.add(reservation);

		emprunteur.setReservations(reservationsEmprunteur);

		ouvrage.setReservations(reservationsEmprunteur);

		emprunteurDao.save(emprunteur);

		ouvrageDao.save(ouvrage);

		Emprunt emprunt = new Emprunt();

		emprunt.setProlongation(false);
		emprunt.setEmprunteur(emprunteur2);
		emprunt.setDateEmprunt(LocalDate.of(2020, 03, 27));
		emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
		emprunt.setExemplaire(exemplaire);
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();
		empruntsEmprunteur.add(emprunt);

		emprunteur2.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur2);

		exemplaire.setEmprunt(emprunt);

		exemplaireDao.save(exemplaire);

		mockMvc.perform(get("/Search/Reservations/Emprunteur/" + emprunteur.getId()))
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].ouvrageDto.titre", is("TestOuvrage")))
				.andExpect(
						jsonPath("$[0].ouvrageDto.closerDate", is(emprunt.getDateEmprunt().plusDays(28).toString())));

	}

	@Test
	void checkReservation_shouldCheckContrainteDisponibilite_OfReservationDto() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setId(emprunteur.getId());

		OuvrageDto ouvrageDto = new OuvrageDto();

		ouvrageDto.setId(ouvrage.getId());

		ReservationDto reservationDto = new ReservationDto();

		reservationDto.setOuvrageDto(ouvrageDto);

		reservationDto.setEmprunteurDto(emprunteurDto);

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String reservationDtoJson = ow.writeValueAsString(reservationDto);

		mockMvc.perform(post("/Reservations/CheckReservation").contentType(MediaType.APPLICATION_JSON)
				.content(reservationDtoJson))
				.andExpect(jsonPath("$.message", is("Cet ouvrage est disponible, inutile de le reserver")));

	}

	@Test
	void checkReservation_shouldCheckContrainteEmprunt_OfReservationDto() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setId(emprunteur.getId());

		OuvrageDto ouvrageDto = new OuvrageDto();

		ouvrageDto.setId(ouvrage.getId());

		ReservationDto reservationDto = new ReservationDto();

		reservationDto.setOuvrageDto(ouvrageDto);

		reservationDto.setEmprunteurDto(emprunteurDto);

		Emprunt emprunt = new Emprunt();

		emprunt.setProlongation(false);
		emprunt.setEmprunteur(emprunteur);
		emprunt.setDateEmprunt(LocalDate.of(2020, 03, 27));
		emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
		emprunt.setExemplaire(exemplaire);
		empruntDao.save(emprunt);

		Set<Emprunt> empruntsEmprunteur = new HashSet<>();
		empruntsEmprunteur.add(emprunt);

		emprunteur.setEmprunts(empruntsEmprunteur);

		emprunteurDao.save(emprunteur);

		exemplaire.setEmprunt(emprunt);

		exemplaireDao.save(exemplaire);

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String reservationDtoJson = ow.writeValueAsString(reservationDto);

		mockMvc.perform(post("/Reservations/CheckReservation").contentType(MediaType.APPLICATION_JSON)
				.content(reservationDtoJson))
				.andExpect(jsonPath("$.message", is("Vous ne pouvez reserver un ouvrage que vous avez deja emprunté")));

	}

	@Test
	void checkReservation_shouldCheckContrainteSize_OfReservationDto() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		Emprunteur emprunteur2 = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur2");

		Emprunteur emprunteur3 = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur3");

		emprunteurDao.save(emprunteur);

		emprunteurDao.save(emprunteur2);

		emprunteurDao.save(emprunteur3);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation2 = new Reservation();
		reservation2.setNotification(false);
		reservation2.setEmprunteur(emprunteur2);
		reservation2.setOuvrage(ouvrage);
		reservation2.setDateReservation(LocalDate.of(2020, 10, 10));

		reservationDao.save(reservation2);

		Reservation reservation3 = new Reservation();
		reservation3.setNotification(false);
		reservation3.setEmprunteur(emprunteur3);
		reservation3.setOuvrage(ouvrage);
		reservation3.setDateReservation(LocalDate.of(2020, 10, 10));

		reservationDao.save(reservation3);

		List<Reservation> reservationsOuvrage = new ArrayList<>();

		List<Reservation> reservationsEmprunteur2 = new ArrayList<>();
		reservationsEmprunteur2.add(reservation2);

		reservationsOuvrage.add(reservation2);

		emprunteur2.setReservations(reservationsEmprunteur2);

		emprunteurDao.save(emprunteur2);

		List<Reservation> reservationsEmprunteur3 = new ArrayList<>();
		reservationsEmprunteur3.add(reservation3);

		emprunteurDao.save(emprunteur3);

		reservationsOuvrage.add(reservation3);

		ouvrage.setReservations(reservationsOuvrage);

		ouvrageDao.save(ouvrage);

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setId(emprunteur.getId());

		OuvrageDto ouvrageDto = new OuvrageDto();

		ouvrageDto.setId(ouvrage.getId());

		ReservationDto reservationDto = new ReservationDto();

		reservationDto.setOuvrageDto(ouvrageDto);

		reservationDto.setEmprunteurDto(emprunteurDto);

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String reservationDtoJson = ow.writeValueAsString(reservationDto);

		mockMvc.perform(post("/Reservations/CheckReservation").contentType(MediaType.APPLICATION_JSON)
				.content(reservationDtoJson))
				.andExpect(jsonPath("$.message", is(
						"La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage")));

	}

	@Test
	void checkReservation_shouldCheckContrainteReservation_OfReservationDto() throws Exception {

		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setNom("TestBibliotheque");

		bibliothequeDao.save(bibliotheque);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setIdentifiant("TestEmprunteur");

		emprunteurDao.save(emprunteur);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(false);
		ouvrage.setTitre("TestOuvrage");

		ouvrageDao.save(ouvrage);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setEditeur("TestExemplaire");

		exemplaire.setBibliotheque(bibliotheque);
		exemplaire.setOuvrage(ouvrage);

		exemplaireDao.save(exemplaire);

		Set<Exemplaire> exemplairesBib = new HashSet<Exemplaire>();
		exemplairesBib.add(exemplaire);

		bibliotheque.setExemplaires(exemplairesBib);

		bibliothequeDao.save(bibliotheque);

		ouvrage.setExemplaires(exemplairesBib);

		ouvrageDao.save(ouvrage);

		Reservation reservation = new Reservation();
		reservation.setNotification(false);
		reservation.setEmprunteur(emprunteur);
		reservation.setOuvrage(ouvrage);
		reservation.setDateReservation(LocalDate.of(2020, 10, 10));

		reservationDao.save(reservation);

		List<Reservation> reservationsEmprunteur = new ArrayList<>();
		reservationsEmprunteur.add(reservation);

		emprunteur.setReservations(reservationsEmprunteur);

		emprunteurDao.save(emprunteur);

		ouvrage.setReservations(reservationsEmprunteur);

		ouvrageDao.save(ouvrage);

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setId(emprunteur.getId());

		OuvrageDto ouvrageDto = new OuvrageDto();

		ouvrageDto.setId(ouvrage.getId());
		ouvrageDto.setTitre(ouvrage.getTitre());

		ReservationDto reservationDto = new ReservationDto();

		reservationDto.setOuvrageDto(ouvrageDto);

		reservationDto.setEmprunteurDto(emprunteurDto);

		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String reservationDtoJson = ow.writeValueAsString(reservationDto);

		mockMvc.perform(post("/Reservations/CheckReservation").contentType(MediaType.APPLICATION_JSON)
				.content(reservationDtoJson))
				.andExpect(jsonPath("$.message", is("Vous ne pouvez reserver plus de 2x le meme ouvrage")));

	}

}
