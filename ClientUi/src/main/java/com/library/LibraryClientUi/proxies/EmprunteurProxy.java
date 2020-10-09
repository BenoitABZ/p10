package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.EmprunteurBean;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface EmprunteurProxy {

	@GetMapping(value = "/Emprunteurs")
	public List<EmprunteurBean> getEmprunteurs();

	@GetMapping(value = "/Emprunteurs/{emprunteurId}")
	public EmprunteurBean getEmprunteur(@PathVariable("emprunteurId") int emprunteurId);

	@PostMapping(value = "/Emprunteurs")
	public void ajouterEmprunteur(@RequestBody EmprunteurBean emprunteur);

	@DeleteMapping(value = "/Emprunteurs/{emprunteurId}")
	public void supprimerEmprunteur(@PathVariable("emprunteurId") int emprunteurId);

}
