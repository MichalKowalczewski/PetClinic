package com.petclinic.controler.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.util.ApplicationBean;

import interfaces.FilterInterface;

public abstract class DaoHelper<T> {

	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean application;
	
	public CriteriaQuery<T> criteriaQuery;

	public ApplicationBean getApplication() {
		return application;
	}

	public void setApplication(ApplicationBean application) {
		this.application = application;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		CriteriaBuilder criteriaBuilder = application.getSession().getCriteriaBuilder();
		return criteriaBuilder;
	}

	public TypedQuery<T> getTypedQuery(CriteriaQuery<T> select) {
		return application.getSession().createQuery(select);
	}
			
	public TypedQuery<T> getAllQuery(Integer first, Integer max, Class chosenClass) {
		CriteriaQuery<T> criteriaQuery = getCriteriaBuilder().createQuery(chosenClass);
		Root<T> from = criteriaQuery.from(chosenClass);
		CriteriaQuery<T> select = criteriaQuery.select(from);

		TypedQuery<T> typedQuery = application.getSession().createQuery(select);
		typedQuery.setFirstResult(first);
		typedQuery.setMaxResults(max);

		return typedQuery;
	}
	
	public TypedQuery<T> getJoinedQuery(Integer first, Integer max, Class<T> chosenClass, String sortField,
			SortOrder sortOrder, Map<String, Object> filters, FilterInterface filter) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(chosenClass);
		filter.setJoins(chosenClass, criteriaQuery, criteriaBuilder);

		CriteriaQuery<T> select = criteriaQuery.select(filter.getFrom());

		if (filters != null && filters.size() > 0) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				String field = entry.getKey();
				Object value = entry.getValue();
				System.out.println("testujemy field " + field);
				System.out.println("testujemy value " + value.toString());

				predicates.add(filter.getPredicate(field, value));
			}
			if (predicates.size() > 0) {
				criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			}
		}

		if (sortField != null) {
			criteriaQuery.orderBy(filter.getOrder(sortField, sortOrder));
		} else {
			criteriaQuery.orderBy((criteriaBuilder.asc(filter.getFrom().get(filter.getIdName()))));
		}

		TypedQuery<T> query = getTypedQuery(select);
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query;
	}

	
	public Integer countObjects(Class chosenClass) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(chosenClass)));
		int number = application.getSession().createQuery(countQuery).getSingleResult().intValue();
		return number;
	}
	
	public Integer countJoinedObjects(Class chosenClass, Map<String, Object> filters, FilterInterface filter) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		filter.setJoins(chosenClass, criteriaQuery, criteriaBuilder);
		CriteriaQuery<Long> select = criteriaQuery.select(criteriaBuilder.count(filter.getFrom()));

		if (filters != null && filters.size() > 0) {
			List<Predicate> predicates = new ArrayList<>();
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				String field = entry.getKey();
				Object value = entry.getValue();
				
				predicates.add(filter.getPredicate(field, value));
			}
			if (predicates.size() > 0) {
				criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			}
		}
		Long number = application.session.createQuery(select).getSingleResult();

		return number.intValue();
	}
}
