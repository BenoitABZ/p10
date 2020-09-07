package com.library.LibraryClientUi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.LibraryClientUi.beans.EmprunteurAuth;

@Controller
public class IndexClientController {
	
	@RequestMapping("/")
	public String accueil(Model model) {
		
		EmprunteurAuth emprunteurAuth = new EmprunteurAuth();
		
		emprunteurAuth.setPrenom("");
		
		model.addAttribute(emprunteurAuth);
		
		return "Index";
	}

}
