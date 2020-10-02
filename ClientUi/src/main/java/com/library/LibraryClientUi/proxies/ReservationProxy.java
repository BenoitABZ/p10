package com.library.LibraryClientUi.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.LibraryClientUi.beans.ReservationBean;
import com.library.LibraryClientUi.dto.ReservationDto;

@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface ReservationProxy {

	@GetMapping(value = "/Search/Reservations/{emprunteurId}")
	public List<ReservationDto> getReservationsEmprunteur(@PathVariable("emprunteurId") int emprunteurId);

	@GetMapping(value = "/Search/Reservations/{ouvrageId}")
	public List<ReservationBean> getReservationsOuvrage(@PathVariable("ouvrageId") int ouvrageId);

	@PostMapping(value = "/Reservations/CheckReservation")
	public ReservationDto checkReservation(@RequestBody ReservationDto reservationDto);

	@PostMapping(value = "/Reservations")
	public ReservationDto ajouterReservation(@RequestBody ReservationDto reservationDto);

	@DeleteMapping(value = "/Reservations/{reservationId}")
	public void supprimerReservation(@PathVariable("reservationId") int reservationId);

}
