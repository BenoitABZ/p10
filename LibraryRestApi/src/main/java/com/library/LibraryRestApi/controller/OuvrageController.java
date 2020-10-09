package com.library.LibraryRestApi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.OuvrageDao;
import com.library.LibraryRestApi.model.Ouvrage;
import com.library.LibraryRestApi.model.OuvrageAuth;

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
	public List<OuvrageAuth> getOuvrage(@PathVariable("keyString") String keyString) {

		if (keyString == "null" || keyString == null) {

			List<Ouvrage> ouvrages = ouvrageDao.findAll();

			List<OuvrageAuth> ouvragesBuff = new ArrayList<OuvrageAuth>();

			for (Ouvrage ouvrage : ouvrages) {

				OuvrageAuth ouvrageAuth = new OuvrageAuth();

				ouvrageAuth.setId(ouvrage.getId());
				ouvrageAuth.setAnneeParution(ouvrage.getAnneeParution());
				ouvrageAuth.setTitre(ouvrage.getTitre());
				ouvrageAuth.setAuteur(ouvrage.getAuteur());
				ouvrageAuth.setResume(ouvrage.getResume());
				ouvrageAuth.setCategorie(ouvrage.getCategorie());
				ouvrageAuth.setImage(ouvrage.getImage());
				ouvrageAuth.setDisponibilite(ouvrage.getDisponibilite());

				int nombreExemplaires = ouvrage.getExemplaires().size();

				ouvrageAuth.setNombreExemplaires(nombreExemplaires);

				ouvragesBuff.add(ouvrageAuth);

			}

			return ouvragesBuff;

		}

		String keyS = format(keyString);

		List<OuvrageAuth> ouvragesBuff = new ArrayList<OuvrageAuth>();

		List<Ouvrage> ouvrages = ouvrageDao.findAll();

		for (Ouvrage ouvrage : ouvrages) {

			if (keyS.equals(format(ouvrage.getTitre())) || format(ouvrage.getAuteur()).contains(keyS)
					|| keyS.equals(format(ouvrage.getCategorie()))) {

				OuvrageAuth ouvrageAuth = new OuvrageAuth();

				ouvrageAuth.setId(ouvrage.getId());
				ouvrageAuth.setAnneeParution(ouvrage.getAnneeParution());
				ouvrageAuth.setTitre(ouvrage.getTitre());
				ouvrageAuth.setAuteur(ouvrage.getAuteur());
				ouvrageAuth.setResume(ouvrage.getResume());
				ouvrageAuth.setCategorie(ouvrage.getCategorie());
				ouvrageAuth.setImage(ouvrage.getImage());
				ouvrageAuth.setDisponibilite(ouvrage.getDisponibilite());

				int nombreExemplaires = ouvrage.getExemplaires().size();

				ouvrageAuth.setNombreExemplaires(nombreExemplaires);

				ouvragesBuff.add(ouvrageAuth);

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
