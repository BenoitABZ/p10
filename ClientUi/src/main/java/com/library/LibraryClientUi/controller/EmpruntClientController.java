package com.library.LibraryClientUi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.LibraryClientUi.beans.EmpruntBean;
import com.library.LibraryClientUi.beans.EmprunteurAuth;
import com.library.LibraryClientUi.proxies.EmpruntProxy;

@Controller
public class EmpruntClientController {

	@Autowired
	EmpruntProxy empruntProxy;

	private String dateAndFormat(String dateString) {

		LocalDate date = LocalDate.parse(dateString);

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-uuuu");

		String newDate = date.format(formatters);

		return newDate;
	}

	@GetMapping("/Liste-emprunts")
	public String listerEmprunts(Model model, HttpServletRequest req) {

		EmprunteurAuth emprunteurAuth = new EmprunteurAuth();

		HttpSession session = req.getSession();

		if (session.getAttribute("emprunteur") != null) {

			emprunteurAuth = (EmprunteurAuth) session.getAttribute("emprunteur");

			int id = emprunteurAuth.getId();

			List<EmpruntBean> emprunts = empruntProxy.getEmpruntsEmprunteur(id);

			for (EmpruntBean emprunt : emprunts) {

				String dateEmprunt = dateAndFormat(emprunt.getDateEmprunt());

				emprunt.setDateEmprunt(dateEmprunt);

				String dateRetour = dateAndFormat(emprunt.getDateRetour());

				emprunt.setDateRetour(dateRetour);
			}

			model.addAttribute("emprunts", emprunts);

			return "Liste-emprunts";

		} else {

			return "Connexion";
		}

	}

	@GetMapping("/Liste-emprunts/prolonger/{empruntId}")
	public String prolongerEmprunts(@PathVariable("empruntId") int empruntId, Model model) {

		empruntProxy.prolongerEmprunt(empruntId);

		return "redirect:/Liste-emprunts";

	}
}
