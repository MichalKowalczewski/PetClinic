package com.petclinic.model;

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
@Table(name="Pet")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Pet implements EntityInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PetID")
	private int petId;
	@Column(name="PetName")
	private String petName;
	
	@ManyToOne
	@JoinColumn(name = "PetTypeID", referencedColumnName = "TypeID")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "PetOwnerID", referencedColumnName = "OwnerID")
	private Owner owner;

	@Override
	public int getId() {
		return petId;
	}
}
