package com.library.LibraryClientUi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.LibraryClientUi.dto.EmprunteurDto;
import com.library.LibraryClientUi.proxies.LogProxy;

@Controller
public class LogClientController {

	@Autowired
	LogProxy logProxy;

	@PostMapping(path = "/Connexion", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String connecter(Model model, @RequestParam MultiValueMap<String, String> params, HttpServletRequest req) {

		EmprunteurDto emprunteurAuthParam = new EmprunteurDto();

		String identifiant = params.getFirst("identifiant");

		String password = params.getFirst("motDePasse");

		emprunteurAuthParam.setIdentifiant(identifiant);

		emprunteurAuthParam.setMotDePasse(password);

		EmprunteurDto emprunteurDto = logProxy.login(emprunteurAuthParam);

		model.addAttribute(emprunteurDto);

		if (emprunteurDto.getStatus().equals("succes")) {

			HttpSession session = req.getSession();

			session.setAttribute("emprunteur", emprunteurDto);

			return "Index";
		}

		else {

			return "Connexion";
		}

	}

	@GetMapping("/Connexion")
	public String ConnexionPage(Model model) {

		return "Connexion";
	}

	@GetMapping("/Deconnexion")
	public String Deconnecter(Model model, HttpServletRequest req) {

		logProxy.logout();

		HttpSession session = req.getSession();

		session.invalidate();

		return "Connexion";
	}

}
