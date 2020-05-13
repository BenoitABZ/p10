package com.library.LibraryBatch.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Ouvrage.class)

public class Ouvrage implements Serializable{
	

	private static final long serialVersionUID = 1L; 
	
	    @Id
	    @GeneratedValue
	    @Column(name="id_ouvrage")
	    private Integer id;
	    
	    @Column(name="titre")
	    private String titre;

	    @Column(name="auteur")
	    private String auteur;

	    @Column(name="categorie")
	    private String categorie;

	    @Column(name="resume")
	    private String resume;

	    @Column(name="annee_parution")
		private String anneeParution;
	    
	    @Column(name="disponibilite")
	    private Boolean disponibilite;
	    
	    @Column(name="image")    
	    private String image;
        
	    
		@OneToMany(mappedBy="ouvrage",cascade=CascadeType.ALL)
		private Set<Exemplaire> exemplaires;
		

	  

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
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


		public Set<Exemplaire> getExemplaires() {
			return exemplaires;
		}

		public void setExemplaires(Set<Exemplaire> exemplaires) {
			this.exemplaires = exemplaires;
		}


		

}
