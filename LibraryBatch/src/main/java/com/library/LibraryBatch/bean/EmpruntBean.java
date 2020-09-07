package com.library.LibraryBatch.bean;


import java.io.Serializable;


import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope=EmpruntBean.class)

public class EmpruntBean implements Serializable{
	
	private static final long serialVersionUID = 1L; 

    private int id;
    
 
    private String dateEmprunt;
    

    private String dateRetour;
    
    
    private Boolean prolongation;
 
    private ExemplaireBean exemplaire;

	private EmprunteurBean emprunteur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

