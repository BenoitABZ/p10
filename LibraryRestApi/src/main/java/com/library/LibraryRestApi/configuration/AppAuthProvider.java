package com.library.LibraryRestApi.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppAuthProvider extends DaoAuthenticationProvider {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	UserDetailsImpl userDetailsImpl;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String name = auth.getName();
		String password = auth.getCredentials().toString();
		UserDetails emprunteur = userDetailsService.loadUserByUsername(name);
		if (emprunteur == null) {

			throw new UsernameNotFoundException("Identifiant incorrect");

		}

		if (!passwordEncoder.matches(password, emprunteur.getPassword())) {

			throw new BadCredentialsException("Mot de passe incorrect " + auth.getPrincipal());
		}

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		return new UsernamePasswordAuthenticationToken(name, password, userDetailsImpl.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
