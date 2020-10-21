package com.library.LibraryClientUi.beans;

import java.io.Serializable;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OuvrageBean.class)
public class OuvrageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public Integer id;

	public String titre;

	public String auteur;

	public String categorie;

	public String resume;

	public String anneeParution;

	public Boolean disponibilite;

	public String image;

	public Set<ExemplaireBean> exemplaires;

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

	public Set<ExemplaireBean> getExemplaires() {
		return exemplaires;
	}

	public void setExemplaires(Set<ExemplaireBean> exemplaires) {
		this.exemplaires = exemplaires;
	}

}
