package com.library.LibraryBatch.bean;

import java.io.Serializable;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope=BibliothequeBean.class)

public class BibliothequeBean {


		public int id;
	    	    
	    public String nom;
	    
	    public String mail;
	    
	    public String telephone;
	    
	    public String image;
	    	
	    public AdresseBean adresse;
	    	    
		public Set<ExemplaireBean> exemplaires;
		
		



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



		public String getImage() {
			return image;
		}



		public void setImage(String image) {
			this.image = image;
		}



		public AdresseBean getAdresse() {
			return adresse;
		}



		public void setAdresse(AdresseBean adresse) {
			this.adresse = adresse;
		}



		public Set<ExemplaireBean> getExemplaires() {
			return exemplaires;
		}



		public void setExemplaires(Set<ExemplaireBean> exemplaires) {
			this.exemplaires = exemplaires;
		}



@Override
public String toString() {
    return "BibliothequeBean{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", adresse='" + adresse + '\'' +
            ", exemplaires='" + exemplaires + '\'' +
            
            '}';
}
}
