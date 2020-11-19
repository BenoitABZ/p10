package com.library.LibraryRestApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.LibraryRestApi.business.EmpruntBusiness;
import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;

@ExtendWith(SpringExtension.class)
public class EmpruntTest {
	
	@MockBean
	EmpruntDao empruntDao;

	@InjectMocks
	EmpruntBusiness empruntBusiness;
	
	@BeforeEach
	public void init(){
		
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void getEmpruntsEmprunteur_shouldReturnAListOfEmprunts_ofEmprunteurId() {

		List<Emprunt> empruntsExpected = new ArrayList<>();

		Emprunteur emprunteur = new Emprunteur();
		int idEmprunteur = 1;
		emprunteur.setId(idEmprunteur);

		Emprunt emprunt = new Emprunt();
		int idEmprunt = 1;
		emprunt.setId(idEmprunt);
		emprunt.setDateEmprunt(LocalDate.now());
		emprunt.setDateRetour(LocalDate.now().plusDays(28));
		emprunt.setEmprunteur(emprunteur);

		empruntsExpected.add(emprunt);
		
		doReturn(empruntsExpected).when(empruntDao).findAll();

		List<Emprunt> empruntsReal = empruntBusiness.getEmpruntsEmprunteur(idEmprunteur);

		assertTrue(empruntsReal.size() == 1);
	}

	@Test
	public void prolonger_shouldProlongerEmprunt_ofEmpruntId() {

		Emprunt empruntExpected = new Emprunt();
		int idEmprunt = 1;
		empruntExpected.setId(idEmprunt);
		empruntExpected.setProlongation(false);
		empruntExpected.setDateEmprunt(LocalDate.now());
		empruntExpected.setDateRetour(LocalDate.now().plusDays(28));

		when(empruntDao.findById(idEmprunt)).thenReturn(empruntExpected);
		
		doReturn(empruntExpected).when(empruntDao).findById(idEmprunt);

		//doNothing().when(empruntDao.save(empruntExpected));

		Emprunt empruntReal = empruntBusiness.prolonger(idEmprunt);

		assertTrue(empruntReal.getProlongation());
	}

	@Test
	public void prolonger_shouldNotWork_ofEmpruntId() {

		Emprunt empruntExpected = new Emprunt();
		int idEmprunt = 1;
		empruntExpected.setId(idEmprunt);
		empruntExpected.setProlongation(false);
		empruntExpected.setDateEmprunt(LocalDate.now().minusDays(30));
		empruntExpected.setDateRetour(LocalDate.now().minusDays(30).plusDays(28));

		//when(empruntDao.findById(idEmprunt)).thenReturn(empruntExpected);
		
		doReturn(empruntExpected).when(empruntDao).findById(idEmprunt);

		// doNothing().when(empruntDao.save(empruntExpected));

		Emprunt empruntReal = empruntBusiness.prolonger(idEmprunt);

		assertEquals(LocalDate.now().minusDays(30).plusDays(28), empruntReal.getDateRetour());
	}

}
