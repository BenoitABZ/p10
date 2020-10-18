package com.library.LibraryBatch.bean;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = EmprunteurBean.class)
public class EmprunteurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public int id;

	public String nom;

	public String prenom;

	public String mail;

	public String telephone;

	public String identifiant;

	public String motDePasse;

	public String dateInscription;

	public AdresseBean adresse;

	public Set<EmpruntBean> emprunts;

	private Set<ReservationBean> reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public AdresseBean getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseBean adresse) {
		this.adresse = adresse;
	}

	public Set<EmpruntBean> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(Set<EmpruntBean> emprunts) {
		this.emprunts = emprunts;
	}

	public Set<ReservationBean> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationBean> reservations) {
		this.reservations = reservations;
	}

}
