package com.petclinic.controler.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Type;

import interfaces.DaoInterface;
import interfaces.FilterInterface;

@ManagedBean(name = "typeDAO", eager = true)
@SessionScoped
public class TypeDAO extends DaoHelper<Type> implements DaoInterface {

	public List<Type> data;
	public Map<Integer, Type> dataMap;

	private Type type = new Type();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public List<Type> getData(Integer first){	
		TypedQuery<Type> typedQuery = getAllQuery(first, 10, Type.class);
		List<Type> data = typedQuery.getResultList();
		return data;
	}
	
	@Override
	public Map<Integer, Type> getDataMap(Integer first) {
		List<Type> tempList;
		tempList = getData(first);
		dataMap = new HashMap<Integer, Type>();
		for (Type t : tempList)
			dataMap.put(t.getTypeId(), t);

		return dataMap;
	}
	
	public List<Type> getSpecificData(Integer first, String sortField, SortOrder sortOrder, Map<String, Object> filters, FilterInterface filter){
		TypedQuery<Type> typedQuery = getJoinedQuery(first, 10, Type.class, sortField, sortOrder, filters, filter);
		List<Type> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter) {
		return countJoinedObjects(Type.class, filters, filter);
	}
	
	
}