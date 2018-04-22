package com.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import interfaces.EntityInterface;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Vet")
@NoArgsConstructor
public @Data class Vet implements EntityInterface {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VetID")
	private int vetId;
	@Column(name="VetFirstName")
	private String vetFirstName;
	@Column(name="VetLastName")
	private String vetLastName;
	@Getter private static int tempVetId; 

	public String getVetFullName() {
		return vetFirstName + " " + vetLastName;
	}

	@Override
	public int getId() {
		return vetId;
	}	
}
