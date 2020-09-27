package com.library.LibraryClientUi.beans;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReservationBean.class)
public class ReservationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDate dateReservation;

	private OuvrageBean ouvrage;

	private EmprunteurBean emprunteur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}

	public OuvrageBean getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(OuvrageBean ouvrage) {
		this.ouvrage = ouvrage;
	}

	public EmprunteurBean getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(EmprunteurBean emprunteur) {
		this.emprunteur = emprunteur;
	}

}
