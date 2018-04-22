package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.petclinic.controler.dao.VetDAO;
import com.petclinic.model.Vet;

public class VetLazyModel extends LazyDataModel<Vet> {
	private static final long serialVersionUID = 4L;
	private VetDAO vetDAO;
	private List<Vet> result;

	public VetLazyModel(VetDAO vetDAO) {
		this.vetDAO = vetDAO;
	}

	@Override
	public List<Vet> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		//this.setRowCount(vetDAO.countFilteredObjects(Vet.class, filters));
		//result = vetDAO.getSpecificData(first, sortField, sortOrder, filters);
		
		return result;
	}	

	@Override
	public Vet getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (Vet vet : result) {
			if (vet.getVetId() == intRowKey) {
				return vet;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Vet vet) {
		return vet.getVetId();
	}
}
