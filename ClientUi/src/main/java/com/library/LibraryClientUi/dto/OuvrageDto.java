package com.library.LibraryClientUi.dto;

import java.time.LocalDate;

public class OuvrageDto {

	private int id;

	private String titre;

	private String auteur;

	private String categorie;

	private String resume;

	private String anneeParution;

	private Boolean disponibilite;

	private String image;

	private int nombreExemplaires;

	private LocalDate closerDate;

	private int nombreReservations;

	private ReservationDto reservationDto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getAnneeParution() {
		return anneeParution;
	}

	public void setAnneeParution(String anneeParution) {
		this.anneeParution = anneeParution;
	}

	public Boolean getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNombreExemplaires() {
		return nombreExemplaires;
	}

	public void setNombreExemplaires(int nombreExemplaires) {
		this.nombreExemplaires = nombreExemplaires;
	}

	public LocalDate getCloserDate() {
		return closerDate;
	}

	public void setCloserDate(LocalDate closerDate) {
		this.closerDate = closerDate;
	}

	public int getNombreReservations() {
		return nombreReservations;
	}

	public void setNombreReservations(int nombreReservations) {
		this.nombreReservations = nombreReservations;
	}

	public ReservationDto getReservationDto() {
		return reservationDto;
	}

	public void setReservationDto(ReservationDto reservationDto) {
		this.reservationDto = reservationDto;
	}

}
