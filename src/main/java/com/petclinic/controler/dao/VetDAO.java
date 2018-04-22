package com.petclinic.controler.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Vet;

import interfaces.DaoInterface;
import interfaces.FilterInterface;

@ManagedBean(name = "vetDAO", eager = true)
@SessionScoped
public class VetDAO extends DaoHelper<Vet> implements DaoInterface  {

	public List<Vet> data;
	public Map<Integer, Vet> dataMap;

	private Vet vet = new Vet();

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}
	
	@Override
	public List<Vet> getData(Integer first){	
		TypedQuery<Vet> typedQuery = getAllQuery(first, 10, Vet.class);
		List<Vet> data = typedQuery.getResultList();
		return data;
	}
	
	@Override
	public Map<Integer, Vet> getDataMap(Integer first) {
		List<Vet> tempList;
		tempList = getData(first);
		dataMap = new HashMap<Integer, Vet>();
		for (Vet v : tempList)
			dataMap.put(v.getVetId(), v);

		return dataMap;
	}
	
	public List<Vet> getSpecificData(Integer first, String sortField, SortOrder sortOrder, Map<String, Object> filters, FilterInterface filter){
		TypedQuery<Vet> typedQuery = getJoinedQuery(first, 10, Vet.class, sortField, sortOrder, filters, filter);
		List<Vet> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter) {
		return countJoinedObjects(Vet.class, filters, filter);
	}
}