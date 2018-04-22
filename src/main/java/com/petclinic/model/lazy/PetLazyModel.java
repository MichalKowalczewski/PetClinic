package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.petclinic.controler.dao.PetDAO;
import com.petclinic.filters.PetFilter;
import com.petclinic.model.Pet;

import interfaces.FilterInterface;

public class PetLazyModel extends LazyDataModel<Pet> {
	private static final long serialVersionUID = 3L;
	@ManagedProperty(value = "#{petDAO}")
	private PetDAO petDAO;
	private List<Pet> result;

	public PetLazyModel(PetDAO petDAO) {
		this.petDAO = petDAO;
	}

	@Override
	public List<Pet> load(int first, int pageSize, String sortField, SortOrder sortOrder, 
			Map<String, Object> filters) {
		FilterInterface filter = new PetFilter();
		this.setRowCount(petDAO.countJoinedObjects(Pet.class, filters, filter));
		result = petDAO.getSpecificData(first, sortField, sortOrder, filters, filter);

		
		return result;
	}

	@Override
	public Pet getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (Pet pet : result) {
			if (pet.getPetId() == intRowKey) {
				return pet;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Pet pet) {
		return pet.getPetId();
	}
}