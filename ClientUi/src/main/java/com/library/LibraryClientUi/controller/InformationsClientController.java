package com.library.LibraryClientUi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InformationsClientController {

	@RequestMapping("/Informations/InscrireEmprunter")
	public String inscrireEtEmprunter(Model model) {

		return "InscrireEtEmprunter";
	}

	@RequestMapping("/Informations/InternetEtMultimedia")
	public String internetEtMultimedia(Model model) {

		return "InternetEtMultimedia";
	}

	@RequestMapping("/Informations/PlaceEtEmprunter")
	public String placeEtEmprunter(Model model) {

		return "PlaceEtEmprunter";
	}

}
