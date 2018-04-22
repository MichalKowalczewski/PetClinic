package com.petclinic.filters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Visit;

import interfaces.EntityInterface;
import interfaces.FilterInterface;

public class SimpleFilter implements FilterInterface {
	
 	private Root<EntityInterface> from;
 	private CriteriaBuilder criteriaBuilder = null;
 	private String idName;
 	
 	public SimpleFilter(String idName) {
 		this.idName = idName;
 	}
 	
	@Override
	public Order getOrder(String sortField, SortOrder sortOrder) {
		return sortOrder == SortOrder.DESCENDING ? criteriaBuilder.asc(from.get(sortField))
				: criteriaBuilder.desc(from.get(sortField));	
		}
	

	@Override
	public Predicate getPredicate(String field, Object value) {
		Predicate p = null;
		Path<String> pathFilter = null;
			Expression<String> expression = from.get(field).as(String.class);
			p = criteriaBuilder.like(expression, "%" + value.toString().toLowerCase() + "%");
			
			return p;
	}

	@Override
	public void setJoins(Class chosenClass, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
		from = criteriaQuery.from(chosenClass);
		this.criteriaBuilder = criteriaBuilder;
	}

	@Override
	public <T> Root<T> getFrom() {
		return (Root<T>) from;
	}

	@Override
	public String getIdName() {
		return idName;
	}

}
