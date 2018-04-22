package com.petclinic.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import interfaces.EntityInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Owner")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Owner implements EntityInterface {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OwnerID")
	private Integer ownerId;
	@Column(name="OwnerFirstName")
	private String ownerFirstName;
	@Column(name="OwnerLastName")
	private String ownerLastName;
	@Column(name="Address")
	private String address;
	@Column(name="City")
	private String city;
	@Column(name="Phone")
	private Integer phone;
	@OneToMany(mappedBy="owner")
	public List<Pet> pets;
		
	public String getOwnerFullName() {
		return ownerFirstName + " " + ownerLastName;
	}
	
    @Override
    public boolean equals(Object other) {
        return (other instanceof Type) && (ownerId != null)
            ? ownerId.equals(((Owner) other).ownerId)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (ownerId != null)
            ? (this.getClass().hashCode() + ownerId.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Owner[%d, %s]", ownerId, getOwnerFullName());
    }
	
	@Override
	public int getId() {
		return ownerId;
	}
	
}
