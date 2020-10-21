package com.library.LibraryBatch.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReservationBean.class)
public class ReservationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDate dateReservation;

	private Date dateNotification;

	private boolean notification;

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

	public Date getDateNotification() {
		return dateNotification;
	}

	public void setDateNotification(Date dateNotification) {
		this.dateNotification = dateNotification;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
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
