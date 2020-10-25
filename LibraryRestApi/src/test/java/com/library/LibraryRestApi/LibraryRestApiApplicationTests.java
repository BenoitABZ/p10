package com.library.LibraryRestApi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.ExemplaireDao;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest
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

	@Test
	void contextLoads() {
	}
	
	@Test
	void getBibliotheques_shouldReturnListBibliotheques() throws Exception {
		
		mockMvc.perform(get("/Bibliotheques"))
		       .andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	void getEmpruntsEmprunteur_shouldRetournListEmprunts() throws Exception {
	
		List<Emprunt> empruntsBdd = empruntDao.findAll();
		
		Emprunt emprunt = empruntsBdd.get(0);
		
		Emprunteur emprunteur = emprunt.getEmprunteur();
	
        emprunt.setProlongation(false);
        
        emprunt.setDateEmprunt(LocalDate.of(2020, 03, 27));
        
        emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(28));
        
        Set<Emprunt> empruntsEmprunteur = new HashSet<>();
                
        emprunt.setEmprunteur(emprunteur);
        
        empruntsEmprunteur.add(emprunt);
        
        emprunteur.setEmprunts(empruntsEmprunteur);
                
        emprunteurDao.save(emprunteur);
		
		mockMvc.perform(get("/Search/Emprunts/" + emprunteur.getId()))
		       .andExpect(jsonPath("$", hasSize(1)))
		       .andExpect(jsonPath("$[0].prolongation", is(true)));
	}

}
