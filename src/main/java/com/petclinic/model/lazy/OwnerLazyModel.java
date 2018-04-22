package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.petclinic.controler.dao.OwnerDAO;
import com.petclinic.model.Owner;

public class OwnerLazyModel extends LazyDataModel<Owner> {
	private static final long serialVersionUID = 2L;
	private OwnerDAO ownerDAO;
	private List<Owner> result;

	public OwnerLazyModel(OwnerDAO ownerDAO) {
		this.ownerDAO = ownerDAO;
	}

	@Override
	public List<Owner> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		//this.setRowCount(ownerDAO.countFilteredObjects(Owner.class, filters));
		//result = ownerDAO.getSpecificData(first, sortField, sortOrder, filters);

		return result;
	}

	@Override
	public Owner getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (Owner owner : result) {
			if (owner.getOwnerId() == intRowKey) {
				return owner;
			}
		}
		return null;
	}

	@Override
	public Integer getRowKey(Owner owner) {
		return owner.getOwnerId();
	}

}
