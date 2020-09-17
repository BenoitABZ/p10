package com.library.LibraryRestApi.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.model.Emprunteur;
import com.library.LibraryRestApi.model.EmprunteurAuth;

@RestController
public class LogController {

	@Autowired
	AuthenticationProvider authManager;

	@Autowired
	EmprunteurDao emprunteurDao;

	@PostMapping(value = "/Connexion")
	public EmprunteurAuth login(HttpServletRequest req, @RequestBody EmprunteurAuth emprunteurAuth) {

		String user;
		String pass;

		try {

			user = emprunteurAuth.getIdentifiant();
			pass = emprunteurAuth.getMotDePasse();

			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, pass);

			Authentication auth = authManager.authenticate(authReq);

			SecurityContext sc = SecurityContextHolder.getContext();

			sc.setAuthentication(auth);

			HttpSession session = req.getSession(true);

			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

		} catch (BadCredentialsException e1) {

			String status = e1.getMessage();

			emprunteurAuth.setStatus(status);

			return emprunteurAuth;

		} catch (UsernameNotFoundException e2) {

			String status = e2.getMessage();

			emprunteurAuth.setStatus(status);

			return emprunteurAuth;

		} catch (Exception e3) {

			String status = "erreur interne, réessayer dans quelques instants";

			emprunteurAuth.setStatus(status);

			return emprunteurAuth;

		}

		Emprunteur emprunteur = emprunteurDao.findByIdentifiant(user).get();

		emprunteurAuth.setNom(emprunteur.getNom());

		emprunteurAuth.setPrenom(emprunteur.getPrenom());

		emprunteurAuth.setId(emprunteur.getId());

		String status = "succes";

		emprunteurAuth.setStatus(status);

		return emprunteurAuth;
	}

	@GetMapping(value = "/Deconnexion")
	public String logout(HttpServletRequest req) {

		SecurityContextHolder.clearContext();

		HttpSession session = req.getSession(false);

		if (session != null) {
			session.invalidate();

		}

		String status = "Deconnecté";

		return status;
	}
}
