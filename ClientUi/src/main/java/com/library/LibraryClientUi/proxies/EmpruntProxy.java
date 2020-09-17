package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.EmpruntBean;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface EmpruntProxy {

	@GetMapping(value = "/Emprunts")
	public List<EmpruntBean> getEmprunts();

	@GetMapping(value = "/Emprunt/{empruntId}")
	public EmpruntBean getEmprunt(@PathVariable("empruntId") int empruntId);

	@GetMapping(value = "/Search/Emprunts/{emprunteurId}")
	public List<EmpruntBean> getEmpruntsEmprunteur(@PathVariable("emprunteurId") int emprunteurId);

	@PostMapping(value = "/Pret")
	public void ajouterEmprunt(@RequestBody EmpruntBean emprunt);

	@GetMapping(value = "/Retour/{exemplaireId}")
	public void retourEmprunt(@PathVariable("exemplaireId") int exemplaireId);

	@GetMapping(value = "/Prolongation/{empruntId}")
	public List<EmpruntBean> prolongerEmprunt(@PathVariable("empruntId") int empruntId);

	@DeleteMapping(value = "/Emprunts/{empruntId}")
	public void supprimerEmprunt(@PathVariable("empruntId") int empruntId);

}
