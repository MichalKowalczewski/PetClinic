package com.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import interfaces.EntityInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Visit")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Visit implements EntityInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VisitID")
	private int visitId;
	
	@ManyToOne
	@JoinColumn(name = "VisitPetID", referencedColumnName = "PetID")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "VisitVetID", referencedColumnName = "VetID")
	private Vet vet;

	@Column(name="VisitDate")
	private Date visitDate;

	@Override
	public int getId() {
		return visitId;
	}

}
