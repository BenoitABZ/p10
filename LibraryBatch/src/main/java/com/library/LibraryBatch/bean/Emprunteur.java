package com.library.LibraryBatch.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope=Emprunteur.class)

public class Emprunteur implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	    @Id
	    @GeneratedValue
	    @Column(name="id_emprunteur")
	    private Integer id;
	    
	    @Column(name="nom")
	    private String nom;

	    @Column(name="prenom")
	    private String prenom;

	    @Column(name="adresse_mail")
	    private String mail;

	    @Column(name="telephone")
	    private String telephone;
	    
	    @Column(name="identifiant")
	    private String identifiant;

	    @Column(name="mot_de_passe")
	    private String motDePasse;

	    @Column(name="date_inscription")
		private LocalDate dateInscription;
	    
		@Embedded		
	    private Adresse adresse;
		
	    
		@OneToMany(mappedBy="emprunteur", cascade=CascadeType.ALL)
		private Set<Emprunt> emprunts;


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
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


		public LocalDate getDateInscription() {
			return dateInscription;
		}


		public void setDateInscription(LocalDate dateInscription) {
			this.dateInscription = dateInscription;
		}


		public Adresse getAdresse() {
			return adresse;
		}


		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}


		public Set<Emprunt> getEmprunts() {
			return emprunts;
		}


		public void setEmprunts(Set<Emprunt> emprunts) {
			this.emprunts = emprunts;
		}

		
}
