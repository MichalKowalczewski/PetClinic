package com.petclinic.controler.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;

import org.primefaces.model.SortOrder;

import com.petclinic.model.Pet;

import interfaces.DaoInterface;
import interfaces.FilterInterface;

@ManagedBean(name = "petDAO", eager = true)
@SessionScoped
public class PetDAO extends DaoHelper<Pet> implements DaoInterface {

	public List<Pet> data;
	public Map<Integer, Pet> dataMap;
	private Pet pet = new Pet();

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
		
	@Override
	public List<Pet> getData(Integer first) {
		TypedQuery<Pet> typedQuery = getAllQuery(first, 10, Pet.class);
		List<Pet> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Map<Integer, Pet> getDataMap(Integer first) {
		List<Pet> tempList;
		tempList = getData(first);
		dataMap = new HashMap<Integer, Pet>();
		for (Pet p : tempList)
			dataMap.put(p.getPetId(), p);

		return dataMap;
	}

	public List<Pet> getSpecificData(Integer first, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, FilterInterface filter) {
		System.out.println("sortField :" + sortField);
		TypedQuery<Pet> typedQuery = getJoinedQuery(first, 10, Pet.class, sortField, sortOrder, filters, filter);
		List<Pet> data = typedQuery.getResultList();
		return data;
	}

	@Override
	public Integer countSpecificObjects(Map<String, Object> filters, FilterInterface filter) {
		return countJoinedObjects(Pet.class, filters, filter);
	}
}