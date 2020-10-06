package com.library.LibraryBatch.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.LibraryBatch.bean.EmprunteurBean;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface EmprunteurProxy {

	@GetMapping(value = "/Emprunteurs/retardataires")
	public List<EmprunteurBean> getEmprunteursRetardataires();

}
