package com.library.LibraryRestApi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.dto.OuvrageDto;
import com.library.LibraryRestApi.model.Exemplaire;
import com.library.LibraryRestApi.model.Ouvrage;

@RestController
public class OuvrageController {

	@Autowired
	public OuvrageDao ouvrageDao;

	private String format(String formatable) {

		String format = formatable.toLowerCase().trim();

		String formatWithoutAccent = StringUtils.stripAccents(format);

		return formatWithoutAccent;

	}

	@GetMapping(value = "/Ouvrages")
	public List<Ouvrage> getOuvrages() {

		List<Ouvrage> ouvrages = ouvrageDao.findAll();

		return ouvrages;
	}

	@GetMapping(value = "/Search/Ouvrages/{keyString}")
	public List<OuvrageDto> getOuvrage(@PathVariable("keyString") String keyString) {

		if (keyString == "null" || keyString == null) {

			List<Ouvrage> ouvrages = ouvrageDao.findAll();

			List<OuvrageDto> ouvragesBuff = new ArrayList<OuvrageDto>();

			for (Ouvrage ouvrage : ouvrages) {

				OuvrageDto ouvrageDto = new OuvrageDto();

				ouvrageDto.setId(ouvrage.getId());
				ouvrageDto.setAnneeParution(ouvrage.getAnneeParution());
				ouvrageDto.setTitre(ouvrage.getTitre());
				ouvrageDto.setAuteur(ouvrage.getAuteur());
				ouvrageDto.setResume(ouvrage.getResume());
				ouvrageDto.setCategorie(ouvrage.getCategorie());
				ouvrageDto.setImage(ouvrage.getImage());
				ouvrageDto.setDisponibilite(ouvrage.getDisponibilite());

				int nombreExemplaires = ouvrage.getExemplaires().size();

				ouvrageDto.setNombreExemplaires(nombreExemplaires);

				int nombreReservations = ouvrage.getReservations().size();

				ouvrageDto.setNombreReservations(nombreReservations);

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

				ouvragesBuff.add(ouvrageDto);

			}

			return ouvragesBuff;

		}

		String keyS = format(keyString);

		List<OuvrageDto> ouvragesBuff = new ArrayList<OuvrageDto>();

		List<Ouvrage> ouvrages = ouvrageDao.findAll();

		for (Ouvrage ouvrage : ouvrages) {

			if (keyS.equals(format(ouvrage.getTitre())) || format(ouvrage.getAuteur()).contains(keyS)
					|| keyS.equals(format(ouvrage.getCategorie()))) {

				OuvrageDto ouvrageDto = new OuvrageDto();

				ouvrageDto.setId(ouvrage.getId());
				ouvrageDto.setAnneeParution(ouvrage.getAnneeParution());
				ouvrageDto.setTitre(ouvrage.getTitre());
				ouvrageDto.setAuteur(ouvrage.getAuteur());
				ouvrageDto.setResume(ouvrage.getResume());
				ouvrageDto.setCategorie(ouvrage.getCategorie());
				ouvrageDto.setImage(ouvrage.getImage());
				ouvrageDto.setDisponibilite(ouvrage.getDisponibilite());

				int nombreExemplaires = ouvrage.getExemplaires().size();

				ouvrageDto.setNombreExemplaires(nombreExemplaires);

				try {

					int nombreReservations = ouvrage.getReservations().size();

					ouvrageDto.setNombreReservations(nombreReservations);

				} catch (NullPointerException e) {

					ouvrageDto.setNombreReservations(0);

				}
				
				if (ouvrage.getDisponibilite() == false) {
					
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

				}
				
				ouvragesBuff.add(ouvrageDto);
			}
			
		}

		return ouvragesBuff;
	}

	@PostMapping(value = "/Ouvrages")
	public void ajouterOuvrage(@RequestBody Ouvrage ouvrage) {

		ouvrageDao.save(ouvrage);
	}

	@DeleteMapping(value = "/Ouvrages/{ouvrageId}")
	public void supprimerOuvrage(@PathVariable("ouvrageId") int ouvrageId) {

		ouvrageDao.deleteById(ouvrageId);
	}

}
