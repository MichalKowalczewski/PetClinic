package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import interfaces.DaoInterface;
import interfaces.EntityInterface;
import interfaces.FilterInterface;

public class GenericLazyModel<EntityInterface> extends LazyDataModel<EntityInterface> {

	private static final long serialVersionUID = 20L;

	private List<EntityInterface> result;
	private FilterInterface filter;
	private DaoInterface dao;

	public GenericLazyModel(FilterInterface filter, DaoInterface dao) {
		this.dao = dao;
		this.filter = filter;
	}

	@Override
	public List<EntityInterface> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		this.setRowCount(dao.countSpecificObjects(filters, filter));
		result = (List<EntityInterface>) dao.getSpecificData(first, sortField, sortOrder, filters, filter);
		return result;
	}

	@Override
	public EntityInterface getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (EntityInterface entity : result) {
			if (((interfaces.EntityInterface) entity).getId() == intRowKey) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public Integer getRowKey(EntityInterface entity) {
		return ((interfaces.EntityInterface) entity).getId();
	}
}
