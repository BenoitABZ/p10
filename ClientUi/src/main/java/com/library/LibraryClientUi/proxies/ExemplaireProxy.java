package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.ExemplaireBean;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface ExemplaireProxy {

	@GetMapping(value = "/Exemplaires")
	public List<ExemplaireBean> getExemplaires();

	@GetMapping(value = "/Exemplaires/{exemplaireid}")
	public ExemplaireBean getExemplaire(@PathVariable("exemplaireId") int exemplaireId);

	@GetMapping(value = "/search/Exemplaires/{ouvrageTitre}")
	public List<ExemplaireBean> getExemplaireOuvrage(@PathVariable("ouvrageTitre") String ouvrageTitre);

	@PostMapping(value = "/Exemplaires")
	public void ajouterExemplaire(@RequestBody ExemplaireBean exemplaire);

	@DeleteMapping(value = "/Exemplaires/{exemplaireId}")
	public void supprimerExemplaire(@PathVariable("exemplaireId") int exemplaireId);

}
