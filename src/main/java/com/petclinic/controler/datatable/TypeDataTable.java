package com.petclinic.controler.datatable;

import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.petclinic.controler.dao.TypeDAO;
import com.petclinic.filters.SimpleFilter;
import com.petclinic.model.Type;
import com.petclinic.model.lazy.GenericLazyModel;

import interfaces.FilterInterface;
import lombok.Data;

@ManagedBean(name = "typeDataTable", eager = true)
@ViewScoped
public @Data class TypeDataTable {
	private String selectedTypeName;
	private int selectedTypeId;
	private Type selectedType;
	private GenericLazyModel<Type> typeModel;
	@ManagedProperty(value = "#{typeDAO}")
	private TypeDAO typeDAO;
	
	@PostConstruct
	public void init() {
		FilterInterface filter = new SimpleFilter("typeId");
		typeModel = new GenericLazyModel<Type>(filter, typeDAO);
		typeModel.setPageSize(10);
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("wybralem " + selectedType.getTypeName());
		selectedTypeName = selectedType.getTypeName();
		selectedTypeId = selectedType.getTypeId();
	}

	public LazyDataModel<Type> getTypeModel() {
		return typeModel;
	}
}
