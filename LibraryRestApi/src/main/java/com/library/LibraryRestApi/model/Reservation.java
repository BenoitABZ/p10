package com.library.LibraryRestApi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Reservation.class)
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "reservation_reservation_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_reservation")
	private Integer id;

	@Column(name = "date_reservation")
	private LocalDate dateReservation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ouvrage")
	private Ouvrage ouvrage;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_emprunteur")
	private Emprunteur emprunteur;

	public Integer getId() {
		return id;
	}

}