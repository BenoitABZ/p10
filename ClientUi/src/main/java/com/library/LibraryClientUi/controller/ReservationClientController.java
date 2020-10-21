package com.library.LibraryClientUi.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.LibraryClientUi.dto.EmprunteurDto;
import com.library.LibraryClientUi.dto.ReservationDto;
import com.library.LibraryClientUi.proxies.OuvrageProxy;
import com.library.LibraryClientUi.proxies.ReservationProxy;

@Controller
public class ReservationClientController {

	@Autowired
	ReservationProxy reservationProxy;

	@Autowired
	OuvrageProxy ouvrageProxy;

	@GetMapping(value = "/Reservations/{ouvrageId}")
	public String ajouterReservation(@PathVariable("ouvrageId") int ouvrageId, Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		List<ReservationDto> reservationsDto = (List<ReservationDto>) session.getAttribute("reservationsDtoSession");

		for (ReservationDto reservationDto : reservationsDto) {

			if (reservationDto.getOuvrageDto().getId() == ouvrageId) {

				reservationDto.setDateReservation(LocalDate.now());

				reservationProxy.ajouterReservation(reservationDto);

			}
		}

		return "redirect:/Liste-reservations";
	}

	@GetMapping(value = "/Liste-reservations")
	public String getReservationsEmprunteur(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession();

		if (session.getAttribute("emprunteur") != null) {

			EmprunteurDto emprunteurDto = (EmprunteurDto) session.getAttribute("emprunteur");

			List<ReservationDto> reservationsDto = reservationProxy.getReservationsEmprunteur(emprunteurDto.getId());

			model.addAttribute("reservations", reservationsDto);

			return "Liste-reservations";

		} else {

			return "Connexion";
		}

	}

	@GetMapping(value = "/Reservations/supprimer/{reservationId}")
	public String supprimerReservation(@PathVariable("reservationId") int reservationId) {

		reservationProxy.supprimerReservation(reservationId);

		return "redirect:/Liste-reservations";

	}
}
