package com.petclinic.controler.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Owner;

import interfaces.DaoInterface;
import interfaces.FilterInterface;

@ManagedBean (name = "ownerDAO", eager = true)
@SessionScoped
public class OwnerDAO extends DaoHelper<Owner> implements DaoInterface {
		
	public List<Owner> data;
	public Map<Integer, Owner> dataMap;
	private Owner owner = new Owner();
	

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public List<Owner> getData(Integer first) {
		TypedQuery<Owner> typedQuery = getAllQuery(first, 10, Owner.class);
		List<Owner> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Map<Integer, Owner> getDataMap(Integer first) {
		List<Owner> tempList;
		tempList = getData(first);
		dataMap = new HashMap<Integer, Owner>();
		for (Owner o : tempList)
			dataMap.put(o.getOwnerId(), o);

		return dataMap;
	}
	
	public List<Owner> getSpecificData(Integer first, String sortField, SortOrder sortOrder, Map<String, Object> filters, FilterInterface filter){
		TypedQuery<Owner> typedQuery = getJoinedQuery(first, 10, Owner.class, sortField, sortOrder, filters, filter);
		List<Owner> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter) {
		return countJoinedObjects(Owner.class, filters, filter);
	}
}