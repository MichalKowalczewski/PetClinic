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
@Table(name="Type")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Type implements EntityInterface {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TypeID")
	private Integer typeId;
	@Column(name="TypeName")
	private String typeName;
	@OneToMany(mappedBy="type")
	private List<Pet> pets;
	
    @Override
    public boolean equals(Object other) {
        return (other instanceof Type) && (typeId != null)
            ? typeId.equals(((Type) other).typeId)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (typeId != null)
            ? (this.getClass().hashCode() + typeId.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Type[%d, %s]", typeId, typeName);
    }
	    
	@Override
	public int getId() {
		return typeId;
	}
	
}
