package com.petclinic.filters;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.petclinic.controler.datatable.OwnerDataTable;
import com.petclinic.model.Owner;
import com.petclinic.model.Pet;
import com.petclinic.model.Type;
import com.petclinic.model.Vet;
import com.petclinic.model.Visit;

import interfaces.FilterInterface;

public class VisitFilter implements FilterInterface {
	
	private Join<Visit, Pet> petJoin;
	private Join<Visit, Vet> vetJoin;
	private Join<Pet, Owner> ownerJoin;
	private Join<Pet, Type> typeJoin;
 	private Root<Visit> from;
 	private CriteriaBuilder criteriaBuilder = null;
	@ManagedProperty(value = "#{ownerDataTable}")
	OwnerDataTable visitController;

	@Override
	public Order getOrder(String sortField, SortOrder sortOrder) {
		if (sortField.equals("petName")) {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(petJoin.get(sortField))
					: criteriaBuilder.desc(petJoin.get(sortField));	}
		if (sortField.equals("ownerFirstName") || sortField.equals("ownerLastName")) {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(ownerJoin.get(sortField))
					: criteriaBuilder.desc(ownerJoin.get(sortField));	}
		if (sortField.equals("vetFirstName") || sortField.equals("vetLastName")){
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(vetJoin.get(sortField))
					: criteriaBuilder.desc(vetJoin.get(sortField));	}
		else {
			return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(from.get(sortField))
					: criteriaBuilder.desc(from.get(sortField));	}
	}


	@Override
	public Predicate getPredicate(String field, Object value) {
		Predicate p = null;
		Path<String> pathFilter = null;
		if (field.equals("visitId")) {
			Expression<String> expression = from.get(field).as(String.class);
			p = criteriaBuilder.like(expression, "%" + value.toString().toLowerCase() + "%");
		}
		if (field.equals("visitDate")) {
			Path<Date> pathDateFilter = null;	
			pathDateFilter = from.get(field);
			Date selectedDate = (Date) value;
			Calendar c = Calendar.getInstance(); 
			c.setTime(selectedDate);
			c.add(Calendar.DATE, 1);
			Date nextDay = c.getTime();
			p = criteriaBuilder.between(pathDateFilter, selectedDate, nextDay);
		}
		else {
			if (field.equals("visitDate"))
				pathFilter = from.get(field);
			if (field.equals("petName"))
				pathFilter = petJoin.get(field);
			if (field.equals("typeName"))
				pathFilter = typeJoin.get(field);
			if (field.equals("ownerFirstName") || field.equals("ownerLastName"))
				pathFilter = ownerJoin.get(field);
			if (field.equals("vetFirstName") || field.equals("vetLastName"))
				pathFilter = vetJoin.get(field);		


			p = criteriaBuilder.like(pathFilter, "%" + value.toString().toLowerCase() + "%");
		}
		return p;
	}

	public Predicate getTimestampPredicate(Date startDate, Date endDate) {
		Predicate p = null;
		Path<Date> pathFilter = null;		
		pathFilter = from.get("visitDate");		
		return p = criteriaBuilder.between(pathFilter, startDate, endDate);
	}

	@Override
	public void setJoins(Class chosenClass, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
		from = criteriaQuery.from(chosenClass);
		petJoin = from.join("pet");
		vetJoin = from.join("vet");
		ownerJoin = petJoin.join("owner");
		typeJoin = petJoin.join("type");
		this.criteriaBuilder = criteriaBuilder;
		
		
	}

	@Override
	public <Visit> Root getFrom() {
		return from;
	}

	@Override
	public String getIdName() {
		return "visitId";
	}


}
