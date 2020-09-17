package com.library.LibraryClientUi.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = EmpruntBean.class)

public class EmpruntBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public int id;

	public String dateEmprunt;

	public String dateRetour;

	public Boolean prolongation;

	public ExemplaireBean exemplaire;

	public EmprunteurBean emprunteur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(String dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Boolean getProlongation() {
		return prolongation;
	}

	public void setProlongation(Boolean prolongation) {
		this.prolongation = prolongation;
	}

	public ExemplaireBean getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(ExemplaireBean exemplaire) {
		this.exemplaire = exemplaire;
	}

	public EmprunteurBean getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(EmprunteurBean emprunteur) {
		this.emprunteur = emprunteur;
	}

}
