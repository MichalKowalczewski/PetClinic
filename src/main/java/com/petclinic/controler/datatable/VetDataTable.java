package com.petclinic.controler.datatable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.petclinic.controler.dao.VetDAO;
import com.petclinic.filters.SimpleFilter;
import com.petclinic.model.Vet;
import com.petclinic.model.lazy.GenericLazyModel;

import interfaces.FilterInterface;
import lombok.Data;
@ManagedBean(name = "vetDataTable", eager = true)
@ViewScoped
public @Data class VetDataTable {
	private String selectedVetFullName;
	private String selectedVetFirstName;
	private String selectedVetLastName;
	private int selectedVetId;
	private Vet selectedVet;
	private GenericLazyModel<Vet> vetModel;
	@ManagedProperty(value = "#{vetDAO}")
	private VetDAO vetDAO;
	
	@PostConstruct
	public void init() {
		FilterInterface filter = new SimpleFilter("vetId");
		vetModel = new GenericLazyModel<Vet>(filter, vetDAO);
		vetModel.setPageSize(10);
	}

	public void onRowSelect(SelectEvent event) {
		selectedVetFirstName = selectedVet.getVetFirstName();
		selectedVetLastName = selectedVet.getVetLastName();
		selectedVetFullName = selectedVet.getVetFullName();
		selectedVetId = selectedVet.getVetId();
	}

	public LazyDataModel<Vet> getVetModel() {
		return vetModel;
	}
}
