package com.library.LibraryRestApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.LibraryRestApi.business.OuvrageBusiness;
import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;

@ExtendWith(SpringExtension.class)
public class OuvrageTest {

	@MockBean
	OuvrageDao ouvrageDao;

	@InjectMocks
	OuvrageBusiness ouvrageBusiness;

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void setDispo_shouldUpdateDisponibiliteOuvrage_OfOuvrage() {

		Set<Exemplaire> exemplaires = new HashSet<Exemplaire>();
		Exemplaire exemplaire = new Exemplaire();
		exemplaires.add(exemplaire);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setDisponibilite(true);
		ouvrage.setExemplaires(exemplaires);

		Ouvrage ouvrageActual = ouvrageBusiness.setDispo(ouvrage);

		assertFalse(ouvrageActual.getDisponibilite());

	}

	@Test
	public void populateOuvrageDto_shouldReturnOuvrageDto_OfOuvrage() {

		Emprunt emprunt1 = new Emprunt();
		int idEmprunt1 = 1;
		emprunt1.setId(idEmprunt1);
		emprunt1.setDateRetour(LocalDate.now());

		Emprunt emprunt2 = new Emprunt();
		int idEmprunt2 = 2;
		emprunt2.setId(idEmprunt2);
		emprunt2.setDateRetour(LocalDate.now().plusDays(1));

		Set<Exemplaire> exemplaires = new HashSet<Exemplaire>();

		Exemplaire exemplaire1 = new Exemplaire();
		exemplaire1.setEmprunt(emprunt1);
		exemplaires.add(exemplaire1);

		Exemplaire exemplaire2 = new Exemplaire();
		exemplaire2.setEmprunt(emprunt2);
		exemplaires.add(exemplaire2);

		Ouvrage ouvrage = new Ouvrage();
		ouvrage.setId(1);
		ouvrage.setDisponibilite(false);
		ouvrage.setAnneeParution("test");
		ouvrage.setAuteur("test");
		ouvrage.setCategorie("test");
		ouvrage.setImage("test");
		ouvrage.setTitre("test");
		ouvrage.setResume("test");
		ouvrage.setExemplaires(exemplaires);

		OuvrageDto ouvrageDto = ouvrageBusiness.populateOuvrageDto(ouvrage);

		assertEquals(LocalDate.now(), ouvrageDto.getCloserDate());

	}

}
