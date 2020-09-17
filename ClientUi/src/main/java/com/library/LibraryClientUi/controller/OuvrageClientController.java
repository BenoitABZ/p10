package com.library.LibraryClientUi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.LibraryClientUi.beans.OuvrageAuth;
import com.library.LibraryClientUi.proxies.OuvrageProxy;

@Controller
public class OuvrageClientController {

	@Autowired
	OuvrageProxy ouvrageProxy;

	@PostMapping(path = "/Search/Ouvrages", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String getSelectOuvrages(Model model, @RequestParam MultiValueMap<String, String> params) {

		String keyString = params.getFirst("libelleCase");

		if (keyString == null || keyString == "") {

			keyString = "null";
		}

		List<OuvrageAuth> ouvrages = ouvrageProxy.getSelectOuvrages(keyString);

		model.addAttribute("ouvrages", ouvrages);

		return "Liste-ouvrages";

	}

}
