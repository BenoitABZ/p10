package com.library.LibraryClientUi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.LibraryClientUi.beans.BibliothequeBean;
import com.library.LibraryClientUi.proxies.BibliothequeProxy;

@Controller
public class BibliothequeClientController {
	
	@Autowired
	BibliothequeProxy bibliothequeProxy;
	
	@RequestMapping("/Liste-sites")
	public String listerBibliotheques(Model model) {
		
        	
		List<BibliothequeBean> bibliotheques = bibliothequeProxy.getBibliotheques();
		
		model.addAttribute("bibliotheques", bibliotheques);
		
		return ("Liste-sites");


    }
}
