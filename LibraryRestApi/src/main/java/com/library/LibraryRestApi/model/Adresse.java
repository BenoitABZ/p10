package com.library.LibraryRestApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "numero_voie")
	private String numeroVoie;

	@Column(name = "libelle_voie")
	private String libelleVoie;

	@Column(name = "nom_voie")
	private String nomVoie;

	@Column(name = "code_postal")
	private String codePostal;

	@Column(name = "ville")
	private String ville;

	public String getNumeroVoie() {
		return numeroVoie;
	}

	public void setNumeroVoie(String numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

	public String getLibelleVoie() {
		return libelleVoie;
	}

	public void setLibelleVoie(String libelleVoie) {
		this.libelleVoie = libelleVoie;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
