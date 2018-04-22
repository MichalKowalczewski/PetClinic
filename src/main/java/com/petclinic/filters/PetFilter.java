package com.petclinic.filters;

import javax.faces.bean.ManagedProperty;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.Type;

import interfaces.FilterInterface;

public class PetFilter implements FilterInterface {
		
	private Join<Pet, Owner> ownerJoin;
	private Join<Pet, Type> typeJoin;
	private Root<Pet> from;
	private CriteriaBuilder criteriaBuilder = null;
	
	@Override
	public Order getOrder(String sortField, SortOrder sortOrder) {
		if (sortField.equals("typeName")) {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(typeJoin.get(sortField))
					: criteriaBuilder.desc(typeJoin.get(sortField));	}
		if (sortField.equals("ownerFirstName") || sortField.equals("ownerLastName")) {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(ownerJoin.get(sortField))
					: criteriaBuilder.desc(ownerJoin.get(sortField));	}
		else {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(from.get(sortField))
					: criteriaBuilder.desc(from.get(sortField));	}
	}

	@Override
	public Predicate getPredicate(String field, Object value) {
		Predicate p = null;
		Path<String> pathFilter = null;
			if (field.equals("petId")) {
				Expression<String> expression = from.get(field).as(String.class);
				p = criteriaBuilder.like(expression, "%" + value.toString().toLowerCase() + "%");
			} else {
				if (field.equals("petName"))
					pathFilter = from.get(field);
				if (field.equals("typeName"))
					pathFilter = typeJoin.get(field);
				if (field.equals("ownerFirstName") || field.equals("ownerLastName"))
					pathFilter = ownerJoin.get(field);

				p = criteriaBuilder.like(pathFilter, "%" + value.toString().toLowerCase() + "%");
			}
				
		return p;
	}

	@Override
	public void setJoins(Class chosenClass, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
		from = criteriaQuery.from(chosenClass);
		ownerJoin = from.join("owner");
		typeJoin = from.join("type");	
		this.criteriaBuilder = criteriaBuilder;
		
	}

	@Override
	public Root<Pet> getFrom() {
		return from;
	}

	@Override
	public String getIdName() {
		return "petId";
	}



	

}
