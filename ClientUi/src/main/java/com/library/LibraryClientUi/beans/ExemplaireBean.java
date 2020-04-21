package com.library.LibraryClientUi.beans;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope=ExemplaireBean.class)
public class ExemplaireBean {
	

	public int id; 

	public String anneePublication;
    
	public String editeur;
	
	
	public BibliothequeBean bibliotheque;
	
	
	public EmpruntBean emprunt;
	
	public OuvrageBean ouvrage;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(String anneePublication) {
		this.anneePublication = anneePublication;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public BibliothequeBean getBibliotheque() {
		return bibliotheque;
	}

	public void setBibliotheque(BibliothequeBean bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	public EmpruntBean getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(EmpruntBean emprunt) {
		this.emprunt = emprunt;
	}

	public OuvrageBean getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(OuvrageBean ouvrage) {
		this.ouvrage = ouvrage;
	}


}
