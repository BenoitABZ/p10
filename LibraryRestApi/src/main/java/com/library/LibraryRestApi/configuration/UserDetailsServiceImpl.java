package com.library.LibraryRestApi.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.LibraryRestApi.dao.EmprunteurDao;
import com.library.LibraryRestApi.model.Emprunteur;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmprunteurDao emprunteurDao;

	@Override
	public UserDetails loadUserByUsername(String identifiant) throws UsernameNotFoundException {

		Emprunteur emprunteur = (emprunteurDao.findByIdentifiant(identifiant))
				.orElseThrow(() -> new UsernameNotFoundException("Emprunteur non trouvé"));

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		return new UserDetailsImpl(emprunteur, authorities);
	}
}
