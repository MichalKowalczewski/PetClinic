package com.petclinic.model.lazy;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.petclinic.controler.dao.VisitDAO;
import com.petclinic.filters.VisitFilter;
import com.petclinic.model.Visit;

import interfaces.FilterInterface;

public class VisitLazyModel extends LazyDataModel<Visit> {
	private static final long serialVersionUID = 5L;
	@ManagedProperty(value = "#{visitDAO}")
	private VisitDAO visitDAO;
	private List<Visit> result;

	public VisitLazyModel(VisitDAO visitDAO) {
		this.visitDAO = visitDAO;
	}

	@Override
	public List<Visit> load(int first, int pageSize, String sortField, SortOrder sortOrder, 
			Map<String, Object> filters) {
		/*FilterInterface filter = new VisitFilter();
		this.setRowCount(visitDAO.countJoinedObjects(Visit.class, filters, filter));
		result = visitDAO.getVisitCalendar("Stefan", "Jakistam");*/

		
		return result;
	}

	@Override
	public Visit getRowData(String rowKey) {
		int intRowKey = Integer.parseInt(rowKey);
		for (Visit visit : result) {
			if (visit.getVisitId() == intRowKey) {
				return visit;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Visit visit) {
		return visit.getVisitId();
	}
}