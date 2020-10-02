package com.library.LibraryRestApi.dto;

import java.time.LocalDate;

public class ReservationDto {

private int id;
	
	private OuvrageDto ouvrageDto;
	
	private EmprunteurDto emprunteurDto;

	private LocalDate dateReservation;
	
	private int positionListe;

	private boolean autorisation;

	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}

	public int getPositionListe() {
		return positionListe;
	}

	public void setPositionListe(int positionListe) {
		this.positionListe = positionListe;
	}

	public boolean isAutorisation() {
		return autorisation;
	}

	public void setAutorisation(boolean autorisation) {
		this.autorisation = autorisation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OuvrageDto getOuvrageDto() {
		return ouvrageDto;
	}

	public void setOuvrageDto(OuvrageDto ouvrageDto) {
		this.ouvrageDto = ouvrageDto;
	}

	public EmprunteurDto getEmprunteurDto() {
		return emprunteurDto;
	}

	public void setEmprunteurDto(EmprunteurDto emprunteurDto) {
		this.emprunteurDto = emprunteurDto;
	}
}
