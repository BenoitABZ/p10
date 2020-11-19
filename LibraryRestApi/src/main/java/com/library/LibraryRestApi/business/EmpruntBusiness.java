package com.library.LibraryRestApi.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryRestApi.dao.EmpruntDao;
import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.dao.ExemplaireDao;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;

@Service
public class EmpruntBusiness {

	@Autowired
	EmpruntDao empruntDao;

	@Autowired
	ExemplaireDao exemplaireDao;

	@Autowired
	EmprunteurDao emprunteurDao;

	@Autowired
	OuvrageBusiness ouvrageBusiness;

	@Autowired
	ReservationBusiness reservationBusiness;

	@Autowired
	EmprunteurBusiness emprunteurBusiness;

	public List<Emprunt> getEmpruntsEmprunteur(int emprunteurId) {

		List<Emprunt> empruntsBuff = new ArrayList<>();

		List<Emprunt> emprunts = empruntDao.findAll();

		for (Emprunt emprunt : emprunts) {

			int id = emprunt.getEmprunteur().getId();

			if (id == emprunteurId) {

				emprunt = verifyProlongation(emprunt);

				empruntsBuff.add(emprunt);

			}

		}

		return empruntsBuff;

	}

	public void ajouterEmprunt(Emprunt emprunt) {

		int emprunteurId = emprunt.getEmprunteur().getId();

		int exemplaireId = emprunt.getExemplaire().getId();

		Emprunteur emprunteur = emprunteurDao.findById(emprunteurId);

		Exemplaire exemplaire = exemplaireDao.findById(exemplaireId);

		if (exemplaire.getEmprunt() == null) {

			Ouvrage ouvrage = exemplaire.getOuvrage();

			ouvrage = ouvrageBusiness.setDispo(ouvrage);

			emprunteur = reservationBusiness.deleteIfReservation(emprunteur, ouvrage);

			LocalDate dateEmprunt = LocalDate.now();

			LocalDate dateRetour = dateEmprunt.plusDays(28);

			emprunt.setDateEmprunt(dateEmprunt);

			emprunt.setDateRetour(dateRetour);

			emprunt.setEmprunteur(emprunteur);

			emprunt.setExemplaire(exemplaire);

			empruntDao.save(emprunt);

		}

	}

	public void retourEmprunt(int exemplaireId) {

		Exemplaire exemplaire = exemplaireDao.findById(exemplaireId);

		Emprunt emprunt = exemplaire.getEmprunt();

		Ouvrage ouvrage = exemplaire.getOuvrage();

		empruntDao.delete(emprunt);

		emprunteurBusiness.updateEmprunts(emprunt);

		ouvrageBusiness.updateExemplaires(ouvrage, exemplaire);
	}

	public Emprunt prolonger(int empruntId) {

		Emprunt emprunt = empruntDao.findById(empruntId);

		LocalDate dateRetour = emprunt.getDateRetour();

		emprunt = verifyProlongation(emprunt);

		if (emprunt.getProlongation() == false) {

			emprunt.setProlongation(true);

			LocalDate dateProlongation = dateRetour.plusDays(28);

			emprunt.setDateRetour(dateProlongation);

			empruntDao.save(emprunt);
		}

		return emprunt;
	}

	private Emprunt verifyProlongation(Emprunt emprunt) {

		LocalDate dateRetour = emprunt.getDateRetour();

		LocalDate now = LocalDate.now();

		if (now.isAfter(dateRetour)) {

			emprunt.setProlongation(true);

			empruntDao.save(emprunt);
		}

		return emprunt;

	}
}
