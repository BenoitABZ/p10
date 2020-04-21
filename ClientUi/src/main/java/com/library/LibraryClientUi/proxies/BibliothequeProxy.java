package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.BibliothequeBean;

@FeignClient(name="microservice-library", url="localhost:8080")
public interface BibliothequeProxy {
	
	 @GetMapping(value = "/Bibliotheques")   
	   public List<BibliothequeBean> getBibliotheques(); 
	 
	 @GetMapping(value = "/Bibliotheques/{bibliothequeId}")
	   public BibliothequeBean getBibliotheque(@PathVariable("bibliothequeId") int bibliothequeId);
	 
	 @PostMapping(value = "/Bibliotheques")
	   public void ajouterBibliotheque(@RequestBody BibliothequeBean bibliotheque);
	 
	 @DeleteMapping(value ="/Bibliotheques/{bibliothequeId}")
	   public void supprimerBibliotheque(@PathVariable("bibliothequeId") int bibliothequeId);

}
