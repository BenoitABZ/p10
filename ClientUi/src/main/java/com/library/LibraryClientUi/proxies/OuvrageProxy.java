package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.OuvrageBean;
import com.library.LibraryClientUi.dto.OuvrageDto;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface OuvrageProxy {

	@GetMapping(value = "/Ouvrages")
	public List<OuvrageBean> getOuvrages();

	@GetMapping(value = "/Search/Ouvrages/{keyString}")
	public List<OuvrageDto> getSelectOuvrages(@PathVariable("keyString") String keyString);

	@PostMapping(value = "/Ouvrages")
	public void ajouterOuvrage(@RequestBody OuvrageBean ouvrage);

}
