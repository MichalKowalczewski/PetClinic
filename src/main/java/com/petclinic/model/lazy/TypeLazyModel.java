package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.petclinic.controler.dao.TypeDAO;
import com.petclinic.model.Type;

public class TypeLazyModel extends LazyDataModel<Type> {
	private static final long serialVersionUID = 1L;
	private TypeDAO typeDAO;
	private List<Type> result;

	public TypeLazyModel(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

	@Override
	public List<Type> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		//this.setRowCount(typeDAO.countFilteredObjects(Type.class, filters));
		//result = typeDAO.getSpecificData(first, sortField, sortOrder, filters);
		
		return result;
	}	

	@Override
	public Type getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (Type type : result) {
			if (type.getTypeId() == intRowKey) {
				return type;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Type type) {
		return type.getTypeId();
	}
}
