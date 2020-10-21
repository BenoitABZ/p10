package com.library.LibraryClientUi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.LibraryClientUi.dto.EmprunteurDto;

@Controller
public class IndexClientController {

	@RequestMapping("/")
	public String accueil(Model model) {

		EmprunteurDto emprunteurDto = new EmprunteurDto();

		emprunteurDto.setPrenom("");

		model.addAttribute(emprunteurDto);

		return "Index";
	}

}
