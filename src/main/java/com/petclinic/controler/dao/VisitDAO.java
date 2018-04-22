package com.petclinic.controler.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.primefaces.model.SortOrder;

import com.petclinic.filters.VisitFilter;
import com.petclinic.model.Visit;

import interfaces.DaoInterface;
import interfaces.FilterInterface;

@ManagedBean(name = "visitDAO", eager = true)
@SessionScoped
public class VisitDAO extends DaoHelper<Visit> implements DaoInterface {

	public List<Visit> data;
	public Map<Integer, Visit> dataMap;
	private Visit visit = new Visit();

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	@Override
	public List<Visit> getData(Integer first) {
		TypedQuery<Visit> typedQuery = getAllQuery(first, 10, Visit.class);
		List<Visit> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Map<Integer, Visit> getDataMap(Integer first) {
		List<Visit> tempList;
		tempList = getData(first);
		dataMap = new HashMap<Integer, Visit>();
		for (Visit v : tempList)
			dataMap.put(v.getVisitId(), v);

		return dataMap;
	}

	public List<Visit> getSpecificData(Integer first, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, FilterInterface filter) {
		System.out.println("sortField :" + sortField);
		TypedQuery<Visit> typedQuery = getJoinedQuery(first, 10, Visit.class, sortField, sortOrder, filters, filter);
		List<Visit> data = typedQuery.getResultList();
		return data;
	}

	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter) {
		return countJoinedObjects(Visit.class, filters, filter);
	}

	public List<Visit> getVisitCalendar(String vetFirstName, String vetLastName, Date startDate, Date endDate) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(Visit.class);
		VisitFilter filter = new VisitFilter();
		filter.setJoins(Visit.class, criteriaQuery, criteriaBuilder);

		CriteriaQuery<Visit> select = criteriaQuery.select(filter.getFrom());

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(filter.getTimestampPredicate(startDate, endDate));
		if(!vetFirstName.equals("stop")) {
		predicates.add(filter.getPredicate("vetFirstName", vetFirstName));
		predicates.add(filter.getPredicate("vetLastName", vetLastName));
		}

		if (predicates.size() > 0) {
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		}

		TypedQuery<Visit> query = getTypedQuery(select);
		return query.getResultList();
	}

}