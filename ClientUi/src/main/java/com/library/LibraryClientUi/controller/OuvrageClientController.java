package com.library.LibraryClientUi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.LibraryClientUi.dto.EmprunteurDto;
import com.library.LibraryClientUi.dto.OuvrageDto;
import com.library.LibraryClientUi.dto.ReservationDto;
import com.library.LibraryClientUi.proxies.OuvrageProxy;
import com.library.LibraryClientUi.proxies.ReservationProxy;

@Controller
public class OuvrageClientController {

	@Autowired
	OuvrageProxy ouvrageProxy;

	@Autowired
	ReservationProxy reservationProxy;

	@PostMapping(path = "/Search/Ouvrages", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String getSelectOuvrages(Model model, HttpServletRequest req,
			@RequestParam MultiValueMap<String, String> params) {

		String keyString = params.getFirst("libelleCase");

		if (keyString == null || keyString == "") {

			keyString = "null";
		}

		List<OuvrageDto> ouvrages = ouvrageProxy.getSelectOuvrages(keyString);

		List<OuvrageDto> ouvragesWithReservationCheck = new ArrayList<OuvrageDto>();

		List<ReservationDto> reservationsDtoSession = new ArrayList<ReservationDto>();

		for (OuvrageDto ouvrageDto : ouvrages) {

			ReservationDto reservationDto = new ReservationDto();

			reservationDto.setOuvrageDto(ouvrageDto);

			if (req.getSession().getAttribute("emprunteur") != null) {

				EmprunteurDto emprunteurDto = (EmprunteurDto) req.getSession().getAttribute("emprunteur");

				reservationDto.setEmprunteurDto(emprunteurDto);

				reservationDto.setOuvrageDto(ouvrageDto);

				ReservationDto reservationDtoAfterCheck = reservationProxy.checkReservation(reservationDto);

				reservationsDtoSession.add(reservationDtoAfterCheck);

				ouvrageDto.setReservationDto(reservationDtoAfterCheck);

				ouvragesWithReservationCheck.add(ouvrageDto);

				req.getSession().setAttribute("reservationsDtoSession", reservationsDtoSession);
			}else {
				
				reservationDto.setAutorisation(false);
				
				reservationDto.setMessage("Vous devez etre inscrit pour reserver");
				
				ouvrageDto.setReservationDto(reservationDto);
				
				ouvragesWithReservationCheck.add(ouvrageDto);
				
			}
		}

		model.addAttribute("ouvrages", ouvragesWithReservationCheck);

		return "Liste-ouvrages";

	}
}
