package com.library.LibraryClientUi.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.EmprunteurAuth;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface LogProxy {

	@PostMapping(value = "/Connexion")
	public EmprunteurAuth login(@RequestBody EmprunteurAuth emprunteurAuth);

	@GetMapping(value = "/Deconnexion")
	public String logout();

}
