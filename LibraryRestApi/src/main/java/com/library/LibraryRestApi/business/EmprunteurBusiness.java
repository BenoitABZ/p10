package com.library.LibraryRestApi.business;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;

@Service
public class EmprunteurBusiness {
	
	@Autowired
	EmprunteurDao emprunteurDao;
	
	public void updateEmprunts(Emprunt emprunt) {
		
		Emprunteur emprunteur = emprunt.getEmprunteur();
		
		Set<Emprunt> emprunts = emprunteur.getEmprunts();

		if (emprunts.contains(emprunt)) {

			emprunts.remove(emprunt);

			emprunteur.setEmprunts(emprunts);

			emprunteurDao.save(emprunteur);

		}
		
		
	}

}
