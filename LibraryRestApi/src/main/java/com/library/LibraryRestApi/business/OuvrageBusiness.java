package com.library.LibraryRestApi.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.model.Emprunt;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;

@Service
public class OuvrageBusiness {

	@Autowired
	OuvrageDao ouvrageDao;

	public Ouvrage setDispo(Ouvrage ouvrage) {

		Set<Exemplaire> exemplairesBuff = new HashSet<>();

		Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

		ouvrage.setDisponibilite(true);

		for (Exemplaire exemplaireBuff : exemplaires) {

			if (exemplaireBuff.getEmprunt() == null) {

				exemplairesBuff.add(exemplaireBuff);

			}
		}

		if (exemplairesBuff.size() == 1) {

			ouvrage.setDisponibilite(false);

			ouvrageDao.save(ouvrage);

		}

		return ouvrage;

	}

	public void updateExemplaires(Ouvrage ouvrage, Exemplaire exemplaire) {

		Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

		if (exemplaires.contains(exemplaire)) {

			exemplaires.remove(exemplaire);

			exemplaire.setEmprunt(null);

			exemplaires.add(exemplaire);
		}

		ouvrage.setDisponibilite(true);

		ouvrage.setExemplaires(exemplaires);

		ouvrageDao.save(ouvrage);
	}

	public OuvrageDto populateOuvrageDto(Ouvrage ouvrage) {

		OuvrageDto ouvrageDto = new OuvrageDto();

		try {
			ouvrageDto.setId(ouvrage.getId());
			ouvrageDto.setAnneeParution(ouvrage.getAnneeParution());
			ouvrageDto.setTitre(ouvrage.getTitre());
			ouvrageDto.setAuteur(ouvrage.getAuteur());
			ouvrageDto.setResume(ouvrage.getResume());
			ouvrageDto.setCategorie(ouvrage.getCategorie());
			ouvrageDto.setImage(ouvrage.getImage());
			ouvrageDto.setDisponibilite(ouvrage.getDisponibilite());
		} catch (NullPointerException e) {

			e.printStackTrace();
		}

		LocalDate closerDate = calculateCloserDate(ouvrage);

		ouvrageDto.setCloserDate(closerDate);

		return ouvrageDto;

	}

	public LocalDate calculateCloserDate(Ouvrage ouvrage) {

		List<Emprunt> emprunts = new ArrayList<Emprunt>();

		Set<Exemplaire> exemplaires = ouvrage.getExemplaires();

		for (Exemplaire exemplaire : exemplaires) {

			if (exemplaire.getEmprunt() != null) {

				emprunts.add(exemplaire.getEmprunt());

			}

		}

		Collections.sort(emprunts, new DateComparator());

		return emprunts.get(0).getDateRetour();

	}

	private class DateComparator implements Comparator<Emprunt> {

		@Override
		public int compare(Emprunt arg0, Emprunt arg1) {

			return arg0.getDateRetour().compareTo(arg1.getDateRetour());
		}

	}

}
