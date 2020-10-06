package com.library.LibraryBatch.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ExemplaireBean.class)
public class ExemplaireBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String anneePublication;

	private String editeur;

	private BibliothequeBean bibliotheque;

	private EmpruntBean emprunt;

	private OuvrageBean ouvrage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
