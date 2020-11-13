package com.library.LibraryRestApi.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="exemplaire")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Exemplaire.class)
public class Exemplaire implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "exemplaire_id_exemplaire_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_exemplaire")
	private Integer id;

	@Column(name = "annee_publication")
	private String anneePublication;

	@Column(name = "editeur")
	private String editeur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bibliotheque")
	private Bibliotheque bibliotheque;

	@OneToOne(mappedBy = "exemplaire", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Emprunt emprunt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ouvrage")
	private Ouvrage ouvrage;

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

	public Bibliotheque getBibliotheque() {
		return bibliotheque;
	}

	public void setBibliotheque(Bibliotheque bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	public Emprunt getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(Emprunt emprunt) {
		this.emprunt = emprunt;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	@Override
	public String toString() {
		return "Exemplaire{" + "id=" + id + ", anneePublication='" + anneePublication + '\'' + ", editeur='" + editeur
				+ '\'' + ", bibliotheque='" + bibliotheque + '\'' + ", ouvrage=" + ouvrage + '}';
	}

}
